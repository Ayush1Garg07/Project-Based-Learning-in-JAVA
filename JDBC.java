import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBC {

    public static void main(String[] args) {
        // Database credentials - CHANGE THESE AS PER YOUR SYSTEM
        String url = "jdbc:mysql://localhost:3306/your_database_name"; // Example: jdbc:mysql://localhost:3306/company
        String user = "root";  // Default for XAMPP/WAMP
        String pass = "";      // If password is set, put it here

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Step 1: Load MySQL JDBC Driver (Optional in newer versions but good for learning)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish Connection
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Database Connected Successfully!");

            // Step 3: Create Statement
            stmt = conn.createStatement();

            // Step 4: Write SQL Query
            String query = "SELECT EmpID, Name, Salary FROM Employee";

            // Step 5: Execute Query
            rs = stmt.executeQuery(query);
            System.out.println("\n---- Employee Table Data ----");

            // Step 6: Display Result
            while (rs.next()) {
                int id = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");

                System.out.println("EmpID: " + id + " | Name: " + name + " | Salary: " + salary);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver Not Found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ SQL Error: " + e.getMessage());
        } finally {
            // Step 7: Close Connections
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                System.out.println("\n🔒 Connection Closed.");
            } catch (SQLException e) {
                System.out.println("❌ Error Closing Connection: " + e.getMessage());
            }
        }
    }
}
