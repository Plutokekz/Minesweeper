package objects.minefield;

public class MineFieldState {

    private final int width;
    private final int height;
    private final int amountMines;


    /**
     * The MinefieldState for the Minefield
     *
     * @param width       of the Minefield
     * @param height      of the Minefield
     * @param amountMines remaining of the Minefield
     * @see MineField
     */
    public MineFieldState(int width, int height, int amountMines) {
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
        return "MineFieldState{" +
                "width=" + width +
                ", height=" + height +
                ", amountMines=" + amountMines +
                '}';
    }

}
