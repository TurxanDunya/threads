package main.basics.daemonThread;

/**
 * When normal worker stops working, daemon worker stops too.
 */
class DaemonWorker implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Daemon thread is running...");
        }
    }
}

class NormalWorker implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(3001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Normal thread finishes execution...");
    }
}

public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread daemonWorker = new Thread(new DaemonWorker());
        Thread normalWorker = new Thread(new NormalWorker());

        daemonWorker.setDaemon(true);

        daemonWorker.start();
        normalWorker.start();
    }
}
