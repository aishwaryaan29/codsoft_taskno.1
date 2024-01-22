import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 10;

        Scanner scanner = new Scanner(System.in);

        int totalAttempts = 0;
        boolean playAgain = true;

        while (playAgain) {
            int secretNumber = generateRandomNumber(lowerBound, upperBound);

            System.out.println("Welcome to the Number Game!");
            System.out.println("I've selected a random number between " + lowerBound + " and " + upperBound
                    + ". Try to guess it.");
            System.out.println("You have a maximum of " + maxAttempts + " attempts.");

            totalAttempts += playNumberGame(secretNumber, maxAttempts);

            System.out.println("Your total score: " + totalAttempts + " attempts.");

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = askToPlayAgain(scanner);
        }

        System.out.println("Thank you for playing the Number Game!");
        scanner.close();
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        while (true) {
            try {
                String playAgainResponse = scanner.next().toLowerCase();
                return playAgainResponse.equals("yes") || playAgainResponse.equals("no");
            } catch (java.util.NoSuchElementException e) {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    public static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    public static int playNumberGame(int secretNumber, int maxAttempts) {
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        boolean hasGuessedCorrectly = false;

        while (attempts < maxAttempts && !hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess == secretNumber) {
                hasGuessedCorrectly = true;
                System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
            } else if (userGuess < secretNumber) {
                System.out.println("Try a higher number.");
            } else {
                System.out.println("Try a lower number.");
            }
        }

        if (!hasGuessedCorrectly) {
            System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was "
                    + secretNumber + ".");
        }

        return attempts;
    }
}