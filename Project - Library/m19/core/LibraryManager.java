package m19.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;  
import m19.core.exception.MissingFileAssociationException;
import m19.core.exception.BadEntrySpecificationException;
import m19.core.exception.ImportFileException;

//imports referentes à gravação
import java.io.*;
import java.util.zip.*;



/**
 * The façade class.
 */
public class LibraryManager {

  private Library _library;
  private String _filename;


public LibraryManager(){
  _library = new Library();
}

  /**
   * Serialize the persistent state of this application.
   * 
   * @throws MissingFileAssociationException if the name of the file to store the persistent
   *         state has not been set yet.
   * @throws IOException if some error happen during the serialization of the persistent state

   */
  public void save() throws MissingFileAssociationException, IOException {
    saveAs(_filename);
  }

  /**
   * Serialize the persistent state of this application into the specified file.
   * 
   * @param filename the name of the target file
   *
   * @throws MissingFileAssociationException if the name of the file to store the persistent
   *         is not a valid one.
   * @throws IOException if some error happen during the serialization of the persistent state
   */
  public void saveAs(String filename) throws MissingFileAssociationException, IOException {

    try (ObjectOutputStream obOut= new ObjectOutputStream(new FileOutputStream(filename))) {
      obOut.writeObject(_library);
      _filename = filename;
    } 
  }

  /**
   * Recover the previously serialized persitent state of this application.
   * 
   * @param filename the name of the file containing the perssitente state to recover
   *
   * @throws IOException if there is a reading error while processing the file
   * @throws FileNotFoundException if the file does not exist
   * @throws ClassNotFoundException 
   */

  public void load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
    //ObjectInputStream obIn = null;
    try (ObjectInputStream obIn = new ObjectInputStream(new FileInputStream(filename))){
      Library anObject = (Library) obIn.readObject();
      _library = anObject;
      _filename = filename;
    }
  } 


  /**
   * Set the state of this application from a textual representation stored into a file.
   * 
   * @param datafile the filename of the file with the textual represntation of the state of this application.
   * @throws ImportFileException if it happens some error during the parsing of the textual representation.
   */
  public void importFile(String datafile) throws ImportFileException {
    try {
      _library.importFile(datafile);
    } catch (IOException | BadEntrySpecificationException e) {
      throw new ImportFileException(e);
    }
  }

  public int getCurrentDate(){
    return _library.getCurrentDate();
  }

  public void advanceDays(int nDays){
    _library.advanceDays(nDays);
}

  public int registerUser(String name, String email){
    if ("".equals(name) || "".equals(email))
      return -1;
    else{
     User user = new User(_library.getNextUserid(),name,email);
     _library.addUser(user);
     return user.getUserid();
    }
  }  
  
  public User getUser(int id) {
    return _library.getUser(id);
  }

  public Map<Integer,User> getUsers(){
    return _library.getUsers();
  }

  public Work getWork(int id){
    return _library.getWork(id);
  }

  public Map<Integer,Work> getWorks(){
    return _library.getWorks();
  }

  public List<Work> searchWorks(String term){
    return _library.searchWorks(term);
  }

  public String getFilename(){
    return _filename;
  }

  public void setFilename(String filename){
     _filename = filename;
  }


  public int requestWork(int userId, int workId){
    User user = getUser(userId);
    if (user == null)
      return -1;
    Work work = getWork(workId);
    if (work == null)
      return -2;
    return _library.requestWork(user, work);
  }

  public int returnWork(int userId, int workId){
    User user = getUser(userId);
    if (user == null)
      return -1;
    Work work = getWork(workId);
    if (work == null)
      return -2;
    return _library.returnWork(user, work);
  }

 public Request searchRequest(int userId, int workId){
  User user = getUser(userId);
  Work work = getWork(workId);
  return _library.searchRequest(user, work);
 }

  public int payFine(int id){
    User user = getUser(id);
    if (user == null)
      return -1;
    if (user.isActive()){
      return -2;
    }
    else{
      user.payFine();
      return 0;
    }
  }

  public void warnUserWhenWorkIsAvailable(User user, Work work){
    work.registerObserverEntrega(user);
  }

}
