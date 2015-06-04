package andrew.deng.threadgroup;

//EnumThreads.java
public class EnumThreads {
	public static void main(String[] args) {
		// Find system thread group
		ThreadGroup system = null;
		ThreadGroup tg = Thread.currentThread().getThreadGroup();
		while (tg != null) {
			System.out.println("tg:"+tg);
			system = tg;
			tg = tg.getParent();
		}
		// Display a list of all system and application threads, and their
		// daemon status
		if (system != null) {
			Thread[] thds = new Thread[system.activeCount()];
			int nthds = system.enumerate(thds);
			for (int i = 0; i < nthds; i++)
				System.out.println(thds[i] + " " + thds[i].isDaemon());
		}
	}
}