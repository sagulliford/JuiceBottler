
public class WorkerThread extends Thread {
    private static final int NUM_THREADS = 5;
    private volatile boolean working;
    
    public static void main(String[] args) {
    	(new WorkerThread()).start();
        }
    
    public void startWorkerThread(){
    	working = true;
    	
    }
    public void stopWorkerThread(){
    	working = false;
    	
    }
	}
        // Wait for the threads to complete before exiting
       

	 




