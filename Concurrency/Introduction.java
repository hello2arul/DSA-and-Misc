package Concurrency;

/*
 * https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html
 */
class Introduction {
    public static void main(String[] args) {
        System.out.println("Hello from main thread");

        Thread anotherThread = new AnotherThread();
        anotherThread.setName("AnotherThread");
        anotherThread.run(); // doesn't create a new thread, instead is invoked my the Main thread
        anotherThread.start(); // creates a new thread and invokes run()

        try {
            anotherThread.start(); // This will throw IllegalThreadStateException
        } catch (IllegalThreadStateException e) {
            System.out.println("Cannot start a thread more than once.");
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    anotherThread.join();
                    /*
                     * join() waits for anotherThread to complete before
                     * executing further.
                     */
                } catch (InterruptedException e) {
                    // TODO: handle exception
                }
                System.out.println("Hello from Anonymous thread.");
            }
        }.start();

        Thread runnableThread = new Thread(new RunnableThread());
        runnableThread.start();
        // anotherThread.interrupt();

        System.out.println("Hello again from main thread");

        /*
         * the output order of the print is based on various factors like priority,
         * thread execution time etc., and doesn't have to be the same.
         * 
         */
    }
}

class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from " + currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            System.out.println("Interrupted by another thread");
        }
    }
}

class RunnableThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Run from RunnableThread implementing Runnable interface");
    }
}