package andrew.deng.multithreading.pauseablethreadpoolexecutor;

import java.util.concurrent.ThreadFactory;

public class Client {

	static class Worker extends Thread {

		private String workName;

		public Worker(String name) {
			super(name);
			// TODO Auto-generated constructor stub
			workName = name;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			System.out.println(workName + " thread is running...");
		}

	}

	static class MyThreadFacotory implements ThreadFactory {
		// Runnable runable;
		//
		// public MyThreadFacotory(Runnable worker) {
		// // TODO Auto-generated constructor stub
		// runable=worker;
		// }

		@Override
		public Thread newThread(Runnable r) {
			// TODO Auto-generated method stub
			return new Thread(r);
		}

	}

	public static void main(String[] args) {
		// new MyThreadFacotory().newThread(new Worker()).start();;

		Scheduler s = new Scheduler();
		s.schedule(new Worker("one"));
		s.schedule(new Worker("two"));
		s.schedule(new Worker("three"));
		s.schedule(new Worker("four"));
		//
		s.pause();

		s.schedule(new Worker("four"));
		
		s.resume();

		s.schedule(new Worker("five"));

		//
		// s.resume();

	}
}
