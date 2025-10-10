import java.io.*;
import java.util.*;

class Employee {
    private int empID;
    private String name;
    private String designation;
    private double salary;

    public Employee(int empID, String name, String designation, double salary) {
        this.empID = empID;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return empID + "," + name + "," + designation + "," + salary;
    }

    public static Employee fromString(String line) {
        String[] data = line.split(",");
        return new Employee(
            Integer.parseInt(data[0]),
            data[1],
            data[2],
            Double.parseDouble(data[3])
        );
    }

    public void display() {
        System.out.println("EmpID: " + empID + " | Name: " + name + " | Designation: " + designation + " | Salary: " + salary);
    }
}

public class EmployeeFileManagement {
    private static final String FILE_NAME = "employees.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== EMPLOYEE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(sc);
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("🚪 Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("❌ Invalid Choice! Please try again.");
            }
        }
    }

    // Method to add employee and write to file
    private static void addEmployee(Scanner sc) {
        try {
            sc.nextLine(); // Clear buffer
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // Clear buffer
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Designation: ");
            String designation = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            Employee emp = new Employee(id, name, designation, salary);

            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true));
            bw.write(emp.toString());
            bw.newLine();
            bw.close();

            System.out.println("✅ Employee Record Added Successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }

    // Method to read and display employee records
    private static void displayEmployees() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("⚠ No Employee Records Found!");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            System.out.println("\n---- EMPLOYEE RECORDS ----");
            while ((line = br.readLine()) != null) {
                Employee emp = Employee.fromString(line);
                emp.display();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }
}
