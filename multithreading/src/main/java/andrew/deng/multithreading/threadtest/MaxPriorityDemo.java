package andrew.deng.multithreading.threadtest;


//However, if you use setMaxPriority(int priority) to lower a group's maximum priority, 
//all threads added to the group prior to that method call keep their original priorities.
//For example, if you add a priority 8 thread to a maximum priority 9 group, and then lower
//that group's maximum priority to 7, the priority 8 thread remains at priority 8. 


//MaxPriorityDemo.java
public class MaxPriorityDemo {
	public static void main(String[] args) {
		ThreadGroup tg = new ThreadGroup("A");
		System.out.println("tg maximum priority = " + tg.getMaxPriority());
		Thread t1 = new Thread(tg, "X");
		System.out.println("t1 priority = " + t1.getPriority());
		t1.setPriority(Thread.NORM_PRIORITY + 1);
		System.out.println("t1 priority after setPriority() = "
				+ t1.getPriority());
		tg.setMaxPriority(Thread.NORM_PRIORITY - 1);
		System.out.println("tg maximum priority after setMaxPriority() = "
				+ tg.getMaxPriority());
		System.out.println("t1 priority after setMaxPriority() = "
				+ t1.getPriority());
		Thread t2 = new Thread(tg, "Y");
		
		
		System.out.println("t2 priority = " + t2.getPriority());
		t2.setPriority(Thread.NORM_PRIORITY);
		System.out.println("t2 priority after setPriority() = "
				+ t2.getPriority());
	}
}