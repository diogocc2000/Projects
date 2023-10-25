package m19.core;

public class Faltoso implements UserBehavior {
    
    public String toString(){
        return "FALTOSO";
    }

    public int getDeadline(Work work){
        int numberOfCopies = work.getTotalNumberOfCopies();
        if (numberOfCopies == 1){
            return 2;
        }
        else if (numberOfCopies <= 5){
            return 2;
        }
        else{
            return 2;
        }
    }

    public int getMaxRequests(){
        return 1;
    }
}