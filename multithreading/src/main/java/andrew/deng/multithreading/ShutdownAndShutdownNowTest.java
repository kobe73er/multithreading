package andrew.deng.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutdownAndShutdownNowTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.submit(new Runnable() {

			public void run() {
				while (true) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("interrupted");
						break;
					}
				}
			}
		});

		executor.shutdownNow();
		if (!executor.awaitTermination(100, TimeUnit.MICROSECONDS)) {
			System.out
					.println("Still waiting after 100ms: calling System.exit(0)...");
			System.exit(0);
		}
		System.out.println("Exiting normally...");
	}

}
