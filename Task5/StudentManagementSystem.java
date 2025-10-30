import java.util.*;
import java.io.*;

class Student {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String toString() {
        return rollNumber + " - " + name + " (Grade: " + grade + ")";
    }
}

public class StudentManagementSystem {
    private static List<Student> students = new ArrayList<Student>();
    private static final String FILE_NAME = "students.txt";

    // Load students from file
    private static void loadStudents() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                return;
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 3) {
                    students.add(new Student(data[0], data[1], data[2]));
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error loading student data.");
        }
    }

    // Save students to file
    private static void saveStudents() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME));
            for (Student s : students) {
                writer.println(s.getName() + "," + s.getRollNumber() + "," + s.getGrade());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving student data.");
        }
    }

    // Add student
    private static void addStudent(Scanner Myobj) {
        System.out.print("Enter student name: ");
        String name = Myobj.nextLine();

        System.out.print("Enter roll number: ");
        String roll = Myobj.nextLine();

        System.out.print("Enter grade: ");
        String grade = Myobj.nextLine();

        if (name.isEmpty() || roll.isEmpty() || grade.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        students.add(new Student(name, roll, grade));
        saveStudents();
        System.out.println("Student added successfully.");
    }

    // Remove student
    private static void removeStudent(Scanner Myobj) {
        System.out.print("Enter roll number to remove: ");
        String roll = Myobj.nextLine();

        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNumber().equalsIgnoreCase(roll)) {
                students.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            saveStudents();
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Search student
    private static void searchStudent(Scanner Myobj) {
        System.out.print("Enter roll number to search: ");
        String roll = Myobj.nextLine();

        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(roll)) {
                System.out.println("Student found: " + s);
                return;
            }
        }
        System.out.println("No student found with that roll number.");
    }

    // Display all students
    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }

        System.out.println("\nList of Students:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Edit student details
    private static void editStudent(Scanner Myobj) {
        System.out.print("Enter roll number to edit: ");
        String roll = Myobj.nextLine();

        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(roll)) {
                System.out.print("Enter new name (leave blank to keep same): ");
                String newName = Myobj.nextLine();
                if (!newName.isEmpty()) {
                    s.setName(newName);
                }

                System.out.print("Enter new grade (leave blank to keep same): ");
                String newGrade = Myobj.nextLine();
                if (!newGrade.isEmpty()) {
                    s.setGrade(newGrade);
                }

                saveStudents();
                System.out.println("Student details updated.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Main method
    public static void main(String[] args) {
        Scanner Myobj = new Scanner(System.in);
        loadStudents();

        while (true) {
            System.out.println("\n==== Student Management System ====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Edit Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(Myobj.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addStudent(Myobj);
                    break;
                case 2:
                    removeStudent(Myobj);
                    break;
                case 3:
                    editStudent(Myobj);
                    break;
                case 4:
                    searchStudent(Myobj);
                    break;
                case 5:
                    displayAllStudents();
                    break;
                case 6:
                    System.out.println("Exiting the program...");
                    saveStudents();
                    Myobj.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
