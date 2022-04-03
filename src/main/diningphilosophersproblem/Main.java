package main.diningphilosophersproblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static main.diningphilosophersproblem.Constants.NUMBER_OF_CHOPSTICKS;
import static main.diningphilosophersproblem.Constants.NUMBER_OF_PHILOSOPHERS;
import static main.diningphilosophersproblem.Constants.SIMULATION_RUNNING_TIME;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philosopher[] philosophers = null;
        Chopstick[] chopsticks = null;

        try {
            philosophers = new Philosopher[NUMBER_OF_PHILOSOPHERS];
            chopsticks = new Chopstick[NUMBER_OF_CHOPSTICKS];

            executorService = Executors.newFixedThreadPool(NUMBER_OF_PHILOSOPHERS);

            for (int i = 0; i < NUMBER_OF_CHOPSTICKS; i++) {
                chopsticks[i] = new Chopstick(i);
            }

            for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
                philosophers[i] = new Philosopher(
                        i, chopsticks[i], chopsticks[(i + 1) % NUMBER_OF_CHOPSTICKS]);

                executorService.execute(philosophers[i]);
            }

            Thread.sleep(SIMULATION_RUNNING_TIME);

            for (Philosopher philosopher : philosophers) {
                philosopher.setFull(true);
            }
        } finally {
            executorService.shutdown();

            while (!executorService.isTerminated()) {
                Thread.sleep(1000);
            }

            for (Philosopher philosopher : philosophers) {
                System.out.println(
                        philosophers + " has eaten " + philosopher.getEatingCounter() + " times!");
            }
        }
    }
}
