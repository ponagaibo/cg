package lab3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import lab3.CustomCanvas.CustomCanvas;
import lab3.Misc.Vector;

public class Drawer {

    public Drawer() {
    }

    public void drawTriangle(CustomCanvas canvas, Vector p1, Vector p2, Vector p3) {
        Vector toObserver = new Vector(0, 0, 50, 1);
        Vector t1 = canvas.getState().modify(p1);
        Vector t2 = canvas.getState().modify(p2);
        Vector t3 = canvas.getState().modify(p3);
        Vector sb1 = Vector.subtract(t1, t2);
        Vector sb2 = Vector.subtract(t1, t3);
        Vector norm = Vector.vectorProduct(sb1, sb2);
        if (Vector.mixedProduct(sb1, sb2, norm) < 0) {
            norm = Vector.vectorProduct(sb2, sb1);
        }
        Vector innerPoint = canvas.getState().modify(new Vector(0, 0, 10, 1));
        Vector toInner = Vector.subtract(t1, innerPoint);
        int kk = 1;
        if (Vector.scalarProduct(toInner, norm) < 0) {
            kk = -1;
        }
        double d = Vector.scalarProduct(toObserver, norm);
        if (kk * d < 0) {
            double inten = Math.abs(d / (toObserver.length() * norm.length()));
            canvas.getLight().fillPolygon(canvas, p1, p2, p3, Color.BLACK, inten);
            line(canvas, t1, t2, Color.BLACK);
            line(canvas, t1, t3, Color.BLACK);
            line(canvas, t2, t3, Color.BLACK);
        }
    }

    private void line(CustomCanvas canvas, Vector p1, Vector p2, Paint color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int xCenter = (int) canvas.getWidth() / 2;
        int yCenter = (int) canvas.getHeight() / 2;
        gc.setStroke(color);
        double h1 = p1.getH();
        double h2 = p2.getH();
        gc.strokeLine(
                p1.getX() * h1 + xCenter, -p1.getY() * h1 + yCenter + 50,
                p2.getX() * h2 + xCenter, -p2.getY() * h2 + yCenter + 50);
    }
}
