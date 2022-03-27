package main.synchronization;

public class SynchronizationExample {
    private static int counter = 0;

    //synchronized keyword makes us sure that this method will be called once a time by a thread.
    private static synchronized void increment() {
        counter++;
    }

    private static void process() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; ++i)
                increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; ++i)
                increment();
        });

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

    public static void main(String[] args) {
        process();
    }
}
