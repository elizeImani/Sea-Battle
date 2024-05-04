enum ShipOrientation {
    HORIZONTAL,
    VERTICAL
}
public class Ship {
    private int size;
    private ShipOrientation orientation;
    private boolean destroyed;

    public Ship(int size, ShipOrientation orientation) {
        this.size = size;
        this.orientation = orientation;
        this.destroyed = false;
    }

    public static Ship createShip1x1(ShipOrientation orientation) {
        return new Ship(1, orientation);
    }

    public static Ship createShip1x4(ShipOrientation orientation) {
        return new Ship(4, orientation);
    }

    public static Ship createShip1x3(ShipOrientation orientation) {
        return new Ship(3, orientation);
    }

    public static Ship createShip2x1(ShipOrientation orientation) {
        return new Ship(2, orientation);
    }

    public int getSize() {
        return size;
    }

    public ShipOrientation getOrientation() {
        return orientation;
    }

    public void place(Board board, int x, int y) {
        if (!board.isValidPlacement(x, y, this)) {
            throw new InvalidPlacementException("Invalid ship placement");
        }

        if (orientation == ShipOrientation.HORIZONTAL) {
            for (int i = x; i < x + size; i++) {
                board.getCell(i, y).setStatus(CellStatus.SHIP);
            }
        } else {
            for (int j = y; j < y + size; j++) {
                board.getCell(x, j).setStatus(CellStatus.SHIP);
            }
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
