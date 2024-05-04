import java.util.Random;
import java.util.Scanner;
public class Player {
    private final String name;
    private final Board board;

    public Player(String name) {
        this.name = name;
        this.board = new Board();
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    // Method to place ships randomly on the player's board
    public void placeShipsRandomly() {
        Random random = new Random();
        int[] shipSizes = {1, 1, 1, 1, 2, 2, 3, 4}; // Sizes of different ships
        for (int size : shipSizes) {
            ShipOrientation orientation = random.nextBoolean() ? ShipOrientation.HORIZONTAL : ShipOrientation.VERTICAL;
            boolean placed = false;
            while (!placed) {
                int x = random.nextInt(board.getSize());
                int y = random.nextInt(board.getSize());
                Ship ship = new Ship(size, orientation);
                if (board.isValidPlacement(x, y, ship)) {
                    boolean validPlacement = true;
                    if (orientation == ShipOrientation.HORIZONTAL) {
                        for (int i = -1; i <= size; i++) {
                            if (x + i >= 0 && x + i < board.getSize()) {
                                if (board.getCell(x + i, y).getStatus() == CellStatus.SHIP) {
                                    validPlacement = false;
                                    break;
                                }
                                if (y - 1 >= 0 && board.getCell(x + i, y - 1).getStatus() == CellStatus.SHIP) {
                                    validPlacement = false;
                                    break;
                                }
                                if (y + 1 < board.getSize() && board.getCell(x + i, y + 1).getStatus() == CellStatus.SHIP) {
                                    validPlacement = false;
                                    break;
                                }
                            }
                        }
                    } else {
                        for (int j = -1; j <= size; j++) {
                            if (y + j >= 0 && y + j < board.getSize()) {
                                if (board.getCell(x, y + j).getStatus() == CellStatus.SHIP) {
                                    validPlacement = false;
                                    break;
                                }
                                if (x - 1 >= 0 && board.getCell(x - 1, y + j).getStatus() == CellStatus.SHIP) {
                                    validPlacement = false;
                                    break;
                                }
                                if (x + 1 < board.getSize() && board.getCell(x + 1, y + j).getStatus() == CellStatus.SHIP) {
                                    validPlacement = false;
                                    break;
                                }
                            }
                        }
                    }
                    if (validPlacement) {
                        board.placeShip(x, y, ship);
                        placed = true;
                    }
                }
            }
        }
        board.printOwnBoard(); // Print the player's own board after random arrangement
    }
    public void attack(Cell target) {
        if (target.getStatus() == CellStatus.SHIP) {
            target.setStatus(CellStatus.HIT); // Mark the cell as hit
            System.out.println("Hit!");
        } else if (target.getStatus() == CellStatus.EMPTY) {
            target.setStatus(CellStatus.MISS); // Mark the cell as missed
            System.out.println("Miss!");
        } else {
            System.out.println("You already attacked this cell."); // Inform the player if they already attacked the cell
        }
    }

    private static void arrangeShips(Player player, Scanner scanner) {
        String input;
        do {
            System.out.println(player.getName() + ", enter 'random' to arrange ships randomly or 'ok' to proceed: ");
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("random")) {
                player.placeShipsRandomly();
            }
        } while (!input.equals("ok")); // Loop until input is "ok"
    }

}