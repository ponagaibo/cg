package lab3;

import lab3.CustomCanvas.CustomCanvas;
import lab3.Misc.Vector;
import java.util.ArrayList;

public class Figure {
    private ArrayList<ArrayList<Vector>> levels;
    private double h, r;
    private int approx, sides;

    Figure(double r, int sides) {
        this.levels = new ArrayList<>();
        this.r = r;
        this.sides = sides;
        this.h = 100;
        this.approx = 10;
    }

    private void generatePoints() {
        double part = 0;
        for (int i = 0; i <= approx; i++) {
            double radius = getR();
            double cur = 0;
            ArrayList<Vector> lev = new ArrayList<>();
            for (int j = 0; j < sides; j++) {
                double x = radius * Math.cos(cur * Math.PI / 180);
                double y = radius * Math.sin(cur * Math.PI / 180);
                lev.add(new Vector(x, y, part, 1));
                cur += 360 / sides;
            }
            this.levels.add(lev);
            part += h / approx;
        }
    }

    public void draw(CustomCanvas canvas) {
        Drawer drawer = new Drawer();
        this.levels.clear();
        generatePoints();
        int n = this.levels.size();
        int sides = this.sides;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < sides; j++) {
                drawer.drawTriangle(canvas,
                        this.levels.get(i).get(j % sides),
                        this.levels.get(i).get((j + 1) % sides),
                        this.levels.get(i + 1).get(j % sides));
                drawer.drawTriangle(canvas,
                        this.levels.get(i + 1).get(j % sides),
                        this.levels.get(i + 1).get((j + 1) % sides),
                        this.levels.get(i).get((j + 1) % sides));
            }
        }
        Vector center1 = new Vector(0, 0, 0, 1);
        Vector center2 = new Vector(0, 0, levels.get(n - 1).get(0).getZ(), 1);
        for (int i = 0; i < sides; i++) {
            drawer.drawTriangle(canvas,
                    center1,
                    this.levels.get(0).get(i % sides),
                    this.levels.get(0).get((i + 1) % sides));
            drawer.drawTriangle(canvas,
                    center2,
                    this.levels.get(n - 1).get(i % sides),
                    this.levels.get(n - 1).get((i + 1) % sides));
        }
    }

    public double getR() {
        return r;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public void setApprox(int approx) {
        this.approx = approx;
    }
}
