package main.producerconsumermodel;

import java.util.ArrayList;
import java.util.List;

/**
 * ATTENTION! Infinite loop will happen when you run application!
 */
public class Processor {

    private final List<Integer> integerList = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void produce() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (integerList.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for removing items...");
                    lock.wait(); //If you noticed we used lock object's lock
                } else {
                    System.out.println("Adding: " + value);
                    integerList.add(value);
                    value++;
                    lock.notify(); //First iteration it will be not needed
                }

                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (integerList.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for adding items...");
                    value = 0;
                    lock.wait();
                } else {
                    System.out.println("Removing: " + integerList.remove(integerList.size() - 1));
                    lock.notify(); //First iteration it will be not needed
                }

                Thread.sleep(500);
            }
        }
    }

    public static void main(String[] args) {
        Processor processor = new Processor();

        Thread t1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
