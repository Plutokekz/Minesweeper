package Objects;


import Objects.Temp.Field;
import Objects.Temp.Point;
import Objects.Temp.Points;

public class MineHandler {

    int width, height, amountMines;
    private Field field;

    public MineHandler(int width, int height, int amountMines) /*throws MineLimitsException*/ {
        this.width = width;
        this.height = height;
        this.amountMines = amountMines;
        this.field = new Field(width, height);
        /* When the user can set the map size and the number of mines. (update)
        if (amountMines >= width * height) {
            throw new MineLimitsException("The number of mines should be smaller than the size of the map");
        }*/
    }

    public Cell[][] generateMines(int x, int y) {
        double probabilityMine = (double) amountMines / ((double) width * (double) height);

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                field.setCellInField(w, h, new Cell(CellType.Empty));
            }
        }
        /*
         * Set the first clicked cell and its neighbors to empty spaces
         *
         * @param x value of the first mouse click coordinate
         * @param y value of the first mouse click coordinate
         */
        field.setCellInField(x, y, new Cell(CellType.FirstClick));
        for (Point neighbourPoints: Points.cellNeighbourPoints) {
            int calcX = x + neighbourPoints.getX();
            int calcY = y + neighbourPoints.getY();
            if (calcY >= 0 && calcX >= 0 && calcY < height && calcX < width) {
                field.setCellInField(calcX, calcY, new Cell(CellType.FirstClick));
            }
        }

        while (amountMines > 0) {

            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {

                    //Exclude the first clicked cells
                    if (field.getCellFromField(w, h).getType() != CellType.FirstClick) {

                        //Prevents mines from being placed more than 6 in a row(ChainMine)
                        if (checkChainMine(w, h) == 5) {
                            if (field.getCellFromField(w, h).getType() == CellType.Number) {
                                field.setCellInField(w, h, new Cell(CellType.Number, field.getCellFromField(w, h).getNeighbourMines() + 1));
                            } else {
                                field.setCellInField(w, h, new Cell(CellType.Number, 1));
                            }
                        } else {
                            //Mine
                            if (amountMines > 0 && Math.random() < probabilityMine && field.getCellFromField(w, h).getType() != CellType.Mine) {
                                if (field.getCellFromField(w, h).getType() != CellType.FirstClick) {
                                    Point minePoint = new Point(w, h);
                                    field.setCellInField(minePoint, new Cell(CellType.Mine));
                                }

                                //Increase numbers in neighboring cells of mine
                                for (Point neighbourPoints: Points.cellNeighbourPoints){
                                    int nextW = w + neighbourPoints.getX();
                                    int nextH = h + neighbourPoints.getY();
                                    if (nextW >= 0 && nextH >= 0 && nextW < width && nextH < height){
                                        switch (field.getCellFromField(nextW, nextH).getType()) {
                                            case Mine:
                                                break;
                                            case FirstClick:
                                                field.setCellInField(nextW, nextH, new Cell(CellType.FirstClick, field.getCellFromField(nextW, nextH).getNeighbourMines() + 1));
                                                break;
                                            case Number:
                                                field.setCellInField(nextW, nextH, new Cell(CellType.Number, field.getCellFromField(nextW, nextH).getNeighbourMines() + 1));
                                                break;
                                            default:
                                                field.setCellInField(nextW, nextH, new Cell(CellType.Number, 1));
                                                break;
                                        }
                                    }
                                }
                                amountMines--;
                            }
                        }
                    }
                }
            }
        }
        //When there are no numbers or mines in the cell
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if (field.getCellFromField(w, h).getType() == CellType.FirstClick) {
                    if (field.getCellFromField(w, h).getNeighbourMines() == 0) {
                        field.setCellInField(w, h, new Cell(CellType.Empty));
                    }else{
                        field.setCellInField(w, h, new Cell(CellType.Number, field.getCellFromField(w, h).getNeighbourMines()));
                    }
                }
            }
        }
        return field.getField();
    }


    //Check if there are 5 mines already lined up
    private int checkChainMine(int w, int h) {
        int countH = 0;
        int countV = 0;
        for (int i = w - 5; i < w; i++) {
            if (i >= 0 && i < width) {
                if (field.getCellFromField(i, h).getType() == CellType.Mine) {
                    countH++;
                }
            }
        }
        for (int j = h - 5; j < h; j++) {
            if (j >= 0 && j < height) {
                if (field.getCellFromField(w, j).getType() == CellType.Mine) {
                    countV++;
                }
            }
        }
        return Math.max(countH, countV);
    }
}
