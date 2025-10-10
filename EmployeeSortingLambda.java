import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public void display() {
        System.out.println("Name: " + name + " | Age: " + age + " | Salary: " + salary);
    }
}

public class EmployeeSortingLambda {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        // Adding data
        employees.add(new Employee("Ayush", 24, 55000));
        employees.add(new Employee("Neha", 21, 60000));
        employees.add(new Employee("Ravi", 29, 50000));
        employees.add(new Employee("Karan", 23, 65000));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== EMPLOYEE SORTING MENU =====");
            System.out.println("1. Sort by Name (A → Z)");
            System.out.println("2. Sort by Age (Ascending)");
            System.out.println("3. Sort by Salary (Ascending)");
            System.out.println("4. Sort by Salary (Descending)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    employees.sort((e1, e2) -> e1.name.compareToIgnoreCase(e2.name));
                    System.out.println("\n✅ Sorted by Name:");
                    break;
                case 2:
                    employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
                    System.out.println("\n✅ Sorted by Age:");
                    break;
                case 3:
                    employees.sort((e1, e2) -> Double.compare(e1.salary, e2.salary));
                    System.out.println("\n✅ Sorted by Salary (Ascending):");
                    break;
                case 4:
                    employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
                    System.out.println("\n✅ Sorted by Salary (Descending):");
                    break;
                case 5:
                    System.out.println("🚪 Exiting Sorting Program...");
                    sc.close();
                    return;
                default:
                    System.out.println("❌ Invalid Choice! Try again.");
            }

            // Display sorted list
            for (Employee emp : employees) {
                emp.display();
            }
        }
    }
}
