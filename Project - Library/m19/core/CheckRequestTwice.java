package m19.core;

import java.util.List;
import java.util.ArrayList;


public class CheckRequestTwice extends Rule{

    public CheckRequestTwice(int id){
        super(id);
    }

    boolean verify(User user, Work work){
        //int workId = work.getWorkId();
        List<Request> requests = user.getUserRequests();
        for (Request r : requests) {
            if(r.isOpen()){
                if(work == r.getWork()){
                    return false;
                }
            }
        }
        return true;
    }
}