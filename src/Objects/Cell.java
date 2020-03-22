package Objects;

public class Cell {
    private int type;
    private boolean checked;
    private boolean isMarked;
    private int neighbourMines;


    public int getType() {
        return type;
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
