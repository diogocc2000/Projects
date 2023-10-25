package m19.core;

import java.io.Serializable;


public class Date implements Serializable {

    private int _currentDate;

    public Date (){
        _currentDate = 0;
    } 

    public int getCurrentDate(){
        return _currentDate;
    }

    public void advanceDate(int nDays){
        if(nDays >0)
        _currentDate += nDays;
    }
}   