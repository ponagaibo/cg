package lab3;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lab3.CustomCanvas.CustomCanvas;
import lab3.Misc.TMatrix;

public class Controller {

    @FXML
    private AnchorPane center;

    @FXML
    private Slider approx;

    @FXML
    private Slider sides;

    @FXML
    private void initialize() {
        TMatrix state = new TMatrix(new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 2}}
        );

        CustomCanvas canvas = new CustomCanvas(
                center.getPrefWidth(),
                center.getPrefHeight(),
                Lab.light,
                state
        );

        Figure fig = new Figure(30, 10);
        canvas.setFig(fig);
        FigureRotation rot = new FigureRotation(canvas, state);

        AnchorPane.setTopAnchor(canvas, 0.0);
        AnchorPane.setBottomAnchor(canvas, 0.0);
        AnchorPane.setLeftAnchor(canvas, 0.0);
        AnchorPane.setRightAnchor(canvas, 0.0);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, rot);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, rot);

        approx.valueProperty().addListener((observableValue, oldV, newV) -> {
            fig.setApprox(2 * (int) newV.doubleValue());
            canvas.resize(canvas.getWidth(), canvas.getHeight());
        });
        sides.valueProperty().addListener(((observableValue, oldV, newV) -> {
            fig.setSides((int) newV.doubleValue());
            canvas.resize(canvas.getWidth(), canvas.getHeight());
        }));

        center.getChildren().add(canvas);
    }
}
