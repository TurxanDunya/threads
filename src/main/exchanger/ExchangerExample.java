package main.exchanger;

import java.util.concurrent.Exchanger;

class FirstThread implements Runnable {

    private int counter;
    private final Exchanger<Integer> exchanger; //We will share integer

    FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            counter++;
            System.out.println("First thread incremented the counter " + counter);

            try {
                counter = exchanger.exchange(counter);
                System.out.println("Second thread get the counter: " + counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondThread implements Runnable {

    private int counter;
    private final Exchanger<Integer> exchanger; //We will share integer

    SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            counter--;
            System.out.println("Second thread incremented the counter " + counter);

            try {
                counter = exchanger.exchange(counter);
                System.out.println("Second thread get the counter: " + counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        FirstThread firstThread = new FirstThread(exchanger);
        SecondThread secondThread = new SecondThread(exchanger);

        new Thread(firstThread).start();
        new Thread(secondThread).start();
    }
}
