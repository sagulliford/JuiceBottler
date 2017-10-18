import java.util.ArrayList;
import java.util.List;

public class Plant implements Runnable {
    // How long do we want to run the juice processing
    public static final long PROCESSING_TIME = 5 * 1000;
    
	

    public static void main(String[] args) {
    	 
        try {
            Thread.sleep(PROCESSING_TIME);
        } catch (InterruptedException e) {
            System.err.println("errMsg");
        }

    }

    public final int ORANGES_PER_BOTTLE = 4;
    //private final int NUM_OF_WORKERS = 3;
    private final Thread thread;
   // public static List <WorkerThread> t = new ArrayList<WorkerThread>();
    public int orangesProvided;
    public int orangesProcessed;
    private volatile boolean timeToWork;
    
    

    Plant() {
        orangesProvided = 0;
        orangesProcessed = 0;
        thread = new Thread(this, "Plant");
       // WorkerThread thread = new WorkerThread();
       // t.add(thread);

    }

    public void startPlant() {
        timeToWork = true;
       // for(int i =0; i< NUM_OF_WORKERS; i++){
       // WorkerThread thread = new WorkerThread();
        // t.add(thread);
         //thread.get(i).startWorkerThread();
        thread.start();
            //}
    }

    public void stopPlant() {
        timeToWork = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println(thread.getName() + " stop malfunction");
        }
        //for(int i =0; i< NUM_OF_WORKERS; i++){
              //thread.get(i).stopWorkerThread()
        	//}
    }

    public void run() {
        System.out.print(Thread.currentThread().getName() + " Processing oranges");
        while (timeToWork) {
            processEntireOrange(new Orange());
            orangesProvided++;
            System.out.print(".");
        }
        System.out.println("");
    }

    public void processEntireOrange(Orange o) {
        while (o.getState() != Orange.State.Bottled) {
            o.runProcess();
        }
        orangesProcessed++;
    }

    public int getProvidedOranges() {
        return orangesProvided;
    }

    public int getProcessedOranges() {
        return orangesProcessed;
    }

    public int getBottles() {
        return orangesProcessed / ORANGES_PER_BOTTLE;
    }

    public int getWaste() {
        return orangesProcessed % ORANGES_PER_BOTTLE;
    }
}

