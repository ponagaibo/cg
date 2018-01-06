package lab7.Misc;

import javafx.scene.paint.Color;

public class TCircle {
    private TVector center;
    private int radius;
    private Color col;

    public TCircle(TVector center, int radius, Color c) {
        this.center = center;
        this.radius = radius;
        this.col = c;
    }

    public TVector getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public Color getCol() {
        return col;
    }
}
