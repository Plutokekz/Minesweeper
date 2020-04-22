package objects.data;

import objects.exceptions.NoCoordinatesException;
import objects.type.ActionType;

public class GameAction {

    private final ActionType type;
    private int x, y;
    private Difficulty difficulty;

    public GameAction(ActionType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public GameAction(ActionType type, Difficulty difficulty) throws NoCoordinatesException {
        if (type == ActionType.UpdateDifficulty) {
            this.type = type;
            this.difficulty = difficulty;
        } else throw new NoCoordinatesException("ClickType " + type + " must be DifficultyButton");

    }

    public GameAction(ActionType type) throws NoCoordinatesException {
        if (type == ActionType.Reset) {
            this.type = type;
        } else throw new NoCoordinatesException("ClickType " + type + " needs a x and y value");

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ActionType getType() {
        return type;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "GameAction{" +
                "x=" + x +
                ", y=" + y +
                ", type=" + type +
                '}';
    }
}
