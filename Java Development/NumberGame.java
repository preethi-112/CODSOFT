import java.util.Scanner;
import java.util.Random;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int maxAttempts = 7; // Max attempts per round
        int lowerBound = 1;
        int upperBound = 100;
        int roundsPlayed = 0;
        int roundsWon = 0;

        System.out.println("🎮 Welcome to the Number Guessing Game!");

        boolean playAgain = true;

        while (playAgain) {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attemptsLeft = maxAttempts;
            boolean guessedCorrectly = false;

            System.out.println("\n🔢 I have picked a number between " + lowerBound + " and " + upperBound + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attemptsLeft > 0) {
                System.out.print("\nEnter your guess: ");
                int userGuess;

                // Validate input
                if (!scanner.hasNextInt()) {
                    System.out.println("⚠️ Please enter a valid number.");
                    scanner.next(); // clear invalid input
                    continue;
                }

                userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess == targetNumber) {
                    System.out.println("🎉 Correct! You guessed the number!");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("📉 Too low!");
                } else {
                    System.out.println("📈 Too high!");
                }

                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!guessedCorrectly) {
                System.out.println("❌ You've run out of attempts! The number was: " + targetNumber);
            }

            roundsPlayed++;

            // Ask if the user wants to play again
            System.out.print("\nDo you want to play another round? (yes/no): ");
            scanner.nextLine(); // consume newline
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");
        }

        // Final score
        System.out.println("\n🏁 Game Over!");
        System.out.println("Rounds played: " + roundsPlayed);
        System.out.println("Rounds won: " + roundsWon);
        System.out.println("Thanks for playing! 👋");

        scanner.close();
    }
}
