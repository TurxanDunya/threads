package main.basics.locks.intrinsicLock;

// An intrinsic lock (or monitor) is an implicit internal entity associated with each instance of objects.
// The intrinsic lock enforces exclusive access to an object's state.
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
            for (int i = 0; i < 10000; ++i)
                increment1();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; ++i)
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
