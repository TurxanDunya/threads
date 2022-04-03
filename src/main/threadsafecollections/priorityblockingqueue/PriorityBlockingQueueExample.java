package main.threadsafecollections.priorityblockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstWorker implements Runnable {

    private final PriorityBlockingQueue<Person> queue;

    public FirstWorker(PriorityBlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put(new Person("Turkhan", 24));
            queue.put(new Person("Valeh", 25));
            Thread.sleep(2000);
            queue.put(new Person("Rashad", 25));
            Thread.sleep(1000);
            queue.put(new Person("Asif", 30));
            queue.put(new Person("Farid", 25));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWorker implements Runnable {

    private final PriorityBlockingQueue<Person> queue;

    public SecondWorker(PriorityBlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(queue.take());
            Thread.sleep(1000);
            System.out.println(queue.take());
            Thread.sleep(2000);
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//How java will compare objects
class Person implements Comparable<Person> {

    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    //We will compare objects by its names
    @Override
    public int compareTo(Person person) {
        return name.compareTo(person.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

//BlockingQueue result is sorted by the way
public class PriorityBlockingQueueExample {
    public static void main(String[] args) {
        PriorityBlockingQueue<Person> queue = new PriorityBlockingQueue<>();

        FirstWorker firstWorker = new FirstWorker(queue);
        SecondWorker secondWorker = new SecondWorker(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }
}
