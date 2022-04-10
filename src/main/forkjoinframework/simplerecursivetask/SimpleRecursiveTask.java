package main.forkjoinframework.simplerecursivetask;

import main.forkjoinframework.simplerecursiveaction.SimpleRecursiveAction;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveTask extends RecursiveTask<Integer> {

    private final int simulatedWork;

    public SimpleRecursiveTask(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected Integer compute() {
        //If the task is too large then we split it and execute the tasks in parallel
        if (simulatedWork > 100) {
            System.out.println("Parallel execution and split the tasks: " + simulatedWork);

            SimpleRecursiveTask task1 = new SimpleRecursiveTask(simulatedWork / 2);
            SimpleRecursiveTask task2 = new SimpleRecursiveTask(simulatedWork / 2);

            //We add the tasks to the thread pool (They are going to be executed in parallel)
            task1.fork();
            task2.fork();

            int subSolution = 0;
            subSolution += task1.join();
            subSolution += task2.join();

            return subSolution;
        } else {
            System.out.println("The task is rather small so sequential is fine." + simulatedWork);
            return simulatedWork;
        }
    }

}
