package andrew.deng.multithreading;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentModificationExceptionTestWithSynchronizedSet {
	private static HashSet<Integer> hashset = new HashSet<Integer>();
	private static Set<Integer> hset = Collections.synchronizedSet(hashset);

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new AddNumberToSetTask());
		executor.execute(new TraverseSetTask());

		executor.shutdown();
	}

	private static class AddNumberToSetTask implements Runnable {

		public void run() {
			while (true) {
				hset.add((int) (Math.random() * 100));
				System.out.println(hset);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private static class TraverseSetTask implements Runnable {

		public void run() {
			while (true) {
				for (Integer item : hset) {
					System.out.println(" current item: " + item);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}

	}
}
