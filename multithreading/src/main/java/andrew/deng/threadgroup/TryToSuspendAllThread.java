package andrew.deng.threadgroup;

import java.io.IOException;

public class TryToSuspendAllThread {

	public static void main(String[] args) throws InterruptedException {

		ThreadGroup system = null;
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		while (tg != null) {
			System.out.println("tg:" + tg);
			system = tg;
			tg = tg.getParent();
		}
		
		
		final Thread[] tlist = new Thread[50];
		final ThreadGroup[] tglist = new ThreadGroup[50];
		ThreadGroup threadGroupOne = new ThreadGroup(system, "tg-one");
		Thread threadOne = new Thread(threadGroupOne, "threadOne") {

			@Override
			public void run() {
				int i = 0;
				while (true) {
					System.out.println("threadOne is running... i=" + i++);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		};

		threadOne.start();

		Thread threadForSystemThread = new Thread(system,
				"threadForSystemThread") {

			@Override
			public void run() {
				System.out
						.println("thread for system thread waiting for input...");
				try {
					System.in.read();
					for (int i = 0; i < tglist.length; i++) {
						if (tglist[i] == null) {
							continue;
						}
						System.out.println("--resuming " + tglist[i]);

						tglist[i].resume();
						System.out.println("thread resumed "
								+ tglist[i].getName());
					}

				} catch (IOException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
		threadForSystemThread.start();

		Thread.sleep(5000);

		for (int i = 0; i < system.enumerate(tglist, false); i++) {
			System.out.println("@@ " + tglist[i]);
		}
	
		for (int i = 0; i < system.enumerate(tglist, false); i++) {
			System.out.println("## " + tglist[i]);
			tglist[i].suspend();
		}
	

		System.out.println("Current thread is " + Thread.currentThread());
		try {
			threadOne.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
