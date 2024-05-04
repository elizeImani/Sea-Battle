public class Board {
    private static final int BOARD_SIZE = 8;
    private Cell[][] cells;

    public Board() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        initializeCells();
    }

    private void initializeCells() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        return cells[x][y];
    }

    public int getSize() {
        return BOARD_SIZE;
    }

    public boolean isValidPlacement(int x, int y, Ship ship) {
        int size = ship.getSize();
        ShipOrientation orientation = ship.getOrientation();

        if (orientation == ShipOrientation.HORIZONTAL && x + size > BOARD_SIZE) {
            return false;
        }

        if (orientation == ShipOrientation.VERTICAL && y + size > BOARD_SIZE) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            int posX = (orientation == ShipOrientation.HORIZONTAL) ? x + i : x;
            int posY = (orientation == ShipOrientation.HORIZONTAL) ? y : y + i;

            if (cells[posX][posY].getStatus() != CellStatus.EMPTY) {
                return false;
            }
        }

        return true;
    }

    public void printOpponentBoard() {
        System.out.println("Opponent's Board:");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (cells[i][j].isHit()) {
                    System.out.print(cells[i][j].getStatus() == CellStatus.SHIP ? "X " : "O ");
                } else {
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
    }
    // Modify the printOwnBoard method in the Board class
    // Add this method to the Board class
    public void printOwnBoard() {
        System.out.println("Your Board:");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (cells[i][j].getStatus() == CellStatus.SHIP) {
                    System.out.print("& ");
                } else {
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
    }



    public boolean allShipsDestroyed() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (cells[i][j].getStatus() == CellStatus.SHIP) {
                    return false;
                }
            }
        }
        return true;
    }

    public void placeShip(int x, int y, Ship ship) {
        int size = ship.getSize();
        ShipOrientation orientation = ship.getOrientation();
        if (orientation == ShipOrientation.HORIZONTAL) {
            for (int i = 0; i < size; i++) {
                cells[x + i][y].setStatus(CellStatus.SHIP);
            }
        } else {
            for (int j = 0; j < size; j++) {
                cells[x][y + j].setStatus(CellStatus.SHIP);
            }
        }
    }
}