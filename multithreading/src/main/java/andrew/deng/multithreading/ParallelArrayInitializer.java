package andrew.deng.multithreading;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelArrayInitializer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] longArr = new int[1000];
		long startTime = System.currentTimeMillis();
		parallelAssignValues(longArr);
		long endTime = System.currentTimeMillis();
		System.out.println("parallel method time is: " + (endTime - startTime));

		// for (int i = 0; i < longArr.length; i++) {
		// System.out.print(longArr[i]);
		// }

		Random random = new Random();
		int[] lista = new int[1000];
		long startTimes = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			lista[i] = random.nextInt(500);
		}
		long endTimes = System.currentTimeMillis();
		System.out.println("serial method time is: " + (endTimes - startTimes));

	}

	public static void parallelAssignValues(int[] list) {
		RecursiveAction mainTask = new ParalleAssignValueTask(list);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(mainTask);
	}

	private static class ParalleAssignValueTask extends RecursiveAction {

		private int list[];

		private final int THRESHOLD = 4000;

		public ParalleAssignValueTask(int[] firstHalf) {
			super();
			this.list = firstHalf;
		}

		@Override
		protected void compute() {
			if (list.length < THRESHOLD) {
				Random random = new Random();
				for (int i = 0; i < list.length; i++) {
					list[i] = random.nextInt(500);
				}
				return;
			}

			int[] firstHalf = new int[list.length / 2];
			System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

			int secondHalfLength = list.length - list.length / 2;
			int[] secondHalf = new int[secondHalfLength];

			System.arraycopy(list, list.length / 2, secondHalf, 0,
					secondHalfLength);

			invokeAll(new ParalleAssignValueTask(firstHalf),
					new ParalleAssignValueTask(secondHalf));

		}

	}

}
