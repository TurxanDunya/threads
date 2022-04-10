package main.forkjoinframework.simplerecursiveaction;

import java.util.concurrent.RecursiveAction;

/**
 * If we do not want to return value then use RecursiveAction.
 * In the case we wait for result then use RecursiveTask<T>
 */
public class SimpleRecursiveAction extends RecursiveAction {

    private final int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {
        //If the task is too large then we split it and execute the tasks in parallel
        if (simulatedWork > 100) {
            System.out.println("Parallel execution and split the tasks: " + simulatedWork);

            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork / 2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork / 2);

            action1.fork();
            action2.fork();
        } else {
            System.out.println("The task is rather small so sequential is fine...");
            System.out.println("The size of the task: " + simulatedWork);
        }
    }
}
