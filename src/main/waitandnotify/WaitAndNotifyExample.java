package main.waitandnotify;

public class WaitAndNotifyExample {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producing...");
            System.out.println("Produce method waits for consume...");
            wait();
            System.out.println("Producing has completed now");
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this) {
            System.out.println("Started consuming...");
            notify();
        }
    }

    public static void main(String[] args) {
        WaitAndNotifyExample example = new WaitAndNotifyExample();

        Thread t1 = new Thread(() -> {
            try {
                example.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                example.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }

}
