package m19.core;

import java.io.Serializable;

public abstract class Rule implements Serializable{

    private int _id;

    public Rule(int id){
        _id = id;
    }

    public int getId(){
        return _id;
    }

    abstract boolean verify(User User, Work work);
}