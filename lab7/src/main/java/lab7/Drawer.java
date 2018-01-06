package lab7;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import lab7.CustomCanvas.MyCanvas;
import lab7.Misc.TCircle;
import lab7.Misc.TMatrix;
import lab7.Misc.TVector;

import java.util.List;

public class Drawer {

    public Drawer() {
    }

    void drawPoints(MyCanvas canvas, List<TVector> points, TMatrix state, Color col) {
        for (int i = 1; i < points.size(); i++) {
            TVector t1 = points.get(i - 1);
            TVector t2 = points.get(i);
            TVector firstPoint = state.transform(t1);
            TVector secondPoint = state.transform(t2);
            line(canvas, firstPoint, secondPoint, col);
        }
    }

    void drawCircle(MyCanvas canvas, List<TCircle> points, TMatrix state) {
        for (int i = 1; i < points.size(); i++) {
            TCircle t1 = points.get(i - 1);
            TCircle t2 = points.get(i);
            TVector nc1 = new TVector(t1.getCenter().getX(), t1.getCenter().getY(), t1.getCenter().getH());
            TCircle nw1 = new TCircle(state.transform(nc1), t1.getRadius(), t1.getCol());
            TVector nc2 = new TVector(t2.getCenter().getX(), t2.getCenter().getY(), t2.getCenter().getH());
            TCircle nw2 = new TCircle(state.transform(nc2), t2.getRadius(), t2.getCol());
            circle(canvas, nw1, nw2, nw1.getCol(), nw2.getCol());
        }
    }

    private void line(MyCanvas canvas, TVector p1, TVector p2, Paint color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int xCenter = (int) canvas.getWidth() / 2;
        int yCenter = (int) canvas.getHeight() / 2;
        gc.setStroke(color);
        double h1 = p1.getH();
        double h2 = p2.getH();
        gc.strokeLine(
                p1.getX() * h1 + xCenter, -p1.getY() * h1 + yCenter,
                p2.getX() * h2 + xCenter, -p2.getY() * h2 + yCenter);
    }

    private void circle(MyCanvas canvas, TCircle one, TCircle two, Color color1, Color color2) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int xCenter = (int) canvas.getWidth() / 2;
        int yCenter = (int) canvas.getHeight() / 2;
        gc.setFill(color1);
        TVector center1 = one.getCenter();
        int x1 = (int) center1.getX();
        int y1 = (int) center1.getY();
        TVector center2 = two.getCenter();
        int x2 = (int) center2.getX();
        int y2 = (int) center2.getY();
        line(canvas, center1, center2, Color.BLACK);
        gc.fillOval(xCenter + x1 * center1.getH() - one.getRadius(), -y1 * center1.getH() + yCenter - one.getRadius(), 10, 10);
        gc.setFill(color2);
        gc.fillOval(xCenter + x2 * center2.getH() - two.getRadius(), -y2 * center2.getH() + yCenter - two.getRadius(), 10, 10);
    }
}
