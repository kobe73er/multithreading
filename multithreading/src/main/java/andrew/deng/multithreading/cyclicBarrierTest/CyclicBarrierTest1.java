package andrew.deng.multithreading.cyclicBarrierTest;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest1 {

	static CyclicBarrier cb = new CyclicBarrier(5);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executor.execute(new Worker());
		}

		executor.shutdown();
	}

	private static class Worker implements Runnable {

		public void run() {
			System.out.println(Thread.currentThread().getName()
					+ " is waiting.... ");
			try {
				cb.await();

				System.out.println(Thread.currentThread().getName()
						+ " countinue running.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
