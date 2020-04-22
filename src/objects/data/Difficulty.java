package objects.data;

import objects.minefield.MineField;
import objects.minefield.MineFieldState;
import objects.type.DifficultyType;

public class Difficulty extends MineFieldState {

    private final DifficultyType type;

    /**
     * The Difficulty for the Minefield Class
     *
     * @param width       of the Minefield
     * @param height      of the Minefield
     * @param amountMines of the Minefield
     * @see MineField
     */
    public Difficulty(int width, int height, int amountMines, DifficultyType type) {
        super(width, height, amountMines);
        this.type = type;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Difficulty) {
            Difficulty difficulty = (Difficulty) other;
            return difficulty.getAmountMines() == this.getAmountMines() && difficulty.getHeight() == this.getHeight() && difficulty.getWidth() == this.getWidth();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Difficulty{" +
                "width=" + super.getWidth() +
                ", height=" + super.getHeight() +
                ", amountMines=" + super.getAmountMines() +
                '}';
    }

    public DifficultyType getType() {
        return this.type;
    }
}
