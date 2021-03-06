package andrew.deng.multithreading;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentModificationExceptionTest {

    private static HashSet<Integer> hashset = new HashSet<Integer>();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new AddNumberToSetTask());
        executor.execute(new TraverseSetTask());

        executor.shutdown();
    }

    private static class AddNumberToSetTask implements Runnable {

        public void run() {
            while (true) {
                synchronized (hashset) {
                    hashset.add((int) (Math.random() * 100));
                    System.out.println(hashset);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static class TraverseSetTask implements Runnable {

        public void run() {
            while (true) {
                synchronized (hashset) {
                    for (Integer item : hashset) {
                        System.out.println(" current item: " + item);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }

    }

}
