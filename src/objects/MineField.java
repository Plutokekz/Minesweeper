package objects;

import objects.base.Field;
import objects.type.CellType;
import objects.type.GameState;

import java.awt.*;
import java.util.LinkedList;

public class MineField {

    private int rows, columns;
    private boolean lost = false;

    private Field field;
    private int actualMinesRemaining, minesRemaining, amountMines;
    private Difficulty difficulty;


    /**
     * Minefield
     *
     * @param difficulty in which the Field gets instantiated, can later be changed
     *                   run generateMines() to create the minefield.
     */
    public MineField(Difficulty difficulty) {
        this.columns = difficulty.getHeight();
        this.rows = difficulty.getWidth();
        this.actualMinesRemaining = difficulty.getAmountMines();
        this.minesRemaining = difficulty.getAmountMines();
        this.amountMines = difficulty.getAmountMines();
        this.difficulty = difficulty;
    }


    //show
    // Debugging
    public void showField() {
        for (int y = 0; y < columns; y++) {
            for (int x = 0; x < rows; x++) {
                field.getCellFromField(x, y).setChecked();
            }
        }
    }

    /**
     * Marks all the Mines as Clicked so they are shown in the next draw call
     */
    public void showRemainingMines() {
        for (int y = 0; y < columns; y++) {
            for (int x = 0; x < rows; x++) {
                Cell cell = field.getCellFromField(x, y);
                if (cell.getType() == CellType.Mine) {
                    cell.setChecked();
                }
            }
        }
    }


    //generate

    /**
     * Generates a new minefield
     *
     * @param x coordinate of the first Click
     * @param y coordinate of the first Click
     **/
    public void generateMines(int x, int y) {
        field = MineHandler.generateMines(x, y, rows, columns, amountMines);
    }


    //clicks

    /**
     * Generates minefield if its null
     *
     * @param x coordinate from the first Click
     * @param y coordinate from the first Click
     */
    private GameState firstClick(int x, int y) {
        if (field == null) {
            generateMines(x, y);
            return GameState.FistClick;
        }
        return GameState.Default;
    }

    /**
     * Called when the left mouse button gets pressed
     * <p>
     * ends the game if the CellType is of type Mine
     * shows the neighbours if the CellType is of Empty
     * shows the Cell if the type if of Number
     *
     * @param x value of the mouse Coordinates
     * @param y value of the mouse Coordinates
     **/
    private GameState leftClick(int x, int y) {
        GameState state = GameState.Default;
        if (!lost) {

            state = firstClick(x, y);
            Cell cellClicked = getFromField(x, y);

            switch (cellClicked.getType()) {
                case Mine:
                    lost = true;
                    cellClicked.setChecked();
                    showRemainingMines();
                    return GameState.Lose;
                case Empty:
                    showNeighbours(x, y);
                    break;
                case Number:
                    cellClicked.setChecked();
                    break;
                case DoesNotExist:
                    break;
            }
        }
        return state;
    }

    /**
     * Called when the right mouse button gets pressed
     * <p>
     * Marks or UnMarks a Cell, if the mine i actually a
     * Mine the minesRemaining counter gets increased for an
     * UnMark and decreased for a Mark
     *
     * @param x value of the mouse Coordinates
     * @param y value of the mouse Coordinates
     **/
    private GameState rightClick(int x, int y) {

        if (!lost) {
            Cell cellToMark = getFromField(x, y);
            if (!cellToMark.isChecked()) {
                if (cellToMark.isMarked()) {
                    cellToMark.setMarked(false);
                    minesRemaining++;
                    if (cellToMark.getType() == CellType.Mine) {
                        actualMinesRemaining++;
                    }
                } else {
                    cellToMark.setMarked(true);
                    minesRemaining--;
                    if (cellToMark.getType() == CellType.Mine) {
                        actualMinesRemaining--;
                        if (actualMinesRemaining == 0) {
                            System.out.println("You Won");
                            return GameState.Win;
                        }
                    }
                }
            }
        }

        return GameState.Default;
    }

    //showNeighbours

