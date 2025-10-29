 import java.util.List;
import java.util.Scanner;

public class StudentView {
    private final StudentDAO dao = new StudentDAO();
    private final Scanner sc = new Scanner(System.in);

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n===== STUDENT MANAGEMENT MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    private void addStudent() {
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        Student s = new Student(0, name, dept, marks);
        dao.addStudent(s);
    }

    private void viewAllStudents() {
        List<Student> list = dao.getAllStudents();
        System.out.println("\nID\tName\tDepartment\tMarks");
        System.out.println("-----------------------------------");
        for (Student s : list)
            System.out.println(s);
    }

    private void updateStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter New Name: ");
        String name = sc.nextLine();
        System.out.print("Enter New Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter New Marks: ");
        double marks = sc.nextDouble();

        Student s = new Student(id, name, dept, marks);
        dao.updateStudent(s);
    }

    private void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        dao.deleteStudent(id);
    }
}
