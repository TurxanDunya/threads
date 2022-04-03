package main.producerconsumermodelwithlocks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
    private final Lock lock = new ReentrantLock(); //It is our lock

    /**
     * Returns a new Condition instance that is bound to this Lock instance.
     * We could not use wait() and notify() methods on lock.
     * Instead of them, Condition class supplies us await() and  methods
     */
    private final Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println("Producer method...");
        condition.await(); //wait()
        System.out.println("Again producer method...");
    }

    public void consume() throws InterruptedException {
        //We have to make sure that we start with the producer() and it will acquire lock() first
        Thread.sleep(2000);
        lock.lock();
        System.out.println("Consumer method...");
        Thread.sleep(3000);
        condition.signal(); //notify()
        lock.unlock();
    }
}

public class ProducerConsumerWithLocks {

    public static void main(String[] args) {
        Worker worker = new Worker();

        Thread t1 = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
