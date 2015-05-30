package andrew.deng.multithreading.threadfactory;

import java.util.concurrent.ThreadFactory;

public class FirstTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		One oneIns = new One();
		oneIns.newThread(new SimpleTask()).start();;
		
	}

	static class One implements ThreadFactory {

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);

			return t;
		}

	}

	static class SimpleTask implements Runnable {

		@Override
		public void run() {
			System.out.println("simple task executing...");

		}

	}

}
