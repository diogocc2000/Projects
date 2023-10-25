package m19.core;

import java.io.Serializable;


public class Dvd extends Work implements Serializable {

    private String _director;
    private String _igac;

    public Dvd (int id,String title,String director,int price,Category category,String igac,int numberOfCopies){
        super(id,price,category,title,numberOfCopies);
        _director = director;
        _igac = igac;
    } 

    @Override
    public String getDescription(){
        return super.getWorkId() + " - " + super.getNumberOfCopies() + " de " + super.getTotalNumberOfCopies() + " - DVD - " + super.getWorkTitle() + " - " + super.getWorkPrice() + " - " + super.getWorkCategory().toString() + " - " + _director + " - " + _igac;
    }

    @Override
    public String getOwner(){
        return _director;
    }

}    


