 import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {

    static final String URL = "jdbc:mysql://localhost:3306/shopdb";
    static final String USER = "root"; 
    static final String PASSWORD = "Amanjeet@4321."; 

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner sc = new Scanner(System.in)) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con.setAutoCommit(false); 
            int choice;

            do {
                System.out.println("\n===== PRODUCT CRUD MENU =====");
                System.out.println("1. Add Product");
                System.out.println("2. View All Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addProduct(con, sc);
                        break;
                    case 2:
                        viewProducts(con);
                        break;
                    case 3:
                        updateProduct(con, sc);
                        break;
                    case 4:
                        deleteProduct(con, sc);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } while (choice != 5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addProduct(Connection con, Scanner sc) {
        try {
            System.out.print("Enter Product Name: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            String sql = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);

            int rows = ps.executeUpdate();
            con.commit();
            System.out.println(rows + " product added successfully.");

        } catch (Exception e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            System.out.println("Insert failed. Transaction rolled back.");
            e.printStackTrace();
        }
    }

    private static void viewProducts(Connection con) {
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
            System.out.println("\nProductID\tProductName\tPrice\tQuantity");
            System.out.println("---------------------------------------------");
            while (rs.next()) {
                System.out.printf("%d\t\t%s\t\t%.2f\t%d\n",
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateProduct(Connection con, Scanner sc) {
        try {
            System.out.print("Enter Product ID to update: ");
            int id = sc.nextInt();
            System.out.print("Enter new Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter new Quantity: ");
            int qty = sc.nextInt();

            String sql = "UPDATE Product SET Price = ?, Quantity = ? WHERE ProductID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, price);
            ps.setInt(2, qty);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            con.commit();
            System.out.println(rows + " product updated successfully.");

        } catch (Exception e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            System.out.println("Update failed. Transaction rolled back.");
            e.printStackTrace();
        }
    }

    private static void deleteProduct(Connection con, Scanner sc) {
        try {
            System.out.print("Enter Product ID to delete: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM Product WHERE ProductID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            con.commit();
            System.out.println(rows + " product deleted successfully.");

        } catch (Exception e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            System.out.println("Delete failed. Transaction rolled back.");
            e.printStackTrace();
        }
    }
}
