package m19.core;

import java.io.Serializable;
import java.io.IOException;
import m19.core.exception.MissingFileAssociationException;
import m19.core.exception.BadEntrySpecificationException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap; 
import java.util.Map; 


/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201901101348L;

  private int _nextWorkid;
  private int _nextUserid;
  private Map<Integer,User> _users;
  private Map<Integer,Work> _works;
  private Date _date;
  private List<Request> _allRequests;
  private List<Rule> _rules;


  public Library(){
    _nextWorkid = 0;
    _nextUserid = 0;
    _users = new LinkedHashMap<Integer,User>();
    _works = new LinkedHashMap<Integer,Work>();
    _date = new Date();
    _allRequests = new ArrayList<Request>();
    _rules = new ArrayList<Rule>();
    _rules.add(new CheckRequestTwice(1));
    _rules.add(new CheckUserIsSuspended(2));
    _rules.add(new CheckCopyIsAvailable(3));
    _rules.add(new CheckNumberOfRequests(4));
    _rules.add(new CheckWorkIsReference(5));
    _rules.add(new CheckWorkPrice(6));
  }

  /*
  addUser ads User to library and increases _nextUserid
  */
  void addUser(User user){
    _users.put(_nextUserid,user);
    _nextUserid++;

  } 
  /*
  addBook ads Book to library and increases _nextWorkid
  */
  void addBook(Book book){
    _works.put(_nextWorkid,book);
    _nextWorkid++;
  } 
  /*
  addDvd ads Dvd to library and increases _nextWorkid
  */
  void addDvd(Dvd dvd){
    _works.put(_nextWorkid,dvd);
    _nextWorkid++;
  } 


  int getNextUserid(){
    return _nextUserid;
  }

  int getNextWorkid(){
    return _nextWorkid;
  }

  int getCurrentDate(){
    return _date.getCurrentDate();
  }

  /*
  advanceDate increases the internal date nDays
  */ 
  void advanceDays(int nDays){
     _date.advanceDate(nDays);
     for(int i = 0; i < nDays;i++){
      for (Request r : _allRequests) {
        if(r.isOpen()){
          r.update();
        }
      }
     }
 }


  /**
  * getUser returns User with a certain id
  * 
  */ 
  User getUser(int id){ 
    if (_users.containsKey(id)) { 
      User user = _users.get(id);
      return user; 
    }
    else{
      return null;
    }
  }

  /**
  * getUsers returns all Users 
  */ 
  Map<Integer,User> getUsers(){
    return _users;
  }

  /**
  * getWork returns Work with a certain id
  * 
  */ 
  Work getWork(int id){ 
    if (_works.containsKey(id)) { 
      Work work = _works.get(id);
      return work; 
    }
    else{
      return null;
    }
  }

  /**
  * getWorks returns all Works 
  */ 
  Map<Integer,Work> getWorks(){
      return _works;
    }

  /**
  * searchWorks returns list with works that contain term 
  */   
  List<Work> searchWorks(String term){
    String termAux = term.toLowerCase();
    List<Work> aux = new ArrayList<Work>();
    for (Map.Entry<Integer,Work> entry : _works.entrySet()) {    
      Work work = entry.getValue();
      if(work.getWorkTitle().toLowerCase().contains(termAux) || work.getOwner().toLowerCase().contains(termAux))
        aux.add(work);
    }
    return aux;
    }
   
   
  /*
  * checkRules verifies all the rules in _rules 
  */ 

    private int checkRules(User user, Work work){
        for (Rule r : _rules) {
          if(!r.verify(user, work))
            return r.getId();
        }
        return 0;
    }


  /*
  * requestWork lends a copy of a work if all rules are successful
  */ 

    public int requestWork(User user, Work work){
      int rule = checkRules(user, work);
      if (rule == 0){                                 //If all rules are successful
        Request request = user.createRequest(work);
        _allRequests.add(request);
        work.decreaseNumberOfCopies();
        return 0;
      } else return rule;
    }


  /*
  * returnWork returns the work back to library 
  */ 
    public int returnWork(User user, Work work){      
        Request request = searchRequest(user, work);
        if (request == null){
          return -3;
        } 
        request.closeRequest();
        user.closeRequest();
        work.increaseNumberOfCopies();
        if (work.getNumberOfCopies() == 1){
          work.notifyObserversEntrega();
        }
        if (request.isDelayed()){  // Returned out of time
          int fine = request.calculateFine();
          user.addFine(fine);
          user.resetNumberReturnedWorks();
          user.increaseNumberDelayedWorks();

          if(user.getBehavior().toString().equals("CUMPRIDOR")){
            user.setNormal();
          }
          if (user.getNumberDelayedWorks() == 3 && user.getBehavior().toString().equals("NORMAL")){
            user.setFaltoso();
          }
        } 
        else{  // Returned on time
          user.resetNumberDelayedWorks();
          user.increaseNumberReturnedWorks();
          if(user.getNumberReturnedWorks() == 5){
            user.setCumpridor();
          }
          if(user.getNumberReturnedWorks() == 3 && user.getBehavior().toString().equals("FALTOSO")){
            user.setNormal();
          }
        }
       return work.getWorkId();
    }



  /*
  * searchRequest searches for a request of a specific user  
  */
    public Request searchRequest(User user, Work work){
      for (Request r : _allRequests) {
        if (r.isOpen()){
          if (r.getUser() == user && r.getWork() == work)
            return r;
        }
      }
      return null;
    }



  /**
   * Read the text input file at the beginning of the program and populates the
   * instances of the various possible types (books, DVDs, users).
   * 
   * @param filename
   *          name of the file to load
   * @throws BadEntrySpecificationException
   * @throws IOException
   */
  void importFile(String filename) throws BadEntrySpecificationException, IOException {
    Parser parser = new Parser(this);
    parser.parseFile(filename);

  }



}
