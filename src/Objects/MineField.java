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
                showNeighbours();
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

    public void showNeighbours() {
        //TODO
    }
}
