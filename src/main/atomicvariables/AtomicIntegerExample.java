package main.atomicvariables;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Instead of synchronization keyword, there are other solutions like Atomic Variables
 */
public class AtomicIntegerExample {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private static void increment() {
        for (int i = 0; i < 100; ++i)
            counter.getAndIncrement();
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(AtomicIntegerExample::increment);
        Thread t2 = new Thread(AtomicIntegerExample::increment);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter is: " + counter);
    }

}