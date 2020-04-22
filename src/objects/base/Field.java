package objects.base;

import objects.data.Cell;

public class Field {

    private final Cell[][] field;

    public Field(int width, int height) {
        this.field = new Cell[height][width];
    }

    /**
     * @param x coordinate of the Cell
     * @param y coordinate of the Cell
     * @return Cell
     */
    public Cell getCellFromField(int x, int y) {
        if (x < 0 || y < 0 || y > field.length || x > field[y].length) {
            throw new IndexOutOfBoundsException("The Coordinates are outside the filed x: " + x + " y: " + y);
        }
        return field[y][x];
    }

    public void setCellInField(int x, int y, Cell value) {
        field[y][x] = value;
    }

    /**
     * Returns all 8 neighbour Cells for the given Coordinates, if they exists
     *
     * @param x Coordinate of the Cell
     * @param y Coordinate of the Cell
     * @return Cell[] null here no cell exists with the length 8
     */
    public Cell[] getNeighbourCells(int x, int y) {
        Cell[] neighbours = new Cell[8];
        int index = 0;
        for (Point point : Points.cellNeighbourPoints) {
            x = x + point.getX();
            y = y + point.getY();
            if (x >= 0 && y >= 0 && y < field.length && x < field[y].length) {
                neighbours[index] = getCellFromField(x, y);
            } else neighbours[index] = null;
        }
        return neighbours;
    }

}
