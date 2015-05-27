package andrew.deng.multithreading.futuretasktest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTestTwo {
	private final static int SIZE = 20000000;

	// private static Random random = new Random();

	public static void main(String[] args) {
		int arr[] = new int[SIZE];
		int serialArr[] = java.util.Arrays.copyOf(arr, SIZE);

		for (int i = 0; i < SIZE; i++) {
			arr[i] = 1;
		}
		int arrOne[] = new int[SIZE / 2];
		System.arraycopy(arr, 0, arrOne, 0, SIZE / 2);
		int arrTwo[] = new int[SIZE / 2];
		System.arraycopy(arr, SIZE / 2, arrTwo, 0, SIZE / 2);

		ExecutorService executor = Executors.newCachedThreadPool();
		Callable WorkerOne = new Worker(arrOne);
		Callable WorkerTwo = new Worker(arrTwo);

		Future workerOneFuture = executor.submit(WorkerOne);
		Future workerTwoFuture = executor.submit(WorkerTwo);

		try {
			long startTime = System.currentTimeMillis();
		

			Integer one = (Integer) workerOneFuture.get();
			Integer two = (Integer) workerTwoFuture.get();
			System.out.println(one + two);
			long endTime = System.currentTimeMillis();

			System.out
					.println("Parallel spend time : " + (endTime - startTime));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long startTime = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < serialArr.length; i++) {
			sum += serialArr[i];
		}
		long endTime = System.currentTimeMillis();
		
		System.out
		.println("Serial spend time : " + (endTime - startTime));


	}

	static class Worker implements Callable {
		int arr[];

		public Worker(int a[]) {
			arr = a;
		}

		public Object call() throws Exception {
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				sum += arr[i];
			}
			return sum;
		}

	}

}
