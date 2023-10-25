package m19.core;



public class CheckWorkIsReference extends Rule{

    public CheckWorkIsReference(int id){
        super(id);
    }

    boolean verify(User user, Work work){

        if(work.getWorkCategory() == Category.valueOf("REFERENCE")){
            return false;
        } else return true; 
    }
}