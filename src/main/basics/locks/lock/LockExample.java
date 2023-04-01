package main.basics.locks.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    private static int counter = 0;
    private static final Lock lock = new ReentrantLock();

    private static void increment() {
        lock.lock();

        try {
            for (int i = 0; i < 1000; i++) {
                counter++;
            }
        } finally { //We have to use finally, in case of maybe exception happens while incrementing
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(LockExample::increment);
        Thread t2 = new Thread(LockExample::increment);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter);
    }
}
