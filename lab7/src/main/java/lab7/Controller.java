package lab7;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lab7.CustomCanvas.MyCanvas;
import lab7.Misc.TCircle;
import lab7.Misc.TMatrix;
import lab7.Misc.TVector;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    @FXML
    private AnchorPane center;

    @FXML
    private TextField in;

    @FXML
    private Button save;

    @FXML
    private Button load;

    @FXML
    private void initialize() {
        ArrayList<TCircle> points = new ArrayList<>();
        points.add(new TCircle(new TVector(-10, 0, 10), 5, Color.PINK));
        points.add(new TCircle(new TVector(-5, 0, 10), 5, Color.PINK));
        points.add(new TCircle(new TVector(5, 0, 10), 5, Color.PINK));
        points.add(new TCircle(new TVector(10, 0, 10), 5, Color.PINK));

        TMatrix state = new TMatrix(new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}}
        );
        Graphic graphic = new Graphic(points);

        double width = center.getPrefWidth();
        double height = center.getPrefHeight();

        MyCanvas canvas = new MyCanvas(width, height);

        AnchorPane.setTopAnchor(canvas, 0.0);
        AnchorPane.setBottomAnchor(canvas, 0.0);
        AnchorPane.setLeftAnchor(canvas, 0.0);
        AnchorPane.setRightAnchor(canvas, 0.0);

        canvas.widthProperty().addListener((observableValue, number, newWidth) -> {
            double scale = newWidth.doubleValue() / width;
            state.getMatrix()[2][2] = scale;
        });
        canvas.heightProperty().addListener((observableValue, number, newHeight) -> {
            double scale = newHeight.doubleValue() / height;
            state.getMatrix()[2][2] = scale;
        });

        final int[] pressed = {-1};
        final int[] curX = {-1};
        final int[] curY = {-1};

        center.setOnMouseClicked(mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) -mouseEvent.getY();
            int xCenter = (int) canvas.getWidth() / 2;
            int yCenter = (int) canvas.getHeight() / 2;
            x -= xCenter;
            y += yCenter;
            for (int i = 0; i < points.size(); i++) {
                TVector curPoint = new TVector(points.get(i).getCenter());
                curPoint = state.transform(curPoint);
                double d2 = Math.pow(curPoint.getX() * curPoint.getH() - x, 2) + Math.pow(curPoint.getY() * curPoint.getH() - y, 2);
                double r = points.get(i).getRadius();
                if (d2 < r * r) {
                    pressed[0] = i;
                    curX[0] = (int) mouseEvent.getX();
                    curY[0] = (int) mouseEvent.getY();
                    break;
                }
            }
            canvas.init();
            graphic.draw(canvas, state);
        });

        save.setOnAction((actionEvent -> {
            File file = new File(in.getText());
            try {
                FileWriter writer = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                for (TCircle circle : points) {
                    TVector cent = circle.getCenter();
                    bufferedWriter.write(cent.getX() + " " + cent.getY() + "\n");
                }
                bufferedWriter.close();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        load.setOnAction(actionEvent -> {
            File file = new File(in.getText());
            try {
                Scanner scan = new Scanner(file);
                points.clear();
                String str;
                for (int i = 0; i < 4; i++) {
                    str = scan.nextLine();
                    String[] arr = str.split(" ");
                    double x, y;
                    x = Double.valueOf(arr[0]);
                    y = Double.valueOf(arr[1]);
                    points.add(new TCircle(new TVector(x, y, 10), 5, Color.PINK));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            graphic.update();
            canvas.init();
            graphic.draw(canvas, state);
        });

        center.setOnMouseDragged(mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            if (pressed[0] != -1) {
                TCircle circle = points.get(pressed[0]);
                TVector r = circle.getCenter();
                double diffX = x - curX[0];
                double diffY = y - curY[0];

                r.setX(r.getX() + diffX);
                r.setY(r.getY() - diffY);
                curX[0] = x;
                curY[0] = y;
                graphic.update();
                canvas.init();
                graphic.draw(canvas, state);
            }
        });
        center.setOnMouseReleased(mouseEvent -> pressed[0] = -1);
        center.getChildren().add(canvas);
    }
}
