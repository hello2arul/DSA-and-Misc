# Multithreading in Java

Multithreading is the concurrent execution of two or more threads in a program. It allows for parallelism, improving performance and resource utilization. Each thread runs independently but shares the same memory space.

## Creating Threads in Java

There are two primary ways to create a thread in Java:

1. **Extending the `Thread` class which is also implements `Runnable` interface**
2. **Implementing the `Runnable` interface**

### 1. Extending the `Thread` Class

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start(); // Starts the thread
    }
}
```

### 2. Implementing the `Runnable` Interface

```
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable thread is running");
    }
}

public class RunnableExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}
```


## Summary of Important Classes and Interfaces

| Class/Interface       | Description                                      |
|-----------------------|--------------------------------------------------|
| `Thread`              | Represents a thread of execution.                |
| `Runnable`            | A functional interface for defining a task.      |
| `synchronized`        | Keyword to prevent concurrent access to methods. |
| `ReentrantLock`       | Provides a more flexible locking mechanism.      |
| `ExecutorService`     | Manages a pool of threads for task execution.    |


- Use Atomic when you need simple operations that can be performed atomically without locks.
- Use Volatile when sharing a flag or status variable among threads without needing compound actions.
- Use Synchronized when protecting a block of code or method from concurrent access by multiple threads.
- Mutex (ReentrantLock): Ensures exclusive access to a resource.
- Semaphore: Controls access to a resource pool, allowing multiple threads to access a specified number of resources concurrently.

### Achieving Multithreading on a Single-Core CPU
Multithreading on a single-core CPU is achieved through time-slicing. The operating system rapidly switches between threads, giving the illusion of concurrent execution by managing context switches.

## Useful links
- https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html