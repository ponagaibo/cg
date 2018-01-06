package lab7.Misc;

public class TVector {
    private double x, y, h;

    public TVector(double x, double y, double h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }

    public TVector(TVector v) {
        this.x = v.getX();
        this.y = v.getY();
        this.h = v.getH();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getH() {
        return h;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
