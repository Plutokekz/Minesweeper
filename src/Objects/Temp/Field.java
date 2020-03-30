package Objects.Temp;

import Objects.Cell;

public class Field {

    private int width, height;
    private Cell[][] field;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.field = new Cell[height][width];
    }

    public Cell getCellFromField(int x, int y){
        return field[y][x];
    }

    public Cell getFromField(Point point){
        return getCellFromField(point.getX(), point.getY());
    }

    public void setCellInField(int x, int y, Cell value){
        field[y][x] = value;
    }

    public void setCellInField(Point point, Cell value){
        setCellInField(point.getX(), point.getY(), value);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Cell[][] getField() {
        return field;
    }
}
