
import java.util.ArrayList;
import java.util.List;

public class Worker {
    public enum State {
        Fetched(),
        Peeled(),
        Squeezed(),
        Bottled(),
        Processed();
       
        }
    List<State> state = new ArrayList<State>();  
    private static final int STATE_PROCESS = 5;
    
    
    
    public Worker() {
    	for (int i = 0; i < STATE_PROCESS; i++){
    		getWork();
            doWork();
            sendWork();
        }
    }


    public void runProcess() {
        // Don't attempt to process an already completed orange
        if (state == State.Processed) {
            throw new IllegalStateException("This orange has already been processed");
        }
        doWork();
        
    }
    
    private synchronized void getWork(){
    	((Thread) state).start();
    	
    }
    private void doWork() {
        // Sleep for the amount of time necessary to do the work
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Incomplete orange processing, juice may be bad");
        }
    	
    }
    private synchronized void sendWork(){
    	try {
            notify();
        } catch(Exception e) {
            System.err.println("Couldn't send work on to next process.");
        }
    	
    }
    public getState(){
    	return state;
    }
}
