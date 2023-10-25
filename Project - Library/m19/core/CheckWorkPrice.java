package m19.core;



public class CheckWorkPrice extends Rule{

    public CheckWorkPrice(int id){
        super(id);
    }

    boolean verify(User user, Work work){
        if(user.getBehavior().toString().equals("CUMPRIDOR"))
            return true;
        
        else if(work.getWorkPrice() > 25){
            return false;
        } else return true;
    }
}