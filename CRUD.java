import java.sql.*;
import java.util.Scanner;

public class CRUD {

    static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
    static final String USER = "root";
    static final String PASS = ""; // Add password if you have one

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✅ Connected to Database.");
            conn.setAutoCommit(false); // Enable manual transaction control

            while (true) {
                System.out.println("\n===== PRODUCT CRUD MENU =====");
                System.out.println("1. Insert Product");
                System.out.println("2. Display All Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        insertProduct(conn, sc);
                        break;
                    case 2:
                        displayProducts(conn);
                        break;
                    case 3:
                        updateProduct(conn, sc);
                        break;
                    case 4:
                        deleteProduct(conn, sc);
                        break;
                    case 5:
                        System.out.println("🚪 Exiting...");
                        conn.close();
                        sc.close();
                        return;
                    default:
                        System.out.println("❌ Invalid Choice! Try again.");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Insert Product
    public static void insertProduct(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter Product Name: ");
            sc.nextLine(); // consume leftover newline
            String name = sc.nextLine();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            String sql = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);

            ps.executeUpdate();
            conn.commit();
            System.out.println("✅ Product Inserted Successfully!");

        } catch (Exception e) {
            try { conn.rollback(); } catch (SQLException ex) {}
            System.out.println("❌ Insertion Failed. Transaction Rolled Back.");
        }
    }

    // Display Products
    public static void displayProducts(Connection conn) {
        try {
            String sql = "SELECT * FROM Product";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n---- PRODUCT LIST ----");
            while (rs.next()) {
                System.out.println("ProductID: " + rs.getInt("ProductID") +
                                   " | Name: " + rs.getString("ProductName") +
                                   " | Price: " + rs.getDouble("Price") +
                                   " | Quantity: " + rs.getInt("Quantity"));
            }

        } catch (Exception e) {
            System.out.println("❌ Error Fetching Data.");
        }
    }

    // Update Product
    public static void updateProduct(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter ProductID to Update: ");
            int id = sc.nextInt();
            System.out.print("Enter New Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter New Quantity: ");
            int qty = sc.nextInt();

            String sql = "UPDATE Product SET Price = ?, Quantity = ? WHERE ProductID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, price);
            ps.setInt(2, qty);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.println("✅ Product Updated Successfully!");
            } else {
                System.out.println("❌ Product Not Found!");
            }

        } catch (Exception e) {
            try { conn.rollback(); } catch (SQLException ex) {}
            System.out.println("❌ Update Failed. Transaction Rolled Back.");
        }
    }

    // Delete Product
    public static void deleteProduct(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter ProductID to Delete: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM Product WHERE ProductID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.println("✅ Product Deleted Successfully!");
            } else {
                System.out.println("❌ Product Not Found!");
            }

        } catch (Exception e) {
            try { conn.rollback(); } catch (SQLException ex) {}
            System.out.println("❌ Delete Failed. Transaction Rolled Back.");
        }
    }
}
