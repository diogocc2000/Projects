package m19.core;



public class CheckUserIsSuspended extends Rule{


    public CheckUserIsSuspended(int id){
        super(id);
    }

    boolean verify(User user, Work work){
        if(user.isActive()){
            return true;
        } else return false;   
        
    }




}