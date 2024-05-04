enum CellStatus {
    EMPTY,
    SHIP,
    HIT,
    MISS
}

public class Cell {
    private CellStatus status;

    public Cell() {
        this.status = CellStatus.EMPTY;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    public void destroy() {
        if (status == CellStatus.SHIP) {
            status = CellStatus.HIT;
        }
    }

    public boolean isHit() {
        return status == CellStatus.HIT;
    }
}
