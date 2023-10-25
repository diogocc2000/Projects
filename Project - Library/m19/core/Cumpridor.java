package m19.core;


public class Cumpridor implements UserBehavior {
    
    public String toString(){
        return "CUMPRIDOR";
    }

    public int getDeadline(Work work){
        int numberOfCopies = work.getTotalNumberOfCopies();
        if (numberOfCopies == 1){
            return 8;
        }
        else if (numberOfCopies <= 5){
            return 15;
        }
        else{
            return 30;
        }
    }

    public int getMaxRequests(){
        return 5;
    }
}