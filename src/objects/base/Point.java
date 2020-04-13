package objects.base;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * compares the x and y value of the points
     *
     * @param other Point
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Point) {
            Point point = (Point) other;
            return this.x == point.x && this.y == point.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
