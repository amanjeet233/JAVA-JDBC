 public class StudentApp {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load driver
            StudentView view = new StudentView();
            view.showMenu();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
            e.printStackTrace();
        }
    }
}
