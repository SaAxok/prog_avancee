import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * public long doRun(int ntot, int numWorkers) throws InterruptedException,
 * ExecutionException
 * Creates workers to run the Monte Carlo simulation
 * and aggregates the results.
 */
class Master {
    public long doRun(int totalCount, int numWorkers) throws InterruptedException, ExecutionException {

        long startTime = System.currentTimeMillis();

        // Create a collection of tasks
        List<Callable<Long>> tasks = new ArrayList<Callable<Long>>(); // collection de tache qui renvoie un résultat
        for (int i = 0; i < numWorkers; ++i) {
            tasks.add(new Worker(totalCount));
        }

        // Run them and receive a collection of Futures
        ExecutorService exec = Executors.newFixedThreadPool(numWorkers);
        List<Future<Long>> results = exec.invokeAll(tasks);
        long total = 0;

        // Assemble the results.
        for (Future<Long> f : results) {
            // Call to get() is an implicit barrier. This will block
            // until result from corresponding worker is ready.
            total += f.get();
        }
        double pi = 4.0 * total / totalCount / numWorkers;

        long stopTime = System.currentTimeMillis();

        System.out.println("\nPi : " + pi);
        System.out.println("Error: " + (Math.abs((pi - Math.PI)) / Math.PI) + "\n");

        System.out.println("Ntot: " + totalCount / numWorkers);
        System.out.println("Available processors: " + numWorkers);
        System.out.println("Time Duration (ms): " + (stopTime - startTime) + "\n");

        System.out.println((Math.abs((pi - Math.PI)) / Math.PI) + " " + totalCount * numWorkers + " " + numWorkers + " "
                + (stopTime - startTime));

        try {
            FileWriter writer = new FileWriter("out-pi-speedup-G26.txt", true);
            writer.write((Math.abs(pi - Math.PI) / Math.PI) + ", " + totalCount / numWorkers + ", " + numWorkers + ", "
                    + (stopTime - startTime) + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        exec.shutdown();
        return total;
    }
}