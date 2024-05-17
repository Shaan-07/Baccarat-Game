import java.util.Scanner;
import java.util.Random;

public class Baccarat {
    public static void main(String[] args) {
        boolean interactiveMode = args.length > 0 && (args[0].equals("-i") || args[0].equals("--interactive"));
        int playerWins = 0;
        int bankerWins = 0;
        int ties = 0;

        try (Scanner scan = new Scanner(System.in)) {
            Random random = new Random();

            while (true) {
                if (!interactiveMode && !playNextGame()) {
                    break;
                }

                int roundsPlayed = 0;
                int totalRounds = interactiveMode ? 100 : random.nextInt(101) + 100; // Random number of rounds for non-interactive mode

                Shoe shoe = new Shoe(8); // Standard Baccarat is usually played with 8 decks
                shoe.shuffle(); // Shuffle the shoe to randomize the cards

                while (roundsPlayed < totalRounds && shoe.size() >= 6) { // A round can only start if there are at least 6 cards in the shoe
                    roundsPlayed++;

                    BaccaratHand playerHand = dealHand(shoe);
                    BaccaratHand bankerHand = dealHand(shoe);

                    // Determine if the player draws a third card
                    if (playerHand.baccaratTotal() <= 5) {
                        playerHand.add(shoe.deal());
                    }

                    // Player stands, but banker may draw third card
                    if (playerHand.baccaratTotal() >= 6 && bankerHand.baccaratTotal() <= 2) {
                        bankerHand.add(shoe.deal());
                    }

                    // Determine the winner based on the final hands
                    String winner = determineWinner(playerHand, bankerHand);

                    if (winner.equals("Player")) {
                        playerWins++;
                    } else if (winner.equals("Banker")) {
                        bankerWins++;
                    } else {
                        ties++;
                    }

                    // Display the hands and their total value after every round
                    System.out.println("\nRound " + roundsPlayed);
                    System.out.println("Player = " + playerHand.toString() + " = " + playerHand.baccaratTotal() + " (total value)");
                    System.out.println("Banker = " + bankerHand.toString() + " = " + bankerHand.baccaratTotal() + " (total value)");
                    System.out.println("Winner: " + winner);

                    // Display game statistics after every round
                    System.out.println("\nGame statistics after round " + roundsPlayed + ":");
                    System.out.println("Player wins: " + playerWins);
                    System.out.println("Banker wins: " + bankerWins);
                    System.out.println("Ties: " + ties);

                    if (shoe.size() < 6) {
                        // Display end of game statistics
                        System.out.println("\nEnd of game statistics:");
                        System.out.println("Rounds played: " + roundsPlayed);
                        System.out.println("Player wins: " + playerWins);
                        System.out.println("Banker wins: " + bankerWins);
                        System.out.println("Ties: " + ties);
                        break;
                    }

                    if (interactiveMode) {
                        System.out.print("Do you want to continue? (Y/N): ");
                        String input = scan.nextLine().trim();
                        if (!input.equalsIgnoreCase("Y")) {
                            // Display end of game statistics
                            System.out.println("\nEnd of game statistics:");
                            System.out.println("Rounds played: " + roundsPlayed);
                            System.out.println("Player wins: " + playerWins);
                            System.out.println("Banker wins: " + bankerWins);
                            System.out.println("Ties: " + ties);
                            return;
                        }
                    }
                }

                if (!interactiveMode) {
                    break;
                }
            }

            scan.close();
        }
    }

    private static boolean playNextGame() {
        return true;
    }

    private static BaccaratHand dealHand(Shoe shoe) {
        BaccaratHand hand = new BaccaratHand();
        hand.add(shoe.deal());
        hand.add(shoe.deal());
        return hand;
    }

    private static String determineWinner(BaccaratHand playerHand, BaccaratHand bankerHand) {
        int playerTotal = playerHand.baccaratTotal();
        int bankerTotal = bankerHand.baccaratTotal();

        if (playerTotal > bankerTotal) {
            return "Player";
        } else if (bankerTotal > playerTotal) {
            return "Banker";
        } else {
            return "Tie";
        }
    }
}
