package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPools {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(threadA);
        executorService.execute(threadB);
        executorService.shutdown();
        /*
        op could be in any order as threads run concurrently
        if 2 is changed to 1, they will be run orderly
        */ 
    }
}

class ThreadA extends Thread {
    @Override
    public void run() {
        System.out.println("running ThreadA");
    }
}

class ThreadB extends Thread {
    @Override
    public void run() {
        System.out.println("running ThreadB");
    }
}
