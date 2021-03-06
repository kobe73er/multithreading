package andrew.deng.multithreading.countdownlatchTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest2 {
	static CountDownLatch latch = new CountDownLatch(10);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {
			executor.execute(new ChildTask());
		}
		executor.shutdown();
		try {
			System.out.println("main thread start to wait...");
			latch.await();
			System.out.println("main thread end wait");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static class ChildTask implements Runnable {

		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
			System.out.println(latch.getCount());
			latch.countDown();
			

		}

	}

}
