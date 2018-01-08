package kp.Misc;

public class TVector {
    private final double x, y, z, h;

    public TVector(double x, double y, double z, double h) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.z = z;
    }

    public final double getX() {
        return x;
    }

    public final double getY() {
        return y;
    }

    public final double getH() {
        return h;
    }

    public final double getZ() {
        return z;
    }
}
