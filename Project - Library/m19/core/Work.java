package m19.core;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public  abstract class Work extends Subject implements Serializable {

    private int _id;
    private int _price;
    private int _numberOfCopies;
    private int _totalNumberOfCopies;
    private String _title;
    private Category _category;
    private List<Observer> _observersEntrega;
    private List<Observer> _observersRequisicao;
    

    public Work (int id, int price,Category category, String title, int numberOfCopies){
        _id = id;
        _price = price;
        _category = category;
        _title = title;
        _numberOfCopies = numberOfCopies;
        _totalNumberOfCopies = numberOfCopies;
        _observersEntrega = new ArrayList<Observer>();
        _observersRequisicao = new ArrayList<Observer>();
    } 

    int getWorkId(){
        return _id;
    }

    int getWorkPrice(){
        return _price;
    }

    void decreaseNumberOfCopies(){
        _numberOfCopies--;
    }
    
    void increaseNumberOfCopies(){
        _numberOfCopies++;
    }
    int getNumberOfCopies(){
        return _numberOfCopies;
    }

    int getTotalNumberOfCopies(){
        return _totalNumberOfCopies;
    }

    String getWorkTitle(){
        return _title;
    }
    Category getWorkCategory(){
        return _category;
    }

    public void registerObserverEntrega(Observer observer){
        _observersEntrega.add(observer);
    }

    public void registerObserverRequisicao(Observer observer){
        _observersRequisicao.add(observer);
    }

    public void notifyObserversEntrega(){
        for (Observer obs : _observersEntrega) {
            obs.updateEntrega(this);
        }
	_observersEntrega = new ArrayList<Observer>();
    }

    public void notifyObserversRequisicao(){
        for (Observer obs : _observersRequisicao) {
            obs.updateRequisicao(this);
        }
    }

    public abstract String getOwner();

    public abstract String getDescription();

}
