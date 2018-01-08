package kp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import kp.CustomCanvas.MyCanvas;
import kp.Misc.TVector;

import java.util.List;

public class Drawer {

    public Drawer() {
    }

    public void draw(MyCanvas canvas, TVector p1, TVector p2) {
        TVector t1 = canvas.getState().transform(p1);
        TVector t2 = canvas.getState().transform(p2);
        line(canvas, t1, t2, Color.BLACK);
    }

    public void draw(MyCanvas canvas, List<TVector> l1, List<TVector> l2) {
        for (int i = 0; i < l1.size() - 1; i++) {
            draw(canvas, l1.get(i), l1.get(i + 1));
            draw(canvas, l1.get(i), l2.get(i + 1));
            draw(canvas, l1.get(i + 1), l2.get(i + 1));
            draw(canvas, l2.get(i), l2.get(i + 1));
            draw(canvas, l2.get(i), l1.get(i));
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
}
