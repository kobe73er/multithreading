package andrew.deng.multithreading.forkjoinpooltest;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FirstTest {

	private static int[] list = new int[10000];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10000; i++) {
			list[i] = i;
		}

		FirstTest f = new FirstTest();
		f.parallelSum(list);

	}

	public static int parallelSum(int[] list) {
		RecursiveTask<Integer> task = new ParallelSum(list);
		ForkJoinPool pool = new ForkJoinPool();
		return pool.invoke(task);

	}

	static class ParallelSum extends RecursiveTask {
		private int list[];
		private final int THOLD = 500;

		public ParallelSum(int[] list) {
			super();
			this.list = list;
		}

		@Override
		protected Integer compute() {
			int sum = 0;
			if (list.length < THOLD) {

				for (int i = 0; i < THOLD; i++) {
					sum = sum + list[i];
				}

			} else {

				int[] leftList = new int[list.length / 2];
				int secondHalfLength = list.length - list.length / 2;
				int[] rightList = new int[secondHalfLength];

				int[] firstHalf = new int[list.length / 2];
				System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

				int[] secondHalf = new int[secondHalfLength];
				System.arraycopy(list, list.length / 2, rightList, 0, secondHalfLength);
				RecursiveTask<Integer> leftTask = new ParallelSum(leftList);
				RecursiveTask<Integer> rightTask = new ParallelSum(rightList);

				leftTask.fork();
				rightTask.fork();

				int leftResult = leftTask.join().intValue();
				int rightResult = rightTask.join().intValue();
				sum = leftResult + rightResult;

			}
			return sum;

		}

	}

}
