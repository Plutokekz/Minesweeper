package Objects;

public class Cell {
    private CellType type;
    private boolean checked;
    private boolean isMarked = false;
    private int neighbourMines;


    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public int getNeighbourMines() {
        return neighbourMines;
    }

}