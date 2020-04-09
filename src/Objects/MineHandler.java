package Objects;


import Objects.BaseObjects.Field;
import Objects.BaseObjects.Point;
import Objects.BaseObjects.Points;

public class MineHandler {

    /**
     * This method enters mines and numbers in the fields.
     * To prevent 'NullPointerException' due to missing CellType, first set the type of all cells to 'Empty'.
     * Mines are randomly planted according to the calculated probability(probabilityMine) in the field.
     * Mines cannot be planted in the first clicked cell and its neighboring cells. ((x,y) & neighborPoints -> NoMines)
     * Mines cannot be planted more than 6 in a row. (ChainMine -> NoMines)
     * When a mine is planted in a cell, the number increases by 1 for 8 neighboring cells.
     * After all mines have been planted, cells with neither mines nor numbers are changed to 'Empty' type.
     *
     * @param x value of the first mouse click coordinate
     * @param y value of the first mouse click coordinate
     * @return Completed minefield
     */
    public static Field generateMines(int x, int y, int rows, int columns, int amountMines) {
        Field field = new Field(rows, columns);
        double probabilityMine = (double) amountMines / ((double) rows * (double) columns);

        for (int h = 0; h < columns; h++) {
            for (int w = 0; w < rows; w++) {
                field.setCellInField(w, h, new Cell(CellType.Empty));
            }
        }
        /*
         * Set the first clicked cell and its neighbors to empty spaces
         *
         * @param x value of the first mouse click coordinate
         * @param y value of the first mouse click coordinate
         */
        field.setCellInField(x, y, new Cell(CellType.NoMines));
        for (Point neighbourPoints : Points.cellNeighbourPoints) {
            int calcX = x + neighbourPoints.getX();
            int calcY = y + neighbourPoints.getY();
            if (calcY >= 0 && calcX >= 0 && calcY < columns && calcX < rows) {
                field.setCellInField(calcX, calcY, new Cell(CellType.NoMines));
            }
        }
        int minesRemaining = amountMines;
        while (minesRemaining > 0) {

            for (int h = 0; h < columns; h++) {
                for (int w = 0; w < rows; w++) {

                    if (field.getCellFromField(w, h).getType() != CellType.NoMines) {

                        /*if (checkChainMine(w, h) == 5) {
                            field.setCellInField(w, h, new Cell(CellType.NoMines, field.getCellFromField(w, h).getNeighbourMines()));
                        } else { }*/
                        if (minesRemaining > 0 && Math.random() < probabilityMine && field.getCellFromField(w, h).getType() != CellType.Mine) {
                            field.setCellInField(w, h, new Cell(CellType.Mine));

                            for (Point neighbourPoints : Points.cellNeighbourPoints) {
                                int nextW = w + neighbourPoints.getX();
                                int nextH = h + neighbourPoints.getY();
                                if (nextW >= 0 && nextH >= 0 && nextW < rows && nextH < columns) {
                                    switch (field.getCellFromField(nextW, nextH).getType()) {
                                        case Mine:
                                            break;
                                        case NoMines:
                                            field.setCellInField(nextW, nextH, new Cell(CellType.NoMines, field.getCellFromField(nextW, nextH).getNeighbourMines() + 1));
                                            break;
                                        default:
                                            field.setCellInField(nextW, nextH, new Cell(CellType.Number, field.getCellFromField(nextW, nextH).getNeighbourMines() + 1));
                                            break;
                                    }
                                }
                            }
                            minesRemaining--;
                        }

                    }
                }
            }

        }
        //When there are no numbers or mines in the cell
        for (int h = 0; h < columns; h++) {
            for (int w = 0; w < rows; w++) {
                if (field.getCellFromField(w, h).getType() == CellType.NoMines) {
                    if (field.getCellFromField(w, h).getNeighbourMines() == 0) {
                        field.setCellInField(w, h, new Cell(CellType.Empty));
                    } else {
                        field.setCellInField(w, h, new Cell(CellType.Number, field.getCellFromField(w, h).getNeighbourMines()));
                    }
                }
            }
        }
        return field;
    }


    /**
     * This method count how many mines are planted in the left(or in the top) 5 cells before the current cell.
     * This is implemented so that too many mines are not on one side.
     * A return of 5 means that mines were planted in a row.
     */
    private int checkChainMine(int w, int h, Field field, int rows, int columns) {
        int countH = 0;
        int countV = 0;
        for (int i = w - 5; i < w; i++) {
            if (i >= 0 && i < rows) {
                if (field.getCellFromField(i, h).getType() == CellType.Mine) {
                    countH++;
                }
            }
        }
        for (int j = h - 5; j < h; j++) {
            if (j >= 0 && j < columns) {
                if (field.getCellFromField(w, j).getType() == CellType.Mine) {
                    countV++;
                }
            }
        }
        return Math.max(countH, countV);
    }
}
