package main.basics.executors.fixedthreadexecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {

    private final int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task with id " + id + " is in work - thread is: " +
                Thread.currentThread().getId());
        long duration = (long) (Math.random() * 5);

        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class FixedThreadExecutorExample {
    public static void main(String[] args) {

        //It is a single thread that will execute the tasks sequentially
        //So one after another
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task(i+1));
        }

        //Be careful! We have to shut down the executor
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ignored) {
        }
    }
}
