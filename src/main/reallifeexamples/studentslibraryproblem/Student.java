package main.reallifeexamples.studentslibraryproblem;

import java.util.Random;

import static main.reallifeexamples.studentslibraryproblem.Constants.NUM_OF_BOOKS;

/**
 * Every STUDENT - is a thread.
 */
public class Student implements Runnable {

    private final int id;
    private final Book[] books; //Student will choose the book randomly
    private final Random random;

    public Student(int id, Book[] books) {
        this.id = id;
        this.books = books;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            int bookId = random.nextInt(NUM_OF_BOOKS);

            try {
                books[bookId].readByStudent(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Student: " + id;
    }
}