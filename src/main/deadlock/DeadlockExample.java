package main.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {

    private final Lock lock1 = new ReentrantLock(true);
    private final Lock lock2 = new ReentrantLock(true);

    public void worker1() {
        lock1.lock();
        System.out.println("Worker1 acquires the lock1...");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock2.lock();
        System.out.println("Worker1 acquires the lock2...");

        lock1.unlock();
        lock2.unlock();
    }

    private void worker2() {
        lock2.lock();
        System.out.println("Worker2 acquires the lock2...");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock1.lock();
        System.out.println("Worker2 acquires the lock1...");

        lock1.unlock();
        lock2.unlock();
    }

    public static void main(String[] args) {
        DeadlockExample deadlockExample = new DeadlockExample();

        new Thread(deadlockExample::worker1, "worker1").start();
        new Thread(deadlockExample::worker2, "worker2").start();
    }

}
