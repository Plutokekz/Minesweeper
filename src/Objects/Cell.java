package Objects;

import Objects.assets.sprites.Sprites;

import java.awt.image.BufferedImage;

public class Cell {
    private CellType type;
    private boolean checked;
    private boolean isMarked = false;
    private int neighbourMines;


    public Cell(CellType type) {
        this.type = type;
    }

    public Cell(CellType type, int neighbourMines) {
        this.type = type;
        this.neighbourMines = neighbourMines;
    }

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

    public void setNeighbourMines(int neighbourMines) {
        this.neighbourMines = neighbourMines;
    }

    public BufferedImage getSprite() {
        switch (type) {
            case Mine:
                return Sprites.SpriteMineTileBuffered;
            case DoesNotExist:
                return null;
            case Number:
                return Sprites.getSpriteNumberTile(neighbourMines);
            case Empty:
                return Sprites.SpriteCellEmptyTile;
            default:
                return Sprites.SpriteDefaultTile;
        }
    }

    @Override
    public String toString() {
        return "Cell{" +
                "type=" + type +
                ", checked=" + checked +
                ", isMarked=" + isMarked +
                ", neighbourMines=" + neighbourMines +
                '}';
    }
}
