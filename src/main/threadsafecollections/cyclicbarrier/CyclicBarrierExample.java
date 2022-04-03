package main.threadsafecollections.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable {

    private final int id;
    private final CyclicBarrier barrier;

    public Worker(int id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.printf("Thread with ID %d is running%n", id);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            //When all the threads call await(), this is when the "barrier is broken"
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("After the await()...");
    }
}

public class CyclicBarrierExample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);

        //After five threads reach to await(), run your thread
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("All tasks have been finished...");
        });

        for (int i = 0; i < 5; ++i) {
            service.execute(new Worker(i, cyclicBarrier));
        }

        service.shutdown();
    }
}
