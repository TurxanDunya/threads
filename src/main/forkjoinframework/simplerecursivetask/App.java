package main.forkjoinframework.simplerecursivetask;

import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SimpleRecursiveTask task = new SimpleRecursiveTask(30);

        //In this case invoke() will return
        System.out.println(task.invoke());
    }
}
