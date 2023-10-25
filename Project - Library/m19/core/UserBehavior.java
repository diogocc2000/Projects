package m19.core;

import java.io.Serializable;

 public interface UserBehavior extends Serializable{

    public String toString();

    public int getDeadline(Work work);

    public int getMaxRequests();


 }
    