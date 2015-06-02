package andrew.deng.multithreading.concurrenthashmaptest;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapExample {
	public static void main(String[] args) {
		ConcurrentNavigableMap concurrentSkipListMap = new ConcurrentSkipListMap();
		concurrentSkipListMap.put("3", "Apple");
		concurrentSkipListMap.put("2", "Ball");
		concurrentSkipListMap.put("1", "Car");
		concurrentSkipListMap.put("5", "Doll");
		concurrentSkipListMap.put("4", "Elephant");

		System.out.println("ceilingEntry-2: "
				+ concurrentSkipListMap.ceilingEntry("2"));
		NavigableSet navigableSet = concurrentSkipListMap.descendingKeySet();
		System.out.println("descendingKeySet: ");
		Iterator itr = navigableSet.iterator();
		while (itr.hasNext()) {
			String s = (String) itr.next();
			System.out.println(s);
		}
		System.out.println("firstEntry: " + concurrentSkipListMap.firstEntry());
		System.out.println("lastEntry: " + concurrentSkipListMap.lastEntry());
		System.out.println("pollFirstEntry: "
				+ concurrentSkipListMap.pollFirstEntry());
		System.out.println("now firstEntry: "
				+ concurrentSkipListMap.firstEntry());
		System.out.println("pollLastEntry: "
				+ concurrentSkipListMap.pollLastEntry());
		System.out.println("now lastEntry: "
				+ concurrentSkipListMap.lastEntry());
	}

}