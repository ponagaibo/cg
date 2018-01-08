package kp;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import kp.CustomCanvas.MyCanvas;
import kp.Misc.TMatrix;

public class RotationHandler implements EventHandler<MouseEvent> {
    private MyCanvas canvas;
    private TMatrix state;
    private double startX;
    private double startY;

    RotationHandler(MyCanvas c, TMatrix m) {
        canvas = c;
        state = m;
        startX = c.getWidth() / 2;
        startY = c.getHeight() / 2;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        EventType<? extends MouseEvent> type = mouseEvent.getEventType();
        if (type == MouseEvent.MOUSE_PRESSED) {
            startX = mouseEvent.getX();
            startY = mouseEvent.getY();
            //System.out.println("click");
        } else {
            double diffX = mouseEvent.getX() - startX;
            double diffY = mouseEvent.getY() - startY;
            state.multiply(TMatrix.rotationX(diffY));
            state.multiply(TMatrix.rotationY(diffX));
            startX = mouseEvent.getX();
            startY = mouseEvent.getY();
            canvas.init();
        }
    }
}
