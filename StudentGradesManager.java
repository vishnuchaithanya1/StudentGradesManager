import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradesManager {
    private static final ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Grades Manager ---");
            System.out.println("1. Add student and grade");
            System.out.println("2. Show summary report");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent(scanner);
                case 2 -> showSummary();
                case 3 -> System.out.println("Exiting program.");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        double grade;
        while (true) {
            System.out.print("Enter grade (0-100): ");
            grade = scanner.nextDouble();
            scanner.nextLine(); // consume newline
            if (grade >= 0 && grade <= 100) break;
            else System.out.println("Invalid grade. Please enter between 0 and 100.");
        }

        students.add(new Student(name, grade));
        System.out.println("Student added successfully.");
    }

    private static void showSummary() {
        if (students.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        double sum = 0, highest = Double.MIN_VALUE, lowest = Double.MAX_VALUE;
        String topStudent = "", bottomStudent = "";

        System.out.println("\n--- Student Report ---");
        for (Student s : students) {
            System.out.println(s.name + ": " + s.grade);
            sum += s.grade;
            if (s.grade > highest) {
                highest = s.grade;
                topStudent = s.name;
            }
            if (s.grade < lowest) {
                lowest = s.grade;
                bottomStudent = s.name;
            }
        }

        double average = sum / students.size();

        System.out.println("\nSummary:");
        System.out.printf("Average Score: %.2f\n", average);
        System.out.printf("Highest Score: %.2f (%s)\n", highest, topStudent);
        System.out.printf("Lowest Score: %.2f (%s)\n", lowest, bottomStudent);
    }
}

