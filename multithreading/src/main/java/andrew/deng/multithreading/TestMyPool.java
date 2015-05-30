package andrew.deng.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;

import andrew.deng.multithreading.TestMyPool.Worker;

public class TestMyPool {

	public static class Worker extends Thread {

		@Override
		public void run() {
			System.out.println("Worker thread...");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PausableExecutor pe = new PausableExecutor(5, new ThreadFactory() {

			@Override
			public Thread newThread(Runnable r) {
				return new Worker();
			};
		});

		List<Worker> workerLst = new ArrayList<Worker>();

		for (int i = 0; i < 10; i++) {
			workerLst.add(new Worker());
		}
		List<Callable<Object>> callables = new ArrayList<Callable<Object>>();
		callables.add(new Callable<Object>() {

			@Override
			public Object call() throws Exception {
				new Worker();
				return null;
			}
		});

		try {
			pe.invokeAll(callables);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pe.shutdown();
		}

	}
}
