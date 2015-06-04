package andrew.deng.threadgroup;

public class TryToSuspendAllThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadGroup threadGroupOne = new ThreadGroup("tg-one");
		Thread threadOne = new Thread(threadGroupOne, "threadOne") {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				System.out.println("threadOne is running...");
			}

		};
		Thread threadTwo = new Thread(threadGroupOne, "threadTne") {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				System.out.println("threadTwo is running...");
			}
		};

		threadOne.start();
		threadTwo.start();

		ThreadGroup ins = Thread.currentThread().getThreadGroup();
//		
//		ins.suspend();
//		ins.resume();

		// ins.list();
		System.out.println("Active count is:" + ins.activeCount());
		System.out.println("Current thread is "+ Thread.currentThread());

	}

}
