package objects;

public class Difficulty {

    private final int width;
    private final int height;
    private final int amountMines;


    /**
     * The Difficulty for the Minefield Class
     *
     * @param width       of the Minefield
     * @param height      of the Minefield
     * @param amountMines of the Minefield
     * @see MineField
     */
    public Difficulty(int width, int height, int amountMines) {
        this.width = width;
        this.height = height;
        this.amountMines = amountMines;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getAmountMines() {
        return amountMines;
    }

    @Override
    public String toString() {
        return "Difficulty{" +
                "width=" + width +
                ", height=" + height +
                ", amountMines=" + amountMines +
                '}';
    }
}
