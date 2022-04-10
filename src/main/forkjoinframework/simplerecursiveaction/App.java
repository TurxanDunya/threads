package main.forkjoinframework.simplerecursiveaction;

import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        /**
         * This is the root of the task. If necessary tasks will be divided into smaller tasks.
         */
        SimpleRecursiveAction action = new SimpleRecursiveAction(120);
        action.invoke();
    }
}
