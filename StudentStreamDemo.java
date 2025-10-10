import java.util.*;
import java.util.stream.*;

class Student {
    private String name;
    private int marks;

    // Constructor
    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }
}

public class StudentStreamDemo {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Ayush", 80),
                new Student("Rohan", 65),
                new Student("Neha", 92),
                new Student("Priya", 78),
                new Student("Sumit", 54)
        );

        System.out.println("Students scoring above 75% sorted by marks:");
        students.stream()
                .filter(s -> s.getMarks() > 75)         // Filter
                .sorted(Comparator.comparingInt(Student::getMarks)) // Sort by marks
                .map(Student::getName)                 // Extract only names
                .forEach(System.out::println);         // Display
    }
}
