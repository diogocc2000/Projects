package m19.core;



public class CheckCopyIsAvailable extends Rule{

    public CheckCopyIsAvailable(int id){
        super(id);
    }

    boolean verify(User user, Work work){
        if (work.getNumberOfCopies() > 0){
            return true;
        } else return false;
    }
}