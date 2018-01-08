package kp.CustomCanvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import kp.Figure;
import kp.Misc.TMatrix;

public class MyCanvas extends Canvas {
    private TMatrix state;
    private Figure fig;
    private double initWidth;
    private double initHeight;

    public MyCanvas(double width, double height, TMatrix m) {
        super(width, height);
        state = m;
        initWidth = width;
        initHeight = height;
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public final void resize(double width, double height) {
        double scale = Math.max(width / initWidth, height / initHeight);
        state.getMatrix()[3][3] = scale;
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
        fig.draw(this);
    }

    public TMatrix getState() {
        return state;
    }

    public void setFig(Figure fig) {
        this.fig = fig;
    }
}