    /**
     * Helping Method for showNeighbours, checks all 8 neighbouring Cells.
     *
     * @param x x coordinate of cell whose neighbours are to be opened
     * @param y y coordinate of cell whose neighbours are to be opened
     */
    private void checkNeighbours(int x, int y) {
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (j >= 0 && i >= 0 && i < rows && j < columns) {
                    getFromField(i, j).setChecked();
                }
            }
        }
    }

    /**
     * Helping Method for showNeighbours, returns a list containing the coordinates of empty, unchecked Cells neighbouring
     * the one given as the argument.
     *
     * @param x x coordinate of cell whose neighbours are to be opened
     * @param y y coordinate of cell whose neighbours are to be opened
     */
    private LinkedList<Integer> emptyUncheckedNeighbours(int x, int y) {
        LinkedList<Integer> returnList = new LinkedList<>();
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (j >= 0 && i >= 0 && i < rows && j < columns && getFromField(i, j).getType() == CellType.Empty && !getFromField(i, j).isChecked()) {
                    returnList.addFirst(j);
                    returnList.addFirst(i);
                }
            }
        }
        return returnList;
    }

    /**
     * Called upon when an empty cell is clicked. A directly recursive method using the leftClick function leads
     * to stackoverflows, so instead this method creates a list of all empty, unchecked cells that are directly
     * connected to the clicked empty cell through empty cells. Then the neighbours of this list all get checked.
     */
    private void showNeighbours(int x, int y) {

        LinkedList<Integer> emptyCellsWithUncheckedNeighbours = new LinkedList<>();
        emptyCellsWithUncheckedNeighbours.addFirst(y);
        emptyCellsWithUncheckedNeighbours.addFirst(x);

        while (emptyCellsWithUncheckedNeighbours.size() > 0) {
            LinkedList<Integer> firstCellEmptyNeighbours = emptyUncheckedNeighbours(emptyCellsWithUncheckedNeighbours.get(0), emptyCellsWithUncheckedNeighbours.get(1));
            checkNeighbours(emptyCellsWithUncheckedNeighbours.get(0), emptyCellsWithUncheckedNeighbours.get(1));
            emptyCellsWithUncheckedNeighbours.remove();
            emptyCellsWithUncheckedNeighbours.remove();
            if (firstCellEmptyNeighbours.size() > 0) {
                emptyCellsWithUncheckedNeighbours.addAll(firstCellEmptyNeighbours);
            }
        }
    }

    //Update

    /**
     * sets the Size (width and height) and the mines of the field over the Difficulty
     *
     * @param difficulty should be a new difficulty
     *                   Run generateMines after setting a new difficulty to get a field with the new size
     */
    private void setDifficulty(Difficulty difficulty) {
        this.columns = difficulty.getHeight();
        this.rows = difficulty.getWidth();
        this.amountMines = difficulty.getAmountMines();
        this.difficulty = difficulty;
    }

    /**
     * Resets the game after a loos or something else
     * generates a new field
     **/
    private void reset() {
        field = null;
        lost = false;
        this.actualMinesRemaining = amountMines;
        this.minesRemaining = amountMines;
    }

    public GameState update(GameAction gameAction) {
        switch (gameAction.getType()) {
            case LeftMouseButton:
                return leftClick(gameAction.getX(), gameAction.getY());
            case RightMouseButton:
                return rightClick(gameAction.getX(), gameAction.getY());
            case Reset:
                reset();
                return GameState.Reset;
            case UpdateDifficulty:
                if (!gameAction.getDifficulty().equals(difficulty)) {
                    setDifficulty(gameAction.getDifficulty());
                }
                reset();
                return GameState.FieldUpdated;
            default:
                System.out.println("Unknown GameAction: " + gameAction.toString());
                return GameState.Default;
        }
    }


    //getter

    /**
     * Gets the right Cell from the 2D Matrix,
     * because of the different mapping the x and y values are changed
     * <p>
     * normal Coordinate system:
     * 1
     * <p>
     * 0    1
     * <p>
     * matrix Coordinate system:
     * 0    1
     * <p>
     * 1
     *
     * @param x index for the field
     * @param y index for the field
     * @return Cell from the field
     * @throws IndexOutOfBoundsException if the x or the y value is out of range
     **/
    public Cell getFromField(int x, int y) {
        if (x < 0 || y < 0 || y > columns || x > rows) {
            throw new IndexOutOfBoundsException("The Coordinates are outside the filed x: " + x + " y: " + y);
        }
        return field.getCellFromField(x, y);
    }

    public MineFieldState getMineFieldState() {
        return new MineFieldState(rows, columns, minesRemaining);
    }

    public String getDifficultyString() {
        switch (difficulty.getType()) {
            case EASY:
                return "easy";
            case NORMAL:
                return "normal";
            case HARD:
                return "hard";
            default:
                return "custom";
        }
    }

    public boolean isEmpty() {
        return field == null;
    }

    public Dimension getDimension() {
        return new Dimension(rows, columns);
    }
}

