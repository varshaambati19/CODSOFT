import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int totalRounds = 0;
        int roundsWon = 0;

        System.out.println(" Welcome to the Number Guessing Game!");

        boolean playAgain = true;

        while (playAgain) {
            totalRounds++;

            int numberToGuess = random.nextInt(100) + 1; // 1 to 100
            int maxAttempts = 7;
            int attemptsUsed = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nRound " + totalRounds);
            System.out.println("you have to select a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts.");

            while (attemptsUsed < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attemptsUsed++;

                if (guess == numberToGuess) {
                    System.out.println(" Correct! You guessed the number in " + attemptsUsed + " attempts.");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            if (!guessedCorrectly) {
                System.out.println(" You've used all attempts. The correct number was: " + numberToGuess);
            }

            // Play again option
            System.out.print("\nDo you want to play again? (yes/no): ");
            sc.nextLine(); // consume newline
            String choice = sc.nextLine().toLowerCase();

            if (!choice.equals("yes")) {
                playAgain = false;
            }
        }

        // Final Score
        System.out.println("\n GAME OVER");
        System.out.println("Total Rounds Played: " + totalRounds);
        System.out.println("Rounds Won: " + roundsWon);
        System.out.println("Thanks for playing!");

        sc.close();
    }
}
