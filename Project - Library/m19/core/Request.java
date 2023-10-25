package m19.core;

import java.io.Serializable;

public class Request implements Serializable{

    private int _deadline;
    private Work _work;
    private User _user;
    private boolean _isDelayed;
    private boolean _isOpen;

    public Request(User user, Work work,int deadline){
        _deadline = deadline;
        _work = work;
        _user = user;
        _isDelayed = false;
        _isOpen = true;
    }

    void update(){
        _deadline--;
        if(_deadline < 0){
            if (_user.isActive()){
                _user.changeState();
            }
            if(!_isDelayed){
                _isDelayed = true;
            }
        }
    }

    Work getWork(){
        return _work;
    }
    
    User getUser(){
        return _user;
    }

    int calculateFine(){
        return (Math.abs(_deadline) * 5);
    }

    boolean isOpen(){
        return _isOpen;
    }

    boolean isDelayed(){
        return _isDelayed;
    }

    public int getDeadline(){
        return _deadline;
    }

    void closeRequest(){
        if (_isOpen)
            _isOpen = false; 
    }

}    

