package main.reallifeexamples.studentslibraryproblem;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Since every student can read only one book at a time, it means:
 * BOOK - is a lock.
 */
public class Book {

    private final int id;
    private final Lock lock;

    public Book(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public void readByStudent(Student student) throws InterruptedException {
        if (lock.tryLock(10, TimeUnit.SECONDS)) { //Student will try to get book firstly
            System.out.println(student + " starts reading " + this);
            Thread.sleep(2000);
            lock.unlock(); //Student puts down the book
            System.out.println(student + " has just finished reading " + this);
        }
    }

    @Override
    public String toString() {
        return "Book: " + id;
    }
}