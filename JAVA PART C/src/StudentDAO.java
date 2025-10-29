 import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "Amanjeet@4321.";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Create
    public void addStudent(Student s) {
        String sql = "INSERT INTO Student (Name, Department, Marks) VALUES (?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setDouble(3, s.getMarks());
            ps.executeUpdate();
            System.out.println("✅ Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("StudentID"),
                        rs.getString("Name"),
                        rs.getString("Department"),
                        rs.getDouble("Marks"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Update
    public void updateStudent(Student s) {
        String sql = "UPDATE Student SET Name=?, Department=?, Marks=? WHERE StudentID=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setDouble(3, s.getMarks());
            ps.setInt(4, s.getStudentID());
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("✅ Student updated successfully!");
            else System.out.println("⚠️ Student not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteStudent(int id) {
        String sql = "DELETE FROM Student WHERE StudentID=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("✅ Student deleted successfully!");
            else System.out.println("⚠️ Student not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
