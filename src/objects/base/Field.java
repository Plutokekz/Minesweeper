package objects.base;

import objects.Cell;

public class Field {

    private final Cell[][] field;

    public Field(int width, int height) {
        this.field = new Cell[height][width];
    }

    public Cell getCellFromField(int x, int y){
        return field[y][x];
    }

    public void setCellInField(int x, int y, Cell value){
        field[y][x] = value;
    }

}
