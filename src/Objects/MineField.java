package Objects;

import Objects.BaseObjects.Field;

public class MineField {

    private final int width, height;
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
     * Helping Method for showNeighbours, opens all 8 neighbouring Cells.
     *
     * @param x index x
     * @param y index y
     */
    private void checkNeighbours(int x, int y) {
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (j >= 0 && i >= 0 && j < width && i < height) {
                    getFromField(i, j).setChecked(true);
                }


            }
        }
    }

    /**
     * Helping function for showNeighbours
     *
     * @param startingPointChanging starting coordinate of line along the orientation of the line
     * @param startingPointConstant starting coordinate of line that stays constant
     * @param dimensions            height and width of the field. The first in the array will be the primary line, of which all
     *                              perpendicular and intersecting lines will be opened. So if startingPointChanging is an x-coordinate,
     *                              then the first entry in dimensions should be the width.
     */
    private void openLine(int startingPointChanging, int startingPointConstant, int[] dimensions) {
        for (int i = startingPointChanging; i < width && getFromField(i, startingPointConstant).getType() == CellType.Empty; i++) {
            /*
             * One for-loop for getting all Cells above, and one for all Cells below the horizontal line
             */
            for (int j = startingPointConstant; j >= 0 && getFromField(i, j).getType() == CellType.Empty; j--) {
                checkNeighbours(i, j);
            }
            for (int j = startingPointConstant; j < height && getFromField(i, j).getType() == CellType.Empty; j++) {
                checkNeighbours(i, j);
            }
            checkNeighbours(i, startingPointConstant);
        }
    }

    /**
     * Called upon a click on an empty Cell(whose Coords are the argument of showNeighbours.) Will open all neighbouring empty cells and any adjacent number Cells.
     * Not using a recursive function for performance reasons. Instead, the method will first find the leftmost nearest Cell containing a number,
     * then go along the horizontal line of the selected Cell and open the neighbours for each empty Cell on each vertical line
     * that is perpendicular to and intersecting with the horizontal line. Same procedure is then repeated for the vertical line of the selected Cell.
     */

    public void showNeighbours(int x, int y) {

        /* TODO: Testing once the other classes are finished.

         * Working with horizontal line of the called Cell ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        /*
         Finding the coordinate of the leftmost empty cell on the horizontal line
         */
        int horizontalLineX = 0;
        if (x > 0) {
            for (int i = x; i > 0 && getFromField(i - 1, y).getType() == CellType.Empty; i--) {
                horizontalLineX = i - 1;
            }
        }/*
         * Opening the neighbours for each Cell on each vertical, perpendicular line intersecting with the horizontal line of the original Cell
         */

        openLine(horizontalLineX, y, new int[]{width, height});
        /*
         * Working with vertical line of the called Cell ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        /*
         Finding the coordinate of the uppermost empty cell on the vertical line
         */
        int verticalLineY = 0;
        if (y > 0) {
            for (int i = y; i > 0 && getFromField(x, i).getType() == CellType.Empty; i--) {
                verticalLineY = i - 1;
            }
        }
        /*
         * Opening the neighbours for each Cell on each horizontal, perpendicular line intersecting with the vertical line of the original Cell
         */
        openLine(verticalLineY, x, new int[]{height, width});
    }
}
