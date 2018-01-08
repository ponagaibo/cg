package kp;

import kp.CustomCanvas.MyCanvas;
import kp.Misc.TMatrix;
import kp.Misc.TVector;

import java.util.ArrayList;

public class Figure {
    private ArrayList<TVector> central;
    private ArrayList<ArrayList<TVector>> points;

    Figure(ArrayList<TVector> points) {
        central = points;
        this.points = new ArrayList<>();
        generatePoints();
        rotate(10);
    }

    private void rotate(int step) {
        for (int i = step; i < 360; i += step) {
            ArrayList<TVector> layer = new ArrayList<>();
            for (int j = 0; j < this.points.get(0).size(); j++) {
                double px = points.get(0).get(j).getX();
                double py = points.get(0).get(j).getY();
                double pz = points.get(0).get(j).getZ();
                TVector tmp = new TVector(px, py, pz, 1);
                TVector tmp2 = TMatrix.rotationY(i).transform(tmp);
                layer.add(tmp2);
            }
            points.add(layer);
        }
    }

    private long combination(int n, int k) {
        if (k > n - k) {
            k = n - k;
        }
        long b = 1;
        for (int i = 1, m = n; i <= k; i++, m--) {
            b = b * m / i;
        }
        return b;
    }

    private double choose(int n, int i, double t) {
        long bi = combination(n, i);
        double res = Math.pow(t, i);
        double res2 = Math.pow(1 - t, n - i);
        return bi * res * res2;
    }

    private void generatePoints() {
        ArrayList<TVector> l = new ArrayList<>();
        for (double t = 0; t < 1; t += 0.1) {
            l.add(point(t));
        }
        points.add(l);
    }

    private TVector point(double t) {
        double tx = 0;
        double ty = 0;
        double tz = 0;
        for (int i = 0; i < 3; i++) {
            double px = central.get(i).getX();
            double py = central.get(i).getY();
            double pz = central.get(i).getZ();
            double coef = choose(3, i, t);
            tx += px * coef;
            ty += py * coef;
            tz += pz * coef;
        }
        return new TVector(tx * 7, ty * 7, tz * 7, 1);
    }

    public void draw(MyCanvas canvas) {
        Drawer drawer = new Drawer();
        int n = points.size();
        for (int i = 0; i < points.size(); i++) {
            drawer.draw(canvas, points.get(i % n), points.get((i + 1) % n));
        }
    }

    public void update(int angle) {
        points.clear();
        generatePoints();
        rotate(angle);
    }

    public void setCentral(ArrayList<TVector> central) {
        this.central = central;
    }
}
