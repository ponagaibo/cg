package kp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import kp.CustomCanvas.MyCanvas;
import kp.Misc.TMatrix;
import kp.Misc.TVector;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    @FXML
    private AnchorPane center;

    @FXML
    private Slider approx;

    @FXML
    private Button save;

    @FXML
    private Button load;

    @FXML
    private TextField text;

    @FXML
    private void initialize() {
        TMatrix state = new TMatrix(new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 3}}
        );

        MyCanvas canvas = new MyCanvas(
                center.getPrefWidth(),
                center.getPrefHeight(),
                state
        );
        ArrayList<TVector> points = new ArrayList<>();
        points.add(new TVector(-10, -10, -5, 1));
        points.add(new TVector(-5, -10, 5, 1));
        points.add(new TVector(5, 10, 5, 1));

        Figure fig = new Figure(points);
        canvas.setFig(fig);

        RotationHandler rot = new RotationHandler(canvas, state);

        AnchorPane.setTopAnchor(canvas, 0.0);
        AnchorPane.setBottomAnchor(canvas, 0.0);
        AnchorPane.setLeftAnchor(canvas, 0.0);
        AnchorPane.setRightAnchor(canvas, 0.0);

        approx.valueProperty().addListener(((observableValue, oldV, newV) -> {
            fig.update(newV.intValue());
            canvas.resize(canvas.getWidth(), canvas.getHeight());
        }));

        load.setOnAction((e) -> {
            //System.out.println("1");
            File file = new File(text.getText());
            try {
                Scanner scan = new Scanner(file);
                double x, y, z;
                points.clear();
                for (int i = 0; i < 3; i++) {
                    String s = scan.nextLine();
                    String[] arr = s.split(" ");
                    x = Double.valueOf(arr[0]);
                    y = Double.valueOf(arr[1]);
                    z = Double.valueOf(arr[2]);
                    TVector v = new TVector(x,y,z,1);
                    points.add(v);
                }
                fig.setCentral(points);
                fig.update((int) approx.getValue());
                canvas.resize(canvas.getWidth(), canvas.getHeight());
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });

        save.setOnAction((e) -> {
            //System.out.println("2");
            File file = new File(text.getText());
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (TVector v : points) {
                    writer.write(v.getX() + " " + v.getY() + " " + v.getZ());
                    writer.write("\n");
                }
                writer.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, rot);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, rot);
        center.getChildren().add(canvas);
    }
}
