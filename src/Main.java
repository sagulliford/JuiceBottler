
public class Main implements Runnable {
    // How long do we want to run the juice processing
    public static final long PROCESSING_TIME = 5 * 1000;

    public static void main(String[] args) {
        Main p = new Main(); //Starting Plant 1
        Main p2 = new Main(); //Starting Plant 2
        p.startPlant();
        
        // Give the plants time to do work
        try {
            Thread.sleep(PROCESSING_TIME);
        } catch (InterruptedException e) {
            System.err.println("errMsg");
        }
        
        
        // Stop the plant, and wait for it to shutdown
        p.stopPlant();

        // Summarize the results
        System.out.println("Total provided/processed = " + p.getProvidedOranges() + "/" + p.getProcessedOranges());
        System.out.println("Created " + p.getBottles() +
                           ", wasted " + p.getWaste() + " oranges");
    }

    public final int ORANGES_PER_BOTTLE = 4;

    private final Thread worker;
    private int orangesProvided;
    private int orangesProcessed;
    private volatile boolean timeToWork;

    Main(){
        orangesProvided = 0;
        orangesProcessed = 0;
        worker = new Thread(this, "Plant");
    }

    public void startPlant() {
        timeToWork = true;
        worker.start();
    }

    public void stopPlant() {
        timeToWork = false;
        try {
            worker.join();
        } catch (InterruptedException e) {
            System.err.println(worker.getName() + " stop malfunction");
        }
    }

    public void run() {
        System.out.print(Thread.currentThread().getName() + " Processing oranges");
        while (timeToWork) {
            processEntireOrange(new Worker());
            orangesProvided++;
            System.out.print(".");
        }
        System.out.println("");
    }

    public void processEntireOrange(Worker w) {
        while (w.getState() != Worker.State.Bottled) {
            w.runProcess();
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


