import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create two players
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // Arrange ships for both players
        arrangeShips(player1, scanner);
        arrangeShips(player2, scanner);

        // Game loop
        Player currentPlayer = player1;
        Player opponent = player2;
        while (!isGameOver(player1, player2)) {
            // Print opponent's board
            System.out.println(currentPlayer.getName() + "'s Turn:");
            opponent.getBoard().printOpponentBoard();

            // Attack opponent's board
            System.out.print("Enter x coordinate to attack (0-7): ");
            int x = scanner.nextInt();
            System.out.print("Enter y coordinate to attack (0-7): ");
            int y = scanner.nextInt();

            // Ensure attack coordinates are within bounds
            if (x < 0 || x >= opponent.getBoard().getSize() || y < 0 || y >= opponent.getBoard().getSize()) {
                System.out.println("Invalid coordinates. Try again.");
                continue;
            }

            Cell targetCell = opponent.getBoard().getCell(x, y);
            currentPlayer.attack(targetCell);

            // Switch players
            Player temp = currentPlayer;
            currentPlayer = opponent;
            opponent = temp;
        }

        // Game over
        System.out.println("Game Over!");
        if (player1.getBoard().allShipsDestroyed()) {
            System.out.println(player2.getName() + " wins!");
        } else {
            System.out.println(player1.getName() + " wins!");
        }

        scanner.close();
    }

    private static boolean isGameOver(Player player1, Player player2) {
        return player1.getBoard().allShipsDestroyed() || player2.getBoard().allShipsDestroyed();
    }

    private static void arrangeShips(Player player, Scanner scanner) {
        String input;
        do {
            System.out.println(player.getName() + ", enter 'random' to arrange ships randomly or 'ok' to proceed: ");
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("random")) {
                player.placeShipsRandomly();
                player.getBoard().printOwnBoard(); // Print the player's own board after random arrangement
            }
        } while (!input.equals("ok")); // Loop until input is "ok"
    }
}
