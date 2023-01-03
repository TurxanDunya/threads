package main.basics.callableandfuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Processor implements Callable<String> {

    private final int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "Id: " + id;
    }
}

public class App {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            Future<String> processId = service.submit(new Processor(i + 1));
            futures.add(processId);
        }

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
