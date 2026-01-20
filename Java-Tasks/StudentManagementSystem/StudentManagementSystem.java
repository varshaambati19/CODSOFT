import java.io.*;
import java.util.*;

/* ===================== STUDENT CLASS ===================== */
class Student {
    private String name;
    private int rollNumber;
    private String grade;
    private String course;

    public Student(String name, int rollNumber, String grade, String course) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.course = course;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return rollNumber + "," + name + "," + grade + "," + course;
    }

    public static Student fromString(String data) {
        String[] parts = data.split(",");
        return new Student(
                parts[1],
                Integer.parseInt(parts[0]),
                parts[2],
                parts[3]
        );
    }

    public void display() {
        System.out.println(
                "Roll No: " + rollNumber +
                ", Name: " + name +
                ", Grade: " + grade +
                ", Course: " + course
        );
    }
}

/* ================= STUDENT MANAGEMENT SYSTEM ================= */
public class StudentManagementSystem {

    private static final String FILE_NAME = "students.txt";
    private static List<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadFromFile();

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> editStudent();
                case 3 -> removeStudent();
                case 4 -> searchStudent();
                case 5 -> displayStudents();
                case 6 -> {
                    saveToFile();
                    System.out.println("Data saved. Exiting application.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    /* ===================== OPERATIONS ===================== */

    private static void addStudent() {
        System.out.print("Enter Roll Number: ");
        int roll = getIntInput();

        if (findStudent(roll) != null) {
            System.out.println("Student already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = getNonEmptyInput();

        System.out.print("Enter Grade: ");
        String grade = getNonEmptyInput();

        System.out.print("Enter Course: ");
        String course = getNonEmptyInput();

        students.add(new Student(name, roll, grade, course));
        System.out.println("Student added successfully!");
    }

    private static void editStudent() {
        System.out.print("Enter Roll Number to edit: ");
        int roll = getIntInput();

        Student s = findStudent(roll);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter New Name: ");
        s.setName(getNonEmptyInput());

        System.out.print("Enter New Grade: ");
        s.setGrade(getNonEmptyInput());

        System.out.print("Enter New Course: ");
        s.setCourse(getNonEmptyInput());

        System.out.println("Student updated successfully!");
    }

    private static void removeStudent() {
        System.out.print("Enter Roll Number to remove: ");
        int roll = getIntInput();

        Student s = findStudent(roll);
        if (s != null) {
            students.remove(s);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void searchStudent() {
        System.out.print("Enter Roll Number to search: ");
        int roll = getIntInput();

        Student s = findStudent(roll);
        if (s != null) {
            s.display();
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            s.display();
        }
    }

    /* ===================== HELPERS ===================== */

    private static Student findStudent(int roll) {
        for (Student s : students) {
            if (s.getRollNumber() == roll)
                return s;
        }
        return null;
    }

    private static int getIntInput() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private static String getNonEmptyInput() {
        String input;
        while (true) {
            input = sc.nextLine().trim();
            if (!input.isEmpty())
                return input;
            System.out.print("Input cannot be empty. Enter again: ");
        }
    }

    /* ===================== FILE HANDLING ===================== */

    private static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving data!");
        }
    }

    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                students.add(Student.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading data!");
        }
    }
}
