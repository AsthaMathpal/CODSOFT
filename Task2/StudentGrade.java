import java.util.Scanner;

public class StudentGrade {
    public static void main(String[] args) {
        Scanner Myobj = new Scanner(System.in);

        System.out.println("Please enter your details for student's grade evaluation!!");

        // Name input with regex validation
        System.out.print("Enter your name: ");
        String name = Myobj.next();

        while (!name.matches("^[A-Za-z]+$")) {
            System.out.print("INVALID NAME! Please enter again: ");
            name = Myobj.next();
        }

        // Number of subjects
        System.out.print("Enter number of subjects: ");
        double subjects = Myobj.nextDouble();
        while (subjects <= 1) {
            System.out.println("The number of subjects should be greater than 1.");
            System.out.print("Enter again: ");
            subjects = Myobj.nextDouble();
        }

        // Highest marks per subject
        System.out.print("Enter the highest marks a student can obtain: ");
        double highest = Myobj.nextDouble();
        while (highest <= 5) {
            System.out.println("Highest marks must be greater than 5. Enter again: ");
            highest = Myobj.nextDouble();
        }

        // Marks input
        double total = 0;
        for (int i = 1; i <= subjects; i++) {
            System.out.print("Enter marks of subject " + i + ": ");
            double mark = Myobj.nextDouble();

            while (mark < 0 || mark > highest) {
                System.out.println("Marks are invalid!");
                System.out.print("Enter marks of subject " + i + " again: ");
                mark = Myobj.nextDouble();
            }

            total += mark;
        }

        // Average and Grade Calculation
        double avg = total / subjects;
        double percentage = (avg / highest) * 100;

        String grade;
        if (percentage < 40) grade = "F";
        else if (percentage < 45) grade = "D";
        else if (percentage < 55) grade = "C";
        else if (percentage < 60) grade = "B";
        else if (percentage < 70) grade = "B+";
        else if (percentage < 80) grade = "A";
        else if (percentage < 90) grade = "A+";
        else grade = "O";

        // Output
        System.out.println("\nThe student details after evaluation are:");
        System.out.println("Name: " + name);
        System.out.println("Total Marks: " + total + " / " + (subjects * highest));
        System.out.printf("Average Marks: %.2f\n", avg);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Grade: " + grade);

        Myobj.close();
    }
}
