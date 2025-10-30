import java.util.Scanner;

public class ATMInterface {

    double totalAmount;
    String user_name;
    String account_number;

    // Constructor
    ATMInterface(String user_name, String account_number) {
        this.user_name = user_name;
        this.account_number = account_number;
        this.totalAmount = 0; // start with 0 balance
    }

    // Withdraw Method
    double withdraw(double amount) {
        if (amount > 0 && amount <= 10000) {
            if (amount <= totalAmount) {
                totalAmount -= amount;
                System.out.println(" Successfully withdrawn Rs." + amount);
            } else {
                System.out.println("Insufficient balance! Your current balance is Rs." + totalAmount);
            }
        } else {
            System.out.println(" Invalid amount! Maximum withdrawal limit is Rs.10,000.");
        }
        return totalAmount;
    }

    // Deposit Method
    double deposit(double amount) {
        if (amount > 0) {
            totalAmount += amount;
            System.out.println("Successfully deposited Rs." + amount);
        } else {
            System.out.println(" Invalid deposit amount!");
        }
        return totalAmount;
    }

    // Check Balance Method
    double checkBalance() {
        System.out.println("Your current balance is: Rs." + totalAmount);
        return totalAmount;
    }

    // Main Method
    public static void main(String[] args) {

        Scanner Myobj = new Scanner(System.in);

        // USER DETAILS INPUT
        System.out.print("Enter your name: ");
        String user_name = Myobj.nextLine();
        while (!user_name.matches("^[A-Z a-z]+$")) {
            System.out.print("Invalid Name! Enter again: ");
            user_name = Myobj.nextLine();
        }

        System.out.print("Enter your 10-digit account number: ");
        String account_number = Myobj.nextLine();
        while (!account_number.matches("^[0-9]{10}$")) {
            System.out.print("Invalid Account Number! It must contain exactly 10 digits. Enter again: ");
            account_number = Myobj.nextLine();
        }

        // CREATE USER ACCOUNT
        ATMInterface user = new ATMInterface(user_name, account_number);

        //  MENU OPTIONS 
        int choice;
        do {
            System.out.println("\n ATM MENU ");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            while (!Myobj.hasNextInt()) {
                System.out.print("Invalid input! Enter a number: ");
                Myobj.next();
            }
            choice = Myobj.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = Myobj.nextDouble();
                    user.deposit(depositAmount);
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = Myobj.nextDouble();
                    user.withdraw(withdrawAmount);
                }
                case 3 -> user.checkBalance();
                case 4 -> System.out.println("Thank you for using the ATM!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        Myobj.close();
    }
}
