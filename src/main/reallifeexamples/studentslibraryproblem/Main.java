package main.reallifeexamples.studentslibraryproblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static main.reallifeexamples.studentslibraryproblem.Constants.NUM_OF_BOOKS;
import static main.reallifeexamples.studentslibraryproblem.Constants.NUM_OF_STUDENTS;

public class Main {
    public static void main(String[] args) {
        Student[] students = null;
        Book[] books = null;
        ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_STUDENTS);

        try {
            students = new Student[NUM_OF_STUDENTS];
            books = new Book[NUM_OF_BOOKS];

            for (int i = 0; i < NUM_OF_BOOKS; ++i)
                books[i] = new Book(i + 1);

            for (int i = 0; i < NUM_OF_STUDENTS; ++i) {
                students[i] = new Student(i + 1, books);
                executor.execute(students[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
