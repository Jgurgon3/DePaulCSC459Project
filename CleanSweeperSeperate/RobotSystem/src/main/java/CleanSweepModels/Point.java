package src.main.java.CleanSweepModels;

public class Point {
    private int _x;
    private int _y;

    public Point(int x, int y) {
        this._x = x;
        this._y = y;
    }
    
    public Point Clone()
    {
    	return new Point(this.getX(),this.getX());
    }
    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public int setX(int x) {
        return this._x = x;
    }

    public int setY(int y) {
        return this._y = y;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        return (this._x == ((Point) o)._x && this._y == ((Point) o)._y);
    }

    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this._x;
        hash = 71 * hash + this._y;
        return hash;
    }
    public String toString()
    {
    	return "("+ this.getX() + "," + this.getY() + ")";
    }
}