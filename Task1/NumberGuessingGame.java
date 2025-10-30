import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        // For reading user inputs
        Scanner scanner = new Scanner(System.in);

        // For generating random numbers
        Random random = new Random();

        int totalScore = 0;   // Tracks cumulative scores across rounds
        int highScore = 0;    // Tracks the highest achieved score in a round
        boolean playAgain = true; // Boolean flag for multiple rounds

        System.out.println(" Welcome to the Number Guessing Game!");

        // Main game loop for multiple rounds
        while (playAgain) {

            // Difficulty level selection
            System.out.println("\nSelect Difficulty:");
            System.out.println("1. Easy (1-50, 15 attempts)");
            System.out.println("2. Medium (1-100, 10 attempts)");
            System.out.println("3. Hard (1-500, 5 attempts)");
            System.out.print("Enter choice: ");
            int level = scanner.nextInt();

            // Setting difficulty parameters
            int maxAttempts, maxNumber;
            switch (level) {
                case 1 -> { maxAttempts = 15; maxNumber = 50; }
                case 3 -> { maxAttempts = 5; maxNumber = 500; }
                default -> { maxAttempts = 10; maxNumber = 100; }
            }

            // Generate random number
            int numberToGuess = random.nextInt(maxNumber) + 1;

            int attempts = 0;    // Counts guesses made in the current round
            boolean guessed = false; // True if user guesses correctly

            System.out.println("\nGuess the number between 1 and " + maxNumber + "!");

            // Guessing loop
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");

                // Input validation
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.next(); // Skip invalid input
                    continue;
                }

                int guess = scanner.nextInt();
                attempts++;

                // Comparison logic
                if (guess == numberToGuess) {
                    System.out.println("Correct! You guessed it in " + attempts + " attempts.");
                    guessed = true;

                    // Round scoring
                    int roundScore = (maxAttempts - attempts + 1) * 10;
                    totalScore += roundScore;
                    if (roundScore > highScore) highScore = roundScore;

                    System.out.println(" Round Score: " + roundScore + " | Total Score: " + totalScore);
                    break;

                } else if (guess < numberToGuess) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }

                System.out.println("Attempts left: " + (maxAttempts - attempts));

                // Optional hint after 3 failed attempts
                if (attempts == 3 && !guessed) {
                    if (numberToGuess % 2 == 0)
                        System.out.println("Hint: The number is even!");
                    else
                        System.out.println(" Hint: The number is odd!");
                }
            }

            // Out of attempts message
            if (!guessed) {
                System.out.println(" Out of attempts! The number was: " + numberToGuess);
            }

            // Replay option
            System.out.print("\nDo you want to play another round? (y/n): ");
            char again = scanner.next().charAt(0);
            playAgain = (again == 'y' || again == 'Y');
        }

        // Game over summary
        System.out.println("\n Game Over!");
        System.out.println("Total Score: " + totalScore);
        System.out.println("Highest Round Score: " + highScore);
        System.out.println("Thanks for playing! ");

        scanner.close();
    }
}
