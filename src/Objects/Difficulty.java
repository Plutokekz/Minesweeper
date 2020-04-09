package Objects;

public class Difficulty {

    private final int width;
    private final int height;
    private final int amountMines;


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
}
