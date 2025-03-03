import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Approximates PI using the Monte Carlo method. Demonstrates
 * use of Callables, Futures, and thread pools.
 */
public class Pi {
	public static void main(String[] args) throws Exception {
		long total = 0;
		total = new Master().doRun(12000000, Integer.parseInt(args[0]));
		System.out.println("total from Master = " + total);
	}
}


