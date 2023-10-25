package m19.core;

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;

public class User implements Serializable,Comparable<User>,Observer {

    private int _id;
    private int _fine;
    private boolean _isActive;
    private String _name;
    private String _email;
    private UserBehavior _behavior;
    private List<Notification> _notifications;
    private List<Request> _requests;
    private int _numberOfOpenRequests;
    private int _numberReturnedWorks;
    private int _numberDelayedWorks;

    public User (int id, String name, String email){

        _id = id;
        _fine = 0;
        _isActive = true;
        _name = name;
        _email = email;
        _behavior = new Normal();
        _notifications = new ArrayList<Notification>();
        _requests = new ArrayList<Request>();
        _numberOfOpenRequests = 0;
        _numberReturnedWorks = 0;
        _numberDelayedWorks = 0;

    } 



    public String getDescription(){
        return _id + " - " + _name + " - " + _email + " - " + _behavior.toString() + " - " + getStateToString();
    }

    int getUserid(){
        return _id; 
    }

    String getUserName(){
        return _name;
    }

    public int getFine(){
        return _fine; 
    }

    UserBehavior getBehavior(){
        return _behavior;
    }

    int getNumberOpenRequests(){
        return _numberOfOpenRequests;
    }

    int getMaxRequests(){
        return _behavior.getMaxRequests();
    }

    int getNumberReturnedWorks(){
        return _numberReturnedWorks;
    }

    int getNumberDelayedWorks(){
        return _numberDelayedWorks;
    }

    List<Request> getUserRequests(){
        return _requests;
    }

    public List<Notification> getNotifications(){
        return _notifications;
    }

    public void removeNotifications(){
        _notifications = new ArrayList<Notification>();
    }

    private String getStateToString(){
        if(_isActive)
            return "ACTIVO";
        else
            return "SUSPENSO" + " - EUR " + _fine;
        }


    boolean isActive(){
        return _isActive;
    }

    void changeState(){
        if(_isActive){
            _isActive = false;
        }
        else{
            _isActive = true;
        }
    }

    void setCumpridor(){
        _behavior = new Cumpridor();
    }
    void setFaltoso(){
        _behavior = new Faltoso();
    }
    void setNormal(){
        _behavior = new Normal();
    }

    Request createRequest(Work work){
        int deadline = _behavior.getDeadline(work);
        Request request = new Request(this, work, deadline);
        _requests.add(request);
        _numberOfOpenRequests++;
        return request;
    } 

    void closeRequest(){
        _numberOfOpenRequests--;
    }

    void increaseNumberReturnedWorks(){
        _numberReturnedWorks++;
    }

    void resetNumberReturnedWorks(){
        _numberReturnedWorks = 0;
    }

    void increaseNumberDelayedWorks(){
        _numberDelayedWorks++;
    }

    void resetNumberDelayedWorks(){
        _numberDelayedWorks = 0;
    } 

    public void payFine(){
        _fine = 0;
        for (Request r : _requests) {
           if(r.isOpen()){
            if(r.isDelayed()){
                return;
            }   
           }
        }
        _isActive = true;
    }

    void addFine(int fine){
        _fine += fine; 
    }

    public int compareTo(User otherUser) {
        return _name.compareTo(otherUser.getUserName());
    }


    public void updateEntrega(Work work){
        String s = "ENTREGA: " + work.getDescription();
        _notifications.add(new Notification(s));
    }

    public void updateRequisicao(Work work){
        String s = "REQUISIÇÃO: " + work.getDescription();
        _notifications.add(new Notification(s));
    }
}