import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

    public void incrementCounter() {
        // Acquire the lock
        lock.lock();

        try {
            // Critical section: Increment the counter
            counter++;
            System.out.println(Thread.currentThread().getName() + ": Counter incremented to " + counter);
        } finally {
            // Always release the lock in the finally block
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();

        // Create multiple threads to increment the counter concurrently
        Thread thread1 = new Thread(example::incrementCounter, "Thread 1");
        Thread thread2 = new Thread(example::incrementCounter, "Thread 2");
        Thread thread3 = new Thread(example::incrementCounter, "Thread 3");

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}