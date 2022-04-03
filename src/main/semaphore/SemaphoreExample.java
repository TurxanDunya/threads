package main.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

//Singleton pattern
enum Downloader {
    INSTANCE;

    private final Semaphore semaphore = new Semaphore(3, true);

    public void download() {
        try {
            semaphore.acquire(); //Acquires a permit from this semaphore
            downloadData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); //Releases a permit, returning it to the semaphore
        }
    }

    private void downloadData() {
        try {
            System.out.println("Downloading data from the web...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SemaphoreExample {
    public static void main(String[] args) {
        //Create multiple threads - executors
        ExecutorService service = Executors.newCachedThreadPool();

        //We are creating 12 threads
        for (int i = 0; i < 12; i++) {
            service.execute(Downloader.INSTANCE::download);
        }
    }
}
