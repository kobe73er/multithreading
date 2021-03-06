package andrew.deng.multithreading.futuretasktest;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;



public class CallableTest1 {

    public static void main(String[] args) 
        throws ExecutionException, InterruptedException{
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        //创建有返回值的任务
        Callable c1 = new MyCallable();
        
        Callable c2 = new MyCallable();
        //执行任务并获取Future对象 
        Future f1 = pool.submit(c1);
        
        Future f2=pool.submit(c2);
        
        System.out.println(f2.get());
        // 输出结果
        System.out.println(f1.get()); 
        //关闭线程池 
        pool.shutdown(); 
    }
    
    static class MyCallable implements Callable {

        public Integer call() throws Exception {
            int sum    = 0;
            // 执行任务
            for (int i=0; i<100; i++)
                sum += i;
            //return sum; 
            return Integer.valueOf(sum);
        } 
    }
}