package main.reallifeexamples.diningphilosophersproblem;

import java.util.Random;

public class Philosopher implements Runnable {

    private final int id;
    private volatile boolean full;
    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;
    private final Random random;
    private int eatingCounter;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.random = new Random();
    }

    @Override
    public void run() {
        //After eating a lot (1000) then we will terminate the given thread
        try {
            while (!full) {
                think();

                if (leftChopstick.pickUp(this, State.LEFT)) {
                    if (rightChopstick.pickUp(this, State.RIGHT)) {
                        eat();
                        rightChopstick.putDown(this, State.RIGHT);
                    }

                    leftChopstick.putDown(this, State.LEFT);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void think() throws InterruptedException {
        System.out.println(this + " is thinking...");
        Thread.sleep(random.nextInt(1000));
    }

    public void eat() throws InterruptedException {
        System.out.println(this + " is thinking...");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public int getEatingCounter() {
        return eatingCounter;
    }

    public void setEatingCounter(int eatingCounter) {
        this.eatingCounter = eatingCounter;
    }

    @Override
    public String toString() {
        return "Philosopher: " + id;
    }

}