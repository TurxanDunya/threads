package main.createthread.withRunnable;

class FirstRunner implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++)
            System.out.println("FirstRunner: " + i);
    }
}

class SecondRunner implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++)
            System.out.println("SecondRunner: " + i);
    }
}

public class SimpleThreadWithRunnable {
    public static void main(String[] args) {
        Thread firstThread = new Thread(new FirstRunner());
        Thread secondThread = new Thread(new SecondRunner());

        firstThread.start();
        secondThread.start();

        System.out.println("Main thread has ended");
    }
}
