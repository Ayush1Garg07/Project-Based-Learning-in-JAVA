import java.io.*;

// Student class (Model) implementing Serializable
class Student implements Serializable {
    private int studentID;
    private String name;
    private String grade;

    // Constructor
    public Student(int studentID, String name, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    // Method to display details
    public void display() {
        System.out.println("StudentID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Grade: " + grade);
    }
}

public class StudentSerializationDemo {
    public static void main(String[] args) {
        String filename = "studentData.ser"; // File to save object

        // STEP 1: Create a Student object
        Student student = new Student(101, "Ayush", "A");

        // STEP 2: Serialization (Saving Object to File)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(student);
            System.out.println("✅ Serialization Successful! Object saved to file.");
        } catch (IOException e) {
            System.out.println("❌ Error during Serialization: " + e.getMessage());
        }

        // STEP 3: Deserialization (Reading Object from File)
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Student deserializedStudent = (Student) ois.readObject();
            System.out.println("\n✅ Deserialization Successful! Object restored.");
            System.out.println("---- Student Details After Deserialization ----");
            deserializedStudent.display();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Error during Deserialization: " + e.getMessage());
        }
    }
}
