public class Weapon1x8 extends Weapon<Cell> {
    @Override
    public void destroy(Cell target) {
        if (target.getStatus() == CellStatus.SHIP) {
            target.setStatus(CellStatus.HIT);
        }
    }
}
