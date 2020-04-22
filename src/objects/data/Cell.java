package objects.data;

import objects.assets.TileHandler;
import objects.base.Point;
import objects.type.CellType;

import java.awt.image.BufferedImage;

public class Cell extends Point {
    private final CellType type;
    private boolean checked;
    private boolean isMarked = false;
    private int neighbourMines;


    public Cell(CellType type, int x, int y) {
        super(x, y);
        this.type = type;
    }

    public Cell(CellType type, int neighbourMines, int x, int y) {
        super(x, y);
        this.type = type;
        this.neighbourMines = neighbourMines;
    }

    public CellType getType() {
        return type;
    }

    /**
     * @return true if the cell got clicked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * sets the checked boolean to true
     * should be done after it got clicked
     */
    public void setChecked() {
        this.checked = true;
    }

    /**
     * @return true if the cell got marked by the player, false if the cell is not marked
     */
    public boolean isMarked() {
        return isMarked;
    }

    /**
     * Set the marked boolean to true if the cell gets marked by the player.
     * Set the marked boolean to false if the cell gets unmarked by the player.
     *
     * @param marked the cell
     */
    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public int getNeighbourMines() {
        return neighbourMines;
    }

    /**
     * Return a sprite depending on the CellType
     *
     * @return BufferedImage
     * @see CellType
     */
    public BufferedImage getSprite() {
        switch (type) {
            case Mine:
                return TileHandler.SPRITE_MINE;
            case DoesNotExist:
                return null;
            case Number:
                return TileHandler.getSpriteNumberTile(neighbourMines);
            case Empty:
                return TileHandler.SPRITE_BOTTOM;
            default:
                return TileHandler.SPRITE_DEFAULT;
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
