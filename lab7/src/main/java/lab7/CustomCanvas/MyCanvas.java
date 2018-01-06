package lab7.CustomCanvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyCanvas extends Canvas {
    private double initWidth;
    private double initHeight;

    public MyCanvas(double width, double height) {
        super(width, height);
        initWidth = width;
        initHeight = height;
    }

    @Override
    public final void resize(double width, double height) {
        setWidth(width);
        setHeight(height);
        init();
    }

    @Override
    public final boolean isResizable() {
        return true;
    }

    public void init() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, getWidth(), getHeight());
    }
}
