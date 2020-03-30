package Objects;

public class MineField {

    private int amountMines, minesRemaining, width, height;
    private boolean lost = false, win = false;

    private Cell[][] field;
    private MineHandler mineHandler;


    public MineField(int width, int height, int amountMines, MineHandler mineHandler) {
        this.width = width;
        this.height = height;
        this.amountMines = amountMines;
        this.mineHandler = mineHandler;
    }

    /**
     * Generates a new minefield
     **/
    public void generateMines(int amountMines) {
        field = mineHandler.generateMines(width, height, amountMines);
    }

    /**
     * Resets the game after a loos or something else
     * generates a new field
     **/
    private void reset() {
        generateMines(amountMines);
        lost = false;
    }

    public boolean gameState() {
        //TODO
        /* Do we really need this method ? */
        return false;
    }

    /**
     * Called when the right mouse button gets pressed
     *
     * Marks or UnMarks a Cell, if the mine i actually a
     * Mine the minesRemaining counter gets increased for an
     * UnMark and decreased for a Mark
     *
     * @param x value of the mouse Coordinates
     * @param y value of the mouse Coordinates
     **/
    public void rightClick(int x, int y) {
        Cell cellToMark = getFromField(x, y);
        if (cellToMark.isMarked()) {
            cellToMark.setMarked(false);
            if (cellToMark.getType() == CellType.MINE) {
                minesRemaining++;
            }
        } else {
            cellToMark.setMarked(true);
            if (cellToMark.getType() == CellType.MINE) {
                minesRemaining--;
                if (minesRemaining == 0) {
                    win = true;
                }
            }
        }
    }

    /**
     * Gets the right Cell from the 2D Matrix,
     * because of the different mapping the x and y values are changed
     *
     * normal Coordinate system:
     * 1
     *
     * 0    1
     *
     * matrix Coordinate system:
     * 0    1
     *
     * 1
     *
     * @param x index for the field
     * @param y index for the field
     * @return Cell from the field
     * @throws IndexOutOfBoundsException if the x or the y value is out of range
     **/
    private Cell getFromField(int x, int y) {
        if (x < 0 || y < 0 || y > height || x > width) {
            throw new IndexOutOfBoundsException("The Coordinates are outside the filed x: " + x + " y: " + y);
        }
        return field[y][x];
    }

    /**
     * Called when the left mouse button gets pressed
     *
     * ends the game if the CellType is of type Mine
     * shows the neighbours if the CellType is of Empty
     * shows the Cell if the type if of Number
     *
     * @param x value of the mouse Coordinates
     * @param y value of the mouse Coordinates
     **/
    public void leftClick(int x, int y) {
        Cell cellClicked = getFromField(x, y);
        switch (cellClicked.getType()) {
            case MINE:
                lost = true;
                cellClicked.setChecked(true);
                //TODO run some method to end the game
                break;
            case EMPTY:
                showNeighbours(x , y);
                //TODO update Visuals
                break;
            case NUMBER:
                cellClicked.setChecked(true);
                //TODO update Visuals
                break;
            case DOES_NOT_EXIST:
                //TODO nothing happens here
                break;
        }
    }

    /**
     *  Helping Method for showNeighbours, opens all 8 neighbouring Cells.
     * @param x index x
     * @param y index y
     */
    private void checkNeighbours(int x, int y){
        for(int i = x - 1;i < x + 2; i++){
            for(int j = y - 1;j < y + 2; j++){
                try{
                    getFromField(i, j).setChecked(true);
                    /**
                      Using try-catch as a control structure in Java isn't good. Might change it later to instead have 5 seperate
                      blocks for the edge cases (left, top, right, down or none of those). Readability would suffer.
                      */
                } catch(IndexOutOfBoundsException e) {}
            }
        }
    }

    /**
     * Helping function for showNeigbours
     *
     * @param startingPointChanging starting coordinate of line along the orientation of the line
     * @param startingPointConstant starting coordinate of line that stays constant
     * @param dimensions height and width of the field. The first in the array will be the primary line, of which all
     *                   perpendicular and intersecting lines will be opened. So if startingPointChanging is an x-coordinate,
     *                   then the first entry in dimensions should be the width.
     */
    private void openLine(int startingPointChanging, int startingPointConstant, int[] dimensions){
        for (int i = startingPointChanging; i < width && getFromField(i, startingPointConstant).getType() == CellType.EMPTY; i++){
            /**
             * One for-loop for getting all Cells above, and one for all Cells below the horizontal line
             */
            for(int j = startingPointConstant; j >= 0 && getFromField(i, j).getType() == CellType.EMPTY; j--){
                checkNeighbours(i, j);
            }
            for(int j = startingPointConstant; j < height && getFromField(i, j).getType() == CellType.EMPTY; j++){
                checkNeighbours(i, j);
            }
            checkNeighbours(i, startingPointConstant);
        }
    }

    /** Called upon a click on an empty Cell(whose Coords are the argument of showNeighbours.) Will open all neighbouring empty cells and any adjacent number Cells.
     * Not using a recursive function for perfomance reasons. Instead, the method will first find the leftmost nearest Cell containing a number,
     * then go along the horizontal line of the selected Cell and open the neighbours for each empty Cell on each vertical line
     * that is perpendicular to and intersecting with the horizontal line. Same procedure is then repeated for the vertical line of the selected Cell.
     */

    public void showNeighbours(int x, int y) {

        /** TODO: Testing once the other classes are finished.

         * Working with horizontal line of the called Cell ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        /**
         Finding the coordinate of the leftmost empty cell on the horizontal line
         */
        int horizontalLineX = 0;
        if (x > 0) {
            for (int i = x; i > 0 && getFromField(i - 1, y).getType() == CellType.EMPTY; i--){
                horizontalLineX = i - 1;
            }
        }/**
         * Opening the neighbours for each Cell on each vertical, perpendicular line intersecting with the horizontal line of the original Cell
         */

        openLine(horizontalLineX, y,new int[] {width, height});
        /**
         * Working with vertical line of the called Cell ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        /**
         Finding the coordinate of the uppermost empty cell on the vertical line
         */
        int verticalLineY = 0;
        if (y > 0) {
            for (int i = y; i > 0 && getFromField(x, i).getType() == CellType.EMPTY; i--){
                verticalLineY = i - 1;
            }
        }
        /**
         * Opening the neighbours for each Cell on each horizontal, perpendicular line intersecting with the vertical line of the original Cell
         */
        openLine(verticalLineY, x,new int[] {height, width});
    }
}
