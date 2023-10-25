package m19.core;

public class Normal implements UserBehavior {

    public String toString(){
        return "NORMAL";
    }

    public int getDeadline(Work work){
        int numberOfCopies = work.getTotalNumberOfCopies();
        if (numberOfCopies == 1){
            return 3;
        }
        else if (numberOfCopies <= 5){
            return 8;
        }
        else{
            return 15;
        }
    }
    public int getMaxRequests(){
        return 3;
    }
}