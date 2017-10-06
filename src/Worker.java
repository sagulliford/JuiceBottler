
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
    List<State> workers = new ArrayList<State>();  
    private static final int STATE_PROCESS = 5;
    
    State getNext() {
          
        }
    

    private State state;

    public Worker() {
    	for (int i = 0; i < STATE_PROCESS; i++){
    		state = State.Fetched;
            doWork();
        }
    }

    public State getState() {
        return state;
    }

    public void runProcess() {
        // Don't attempt to process an already completed orange
        if (state == State.Processed) {
            throw new IllegalStateException("This orange has already been processed");
        }
        doWork();
        state = state.getNext();
    }

    private void doWork() {
        // Sleep for the amount of time necessary to do the work
        try {
            Thread.sleep(state.timeToComplete);
        } catch (InterruptedException e) {
            System.err.println("Incomplete orange processing, juice may be bad");
        }
    }
}
