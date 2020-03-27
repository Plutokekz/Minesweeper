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
            if (cellToMark.getType() == CellType.Mine) {
                minesRemaining++;
            }
        } else {
            cellToMark.setMarked(true);
            if (cellToMark.getType() == CellType.Mine) {
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
            case Mine:
                lost = true;
                cellClicked.setChecked(true);
                //TODO run some method to end the game
                break;
            case Empty:
                showNeighbours(x , y);
                //TODO update Visuals
                break;
            case Number:
                cellClicked.setChecked(true);
                //TODO update Visuals
                break;
            case DoesNotExist:
                //TODO nothing happens here
                break;
        }
    }

    // Helping Method for showNeighbours, opens all 9 neighbouring Cells.
    private void checkNeighbours(int x, int y){
        for(int i = x - 1;i < x + 2; i++){
            for(int j = y - 1;i < y + 2; j++){
                try{
                    getFromField(i, j).setChecked(true);
                    // Using try-catch as a control structure in Java isn't good. Might change it later to instead have 5 seperate
                    // blocks for the edge cases (left, top, right, down or none of those). Readability would suffer.
                } catch(IndexOutOfBoundsException e) {}
            }
        }
    }

    // Called upon a click on an empty Cell(whose Coords are the argument of showNeighbours.) Will open all neighbouring empty cells and any adjacent number Cells.
// Not using a recursive function for perfomance reasons. Instead, the method will first find the leftmost nearest Cell containing a number,
// then go along the horizontal line of the selected Cell and open the neighbours for each empty Cell on each vertical line
// that is perpendicular to and intersecting with the horizontal line. Same procedure is then repeated for the vertical line of the selected Cell.
    public void showNeighbours(int x, int y) {
        // TODO: Testing once the other classes are finished.

        // Working with horizontal line of the called Cell ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Finding the coordinate of the leftmost empty cell on the horizontal line
        int horizontalLineX = 0;
        if (x > 0) {
            for (int i = x;i > 0 && getFromField(i - 1, y).getType() == CellType.Empty; i--){
                horizontalLineX = i - 1;
            }
        }
        // Opening the neighbours for each Cell on each vertical, perpendicular line intersecting with the horizontal line of the original Cell

        for (i = horizontalLineX;i < width && getFromField(i, y).getType() == CellType.Empty;i++){
            // One for-loop for getting all Cells above, and one for all Cells below the horizontal line
            // All Cells above
            for(j = y;j => 0 && getFromField(i, j).getType == CellType.Empty; j--){
                checkNeighbours(i, j);
            }
            // Below
            for(j = y;j < height && getFromField(i, j).getType == CellType.Empty; j++){
                checkNeighbours(i, j);
            }
            checkNeighbours(i, y);
        }

        // Working with vertical line of the called Cell ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Finding the coordinate of the uppermost empty Cell on the vertical line
        int verticalLineY = 0;
        if (y > 0) {
            for (int i = y;i > 0 && getFromField(x, i).getType() == CellType.Empty; i--){
                verticalLineY = i - 1;
            }
        }
        // Opening the neighbours for each Cell on each horizontal, perpendicular line intersecting with the vertical line of the original Cell

        for (i = verticalLineY;i < height && getFromField(x, i).getType() == CellType.Empty;i++){
            // One for-loop for getting all Cells left, and one for all Cells right of the vertical line
            // All Cells left
            for(j = x;j => 0 && getFromField(j, i).getType == CellType.Empty; j--){
                checkNeighbours(j, i);
            }
            // Right
            for(j = x;j < width && getFromField(j, i).getType == CellType.Empty; j++){
                checkNeighbours(j, i);
            }
            checkNeighbours(x, i);
        }
    }
}