package lab3.CustomCanvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lab3.Figure;
import lab3.Misc.Light;
import lab3.Misc.TMatrix;

public class CustomCanvas extends Canvas {
    private TMatrix state;
    private Figure fig;
    private double initWidth;
    private double initHeight;
    private Light light;

    public CustomCanvas(double width, double height, Light l, TMatrix m) {
        super(width, height);
        this.light = l;
        this.state = m;
        this.initWidth = width;
        this.initHeight = height;
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public final void resize(double width, double height) {
        double coef = Math.max(width / initWidth, height / initHeight);
        state.getMatrix()[3][3] = coef;
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
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getWidth(), getHeight());
        fig.draw(this);
    }

    public TMatrix getState() {
        return state;
    }

    public void setFig(Figure fig) {
        this.fig = fig;
    }

    public Light getLight() {
        return light;
    }
}
