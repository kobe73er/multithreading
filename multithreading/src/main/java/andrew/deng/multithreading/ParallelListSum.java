package andrew.deng.multithreading;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelListSum {
	static double list[] = new double[9000000];

	static {
		fullFillList();
	}

	public static void main(String[] args) {
		parallelSum(list);
	}

	public static void fullFillList() {
		Random random = new Random();

		for (int i = 0; i < list.length; i++) {
			list[i] = random.nextDouble();
		}
	}

	public static double parallelSum(double[] list) {
		RecursiveTask mainTask = new parallelSumTask(list);
		ForkJoinPool fjp = new ForkJoinPool();
		fjp.invoke(mainTask);
		return 0;

	}

	private static class parallelSumTask extends RecursiveTask {

		private final int THRSHOLD = 500;

		private double[] list;

		public parallelSumTask(double[] list) {
			super();
			this.list = list;
		}

		@Override
		protected Object compute() {

			if (list.length < THRSHOLD) {
				double sum = 0;
				for (int i = 0; i < list.length; i++) {
					sum += list[i];
				}
			}

			invokeAll();

			return THRSHOLD;
		}

	}

}
