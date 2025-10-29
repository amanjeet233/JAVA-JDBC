 import java.sql.*;

public class FetchEmployees {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/companydb";
        String user = "root"; // replace with your MySQL username
        String password = "Amanjeet@4321."; // replace with your MySQL password

        try {
            // 1. Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish connection
            Connection con = DriverManager.getConnection(url, user, password);

            // 3. Create a statement
            Statement stmt = con.createStatement();

            // 4. Execute query
            String query = "SELECT * FROM Employee";
            ResultSet rs = stmt.executeQuery(query);

            // 5. Display results
            System.out.println("EmpID\tName\t\t\tSalary");
            System.out.println("-----------------------------------------");
            while (rs.next()) {
                int id = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println(id + "\t" + name + "\t\t" + salary);
            }

            // 6. Close resources
            rs.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection or query failed!");
            e.printStackTrace();
        }
    }
}
