package main.intrinsicLock;

public class IntrinsicLockExample {

    private static int counter1 = 0;
    private static int counter2 = 0;

    //Usually it is not a good practice to use synchronized keyword in method signature
    private static void increment1() {
        synchronized (IntrinsicLockExample.class) {
            counter1++;
        }
    }

    private static void increment2() {
        synchronized (IntrinsicLockExample.class) {
            counter2++;
        }
    }


    private static void process() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; ++i)
                increment1();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; ++i)
                increment2();
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter1 is: " + counter1);
        System.out.println("Counter2 is: " + counter2);
    }

    public static void main(String[] args) {
        process();
    }
}
