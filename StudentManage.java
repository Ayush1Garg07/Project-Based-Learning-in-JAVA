import java.sql.*;
import java.util.*;

public class StudentManage {

    // ================= MODEL =================
    static class Student {
        private int studentID;
        private String name;
        private String department;
        private double marks;

        public Student(int studentID, String name, String department, double marks) {
            this.studentID = studentID;
            this.name = name;
            this.department = department;
            this.marks = marks;
        }

        public Student(String name, String department, double marks) {
            this.name = name;
            this.department = department;
            this.marks = marks;
        }

        public int getStudentID() { return studentID; }
        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getMarks() { return marks; }
    }

    // ================= CONTROLLER =================
    static class StudentDAO {
        private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
        private static final String USER = "root";
        private static final String PASS = ""; // Add password if needed

        private Connection conn;

        public StudentDAO() throws Exception {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            conn.setAutoCommit(false);
        }

        public void addStudent(Student s) throws Exception {
            String sql = "INSERT INTO Student (Name, Department, Marks) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setDouble(3, s.getMarks());
            ps.executeUpdate();
            conn.commit();
        }

        public void viewStudents() throws Exception {
            String sql = "SELECT * FROM Student";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("\n---- STUDENT LIST ----");
            while (rs.next()) {
                System.out.println("StudentID: " + rs.getInt("StudentID") +
                                   " | Name: " + rs.getString("Name") +
                                   " | Dept: " + rs.getString("Department") +
                                   " | Marks: " + rs.getDouble("Marks"));
            }
        }

        public void updateStudent(int id, double marks) throws Exception {
            String sql = "UPDATE Student SET Marks = ? WHERE StudentID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, marks);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.println("✅ Student Updated Successfully!");
            } else {
                System.out.println("❌ Student Not Found!");
            }
        }

        public void deleteStudent(int id) throws Exception {
            String sql = "DELETE FROM Student WHERE StudentID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.println("✅ Student Deleted Successfully!");
            } else {
                System.out.println("❌ Student Not Found!");
            }
        }
    }

    // ================= VIEW (Main Menu) =================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            StudentDAO dao = new StudentDAO();
            while (true) {
                System.out.println("\n===== STUDENT MANAGEMENT MENU =====");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Update Student Marks");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Department: ");
                        String dept = sc.nextLine();
                        System.out.print("Enter Marks: ");
                        double marks = sc.nextDouble();
                        dao.addStudent(new Student(name, dept, marks));
                        System.out.println("✅ Student Added Successfully!");
                        break;

                    case 2:
                        dao.viewStudents();
                        break;

                    case 3:
                        System.out.print("Enter StudentID to Update: ");
                        int uid = sc.nextInt();
                        System.out.print("Enter New Marks: ");
                        double newMarks = sc.nextDouble();
                        dao.updateStudent(uid, newMarks);
                        break;

                    case 4:
                        System.out.print("Enter StudentID to Delete: ");
                        int did = sc.nextInt();
                        dao.deleteStudent(did);
                        break;

                    case 5:
                        System.out.println("🚪 Exiting...");
                        sc.close();
                        return;

                    default:
                        System.out.println("❌ Invalid Choice!");
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
