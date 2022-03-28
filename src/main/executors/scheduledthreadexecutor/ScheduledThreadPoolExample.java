package main.executors.scheduledthreadexecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarketUpdater implements Runnable {

    @Override
    public void run() {
        System.out.println("Updating and downloading stock related data from web...");
    }
}

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        //We need only one thread, because of this we created thread pool with size 1
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(
                new StockMarketUpdater(),
                1000,
                2000,
                TimeUnit.MILLISECONDS);
    }
}
