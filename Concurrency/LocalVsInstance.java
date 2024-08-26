package Concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * local variable - thread stack
 * instance variable - heap, threads share the heap
 */
public class LocalVsInstance {
    public static void main(String[] args) {
        Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();
    }
}


class Countdown {

    private int i; // changing this to local variable would result in both the threads 
    // printing from 10 to 1
    private final Lock lock = new ReentrantLock(); 
    /*
     * The constructor for this class accepts an optional fairness parameter.
     * When set true, under contention, locks favor granting access to the longest-waiting thread.
     * i.e, prevents starvation
     */


    // making this syncronized would also make both threads print from 10 to 1 
    public void doCountdown() {
        String color;

        switch(Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }

        lock.lock(); // makes both threads print from 10 to 1
        for(i = 10; i > 0; i--) {
            System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
        }
        lock.unlock();
    }

    /*
    op
    Thread 2: i =10
    Thread 2: i =9 
    Thread 1: i =10
    Thread 2: i =8
    Thread 1: i =7
    Thread 2: i =6
    Thread 1: i =5
    Thread 1: i =3
    Thread 2: i =4
    Thread 1: i =2
    Thread 2: i =1
    */
}

class CountdownThread extends Thread {
    private Countdown threadCountdown;

    public CountdownThread(Countdown countdown) {
        threadCountdown = countdown;
    }

    public void run() {
        threadCountdown.doCountdown();
    }
}