package main.withThreadClass;

class FirstRunner extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++)
                System.out.println("FirstRunner: " + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondRunner extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++)
                System.out.println("SecondRunner: " + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SimpleThreadWithThread {
    public static void main(String[] args) {
        Thread firstThread = new FirstRunner();
        Thread secondThread = new SecondRunner();

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join(); //Waits for first thread to die
            secondThread.join(); //Waits for second thread to die
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //After both threads finish their job, this statement will be executed
        System.out.println("Both thread completed their tasks");
    }
}
