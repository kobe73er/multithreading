package andrew.deng.timertest;

//Clock1.java
//Type Ctrl+C (or equivalent keystroke combination on non-Windows platform) 
//to terminate
import java.util.*;

public class Clock1 {
	public static void main(String[] args) {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				System.out.println(new Date().toString());
			}
		}, 1000, 1000);
	}
}