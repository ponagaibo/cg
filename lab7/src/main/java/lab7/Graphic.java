package lab7;

import javafx.scene.paint.Color;
import lab7.CustomCanvas.MyCanvas;
import lab7.Misc.TCircle;
import lab7.Misc.TMatrix;
import lab7.Misc.TVector;

import java.util.ArrayList;
import java.util.List;

public class Graphic {
    private ArrayList<TCircle> core;
    private ArrayList<TVector> points;

    Graphic(ArrayList<TCircle> core) {
        this.core = core;
        this.points = points();
    }

    private ArrayList<TVector> points() {
        ArrayList<TVector> res = new ArrayList<>();
        double cur = core.get(0).getCenter().getX();
        double end = core.get(core.size() - 1).getCenter().getX();
        for (TCircle circle : core) {
            cur = Math.min(cur, circle.getCenter().getX());
            end = Math.max(end, circle.getCenter().getX());
        }
        double step = 0.02;
        while (cur < end) {
            cur += step;
            double y = 0;
            for (int i = 0; i < core.size(); i++) {
                y += core.get(i).getCenter().getY() * li(cur, i);
            }
            res.add(new TVector(cur, y, 10));
        }
        return res;
    }

    private double li(double x, int i) {
        double res = 1;
        double xi = core.get(i).getCenter().getX();
        for (int j = 0; j < core.size(); j++) {
            if (j == i)
                continue;
            double xj = core.get(j).getCenter().getX();
            res *= (x - xj) / (xi - xj);
        }
        return res;
    }

    public void update() {
        this.points = points();
    }

    void draw(MyCanvas canvas, TMatrix state) {
        List<TVector> points = this.points;
        if (points.isEmpty()) {
            return;
        }
        Drawer drawer = new Drawer();
        drawer.drawPoints(canvas, points, state, Color.RED);
        drawer.drawCircle(canvas, core, state);
    }
}
