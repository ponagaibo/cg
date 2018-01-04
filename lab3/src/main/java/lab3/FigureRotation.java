package lab3;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import lab3.CustomCanvas.CustomCanvas;
import lab3.Misc.TMatrix;

public class FigureRotation implements EventHandler<MouseEvent> {
    private CustomCanvas canvas;
    private TMatrix state;
    private double startX;
    private double startY;

    FigureRotation(CustomCanvas canv, TMatrix matr) {
        canvas = canv;
        state = matr;
        startX = canv.getWidth() / 2;
        startY = canv.getHeight() / 2;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        EventType<? extends MouseEvent> type = mouseEvent.getEventType();
        if (type == MouseEvent.MOUSE_PRESSED) {
            startX = mouseEvent.getX();
            startY = mouseEvent.getY();
            //System.out.println("click");
        } else {
            double nextX = mouseEvent.getSceneX() - startX;
            double nextY = mouseEvent.getSceneY() - startY;
            state.multiply(TMatrix.rotationX(nextY));
            state.multiply(TMatrix.rotationY(nextX));
            startX = mouseEvent.getSceneX();
            startY = mouseEvent.getSceneY();
            canvas.init();
        }
    }
}
