package andrew.deng.multithreading.cyclicBarrierTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest2 {

	static CyclicBarrier cb;

	public static void main(String[] args) {
		cb = new CyclicBarrier(5, new Runnable() {
			public void run() {
				System.out.println("All worker thread finished!");
			}

		});
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executor.execute(new Workder());
		}

	}

	static class Workder implements Runnable {

		public void run() {
			System.out.println("current Thread: "
					+ Thread.currentThread().getName());
			try {
				cb.await();

				System.out.println(Thread.currentThread().getName()
						+ " continued.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}

		}

	}

}
