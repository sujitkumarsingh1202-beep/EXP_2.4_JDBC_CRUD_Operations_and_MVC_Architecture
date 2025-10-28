package view;

 
import java.util.*;

import controller.StudentDAO;
import model.Student;

public class StudentApp {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Department: ");
                    String dept = sc.next();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    dao.addStudent(new Student(0, name, dept, marks));
                    System.out.println("Added!");
                    break;

                case 2:
                    List<Student> students = dao.getAllStudents();
                    System.out.println("ID | Name | Department | Marks");
                    for (Student s : students) {
                        System.out.println(s);
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to update: ");
                    int updateId = sc.nextInt();
                    System.out.print("Enter New Name: ");
                    String newName = sc.next();
                    System.out.print("Enter New Department: ");
                    String newDept = sc.next();
                    System.out.print("Enter New Marks: ");
                    double newMarks = sc.nextDouble();
                    dao.updateStudent(new Student(updateId, newName, newDept, newMarks));
                    System.out.println("Updated!");
                    break;

                case 4:
                    System.out.print("Enter Student ID to Delete: ");
                    int delId = sc.nextInt();
                    dao.deleteStudent(delId);
                    System.out.println("Deleted!");
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
