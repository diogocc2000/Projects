package m19.core;



public class CheckNumberOfRequests extends Rule{


    public CheckNumberOfRequests(int id){
        super(id);
    }

    boolean verify(User user, Work work){
        
        if(user.getNumberOpenRequests() < user.getMaxRequests()){
            return true;
        } else return false;
        
    }
}