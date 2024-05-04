public class Weapon3x3 extends Weapon<Cell> {
    @Override
    public void destroy(Cell target) {
        if (target.getStatus() == CellStatus.SHIP) {
            target.setStatus(CellStatus.HIT);
        }
    }
}
