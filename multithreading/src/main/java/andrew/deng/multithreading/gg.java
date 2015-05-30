package andrew.deng.multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class gg {

	public void t() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Runnable task = new Runnable() {
			public void run() {
				throw new RuntimeException("foo");
			}
		};

		Future<?> future = executor.submit(task);
		try {
			try {
				future.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ExecutionException e) {
			Exception rootException = (Exception) e.getCause();
		}
	}
}
