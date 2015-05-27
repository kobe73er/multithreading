package andrew.deng.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCooperationWithWaitAndNotify {

	private static Account account = new Account();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new WithDrawTask());
		executor.execute(new DepositTask());

		executor.shutdown();
	}

	private static class WithDrawTask implements Runnable {

		public void run() {
			while (true) {
				account.withdraw((int) (Math.random() * 10));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private static class DepositTask implements Runnable {

		public void run() {
			while (true) {
				account.deposit((int) (Math.random() * 10));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class Account {

		private int balance;

		public synchronized void withdraw(int amount) {
			try {
				while (amount > balance) {
					this.wait();
				}
				System.out.print(" before withdraw: " + balance);
				System.out.print(" withdraw: " + amount);
				balance -= amount;
				System.out.println(" after withdraw: " + balance);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
			}

		}

		synchronized public void deposit(int amount) {
			try {
				System.out.print(" before deposit: " + balance);
				System.out.print(" deposit: " + amount);
				balance += amount;
				System.out.println(" after deposit: " + balance);
				notifyAll();

			} finally {
			}
		}
	}

}
