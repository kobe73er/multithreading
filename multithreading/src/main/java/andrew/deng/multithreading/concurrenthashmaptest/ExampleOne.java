package andrew.deng.multithreading.concurrenthashmaptest;

import java.util.HashMap;
import java.util.UUID;

public class ExampleOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final HashMap<String, String> map = new HashMap<String, String>(2);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							map.put(UUID.randomUUID().toString(), "");
						}
					}, "ftf" + i).start();
				}
			}
		}, "ftf");
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}