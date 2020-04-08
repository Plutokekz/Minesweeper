package Objects;

import Objects.BaseObjects.Field;

import java.util.LinkedList;

public class MineField {

    private int width, height;
    private final MineHandler mineHandler;
    private boolean lost = false, win = false;

    private Field field;
    private int actualMinesRemaining, minesRemaining;


    public MineField(MineHandler mineHandler, int height, int width) {
        this.height = height;
        this.width = width;
        this.mineHandler = mineHandler;
        this.actualMinesRemaining = mineHandler.getAmountMines();
        this.minesRemaining = mineHandler.getAmountMines();
    }

    public Field getField() {
        return field;
    }


    // Debugging
    public void showField() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                field.getCellFromField(x, y).setChecked(true);
            }
        }
    }

    /**
     * Marks all all the Mines as Clicked so they are shown
     */
    public void showRemainingMines() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = field.getCellFromField(x, y);
                if (cell.getType() == CellType.Mine) {
                    cell.setChecked(true);
                }
            }
        }
    }

    /**
     * Generates a new minefield
     **/
    public void generateMines(int x, int y) {
        field = mineHandler.generateMines(x, y);
    }

    /**
     * Resets the game after a loos or something else
     * generates a new field
     **/
    public void reset() {
        field = null;
        lost = false;
        this.actualMinesRemaining = mineHandler.getAmountMines();
        this.minesRemaining = mineHandler.getAmountMines();
    }

    /**
     * Generates minefield if its null
     *
     * @param x coordinate from the first Click
     * @param y coordinate from the first Click
     */
    private void firstClick(int x, int y) {
        if (field == null) {
            generateMines(x, y);
        }
    }

    public int getMinesRemaining() {
        return minesRemaining;
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
    public void rightClick(int x, int y) {

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
                            //TODO Run win Screen
                        }
                    }
                }
            }
        }
    }

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
        if (x < 0 || y < 0 || y > height || x > width) {
            throw new IndexOutOfBoundsException("The Coordinates are outside the filed x: " + x + " y: " + y);
        }
        return field.getCellFromField(x, y);
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
    public void leftClick(int x, int y) {
        if (!lost) {

            firstClick(x, y);
            Cell cellClicked = getFromField(x, y);

            switch (cellClicked.getType()) {
                case Mine:
                    lost = true;
                    cellClicked.setChecked(true);
                    showRemainingMines();
                    break;
                case Empty:
                    showNeighbours(x, y);
                    break;
                case Number:
                    cellClicked.setChecked(true);
                    break;
                case DoesNotExist:
                    break;
            }
        }
    }

    /**
     * Helping Method for showNeighbours, checks all 8 neighbouring Cells.
     *
     * @param x x coordinate of cell whose neighbours are to be opened
     * @param y y coordinate of cell whose neighbours are to be opened
     */
    private void checkNeighbours(int x, int y) {
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (j >= 0 && i >= 0 && i < width && j < height) {
                    getFromField(i, j).setChecked(true);
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

    private LinkedList<Integer> emptyUncheckedNeighbours(int x, int y){
        LinkedList<Integer> returnList = new LinkedList<>();
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (j >= 0 && i >= 0 && i < width && j < height && getFromField(i, j).getType() == CellType.Empty && !getFromField(i, j).isChecked()) {
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

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

