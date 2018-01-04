package lab3.Misc;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lab3.CustomCanvas.CustomCanvas;

public class Light {
    private int r, g, b;

    public Light(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void fillPolygon(CustomCanvas canvas, Vector p1, Vector p2, Vector p3, Color col, double inten) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double spec = Math.pow(inten, 32);
        int nr = (int) (col.getRed() * inten + this.r * inten + 255 * spec * inten);
        int ng = (int) (col.getGreen() * inten + this.g * inten + 255 * spec * inten);
        int nb = (int) (col.getBlue() * inten + this.b * inten + 255 * spec * inten);

        gc.setFill(
                Color.rgb(
                        Math.min(nr, 255),
                        Math.min(ng, 255),
                        Math.min(nb, 255)
                )
        );

        Vector t1 = canvas.getState().modify(p1);
        Vector t2 = canvas.getState().modify(p2);
        Vector t3 = canvas.getState().modify(p3);
        double h = t1.getH();
        int xCenter = (int) canvas.getWidth() / 2;
        int yCenter = (int) canvas.getHeight() / 2;

        gc.fillPolygon(
                new double[]{t1.getX() * h + xCenter, t2.getX() * h + xCenter, t3.getX() * h + xCenter},
                new double[]{-t1.getY() * h + yCenter + 50, -t2.getY() * h + yCenter + 50, -t3.getY() * h + yCenter + 50},
                3);
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setB(int b) {
        this.b = b;
    }
}
