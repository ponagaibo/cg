package lab2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static java.lang.Math.sqrt;

public class Main extends Application {
    private double prevMouseX, prevMouseY, curMouseX, curMouseY;
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
        stage.setTitle("Понагайбо А.О. Лабораторная работа 2");
        double width = 800;
        double height = 600;
        Canvas canvas = new Canvas(width, height);
        final Point center = new Point(width / 2, height / 2, 0);
        Group root = new Group();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.FLORALWHITE);
        gc.setStroke(Color.DARKBLUE);
        gc.setLineWidth(3);

        final Point[] points = new Point[8];
        points[0] = new Point(-50, -50 * sqrt(3), 0);
        points[1] = new Point(50, -50 * sqrt(3), 0);
        points[2] = new Point(100, 0, 0);
        points[3] = new Point(50, 50 * sqrt(3), 0);
        points[4] = new Point(-50, 50 * sqrt(3), 0);
        points[5] = new Point(-100, 0, 0);
        points[6] = new Point(0, 0, 200);
        points[7] = new Point(0, 0, 50); // inner point of figure

        gc.fillRect(0,0, width, height);

        final Point toObs = new Point(0, 0, 10);

        Point start1 = points[1].Substract(points[0]);
        Point start2 = points[1].Substract(points[2]);
        Point startNorm = start1.CrossProduct(start2);
        if (startNorm.ScalarProduct(points[7]) > 0) {
            startNorm = start2.CrossProduct(start1);
        }
        if (startNorm.ScalarProduct(toObs) < 0) {
            for (int i = 0; i < 6; i++) {
                gc.strokeLine(points[i % 6].GetX() + center.GetX(), points[i % 6].GetY() + center.GetY(), points[(i + 1) % 6].GetX() + center.GetX(), points[(i + 1) % 6].GetY() + center.GetY());
            }
        }

        for (int i = 0; i < 6; i++) {
            start1 = points[6].Substract(points[i % 6]);
            start2 = points[6].Substract(points[(i + 1) % 6]);
            startNorm = start1.CrossProduct(start2);
            if (startNorm.ScalarProduct(points[7]) > 0) {
                startNorm = start2.CrossProduct(start1);
            }
            if (startNorm.ScalarProduct(toObs) < 0) {
                gc.strokeLine(points[i % 6].GetX() + center.GetX(), points[i % 6].GetY() + center.GetY(), points[(i + 1) % 6].GetX() + center.GetX(), points[(i + 1) % 6].GetY() + center.GetY());
                gc.strokeLine(points[6].GetX() + center.GetX(), points[6].GetY() + center.GetY(), points[i % 6].GetX() + center.GetX(), points[i % 6].GetY() + center.GetY());
                gc.strokeLine(points[6].GetX() + center.GetX(), points[6].GetY() + center.GetY(), points[(i + 1) % 6].GetX() + center.GetX(), points[(i + 1) % 6].GetY() + center.GetY());
            }
        }

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            curMouseX = event.getSceneX();
            curMouseY = event.getSceneY();
        });

        final Matrix rotateX = new Matrix();
        final Matrix rotateY = new Matrix();
        Point[] newPoints = new Point[8];
        for (int i = 0; i < 8; i++) {
            newPoints[i] = new Point(points[i].GetX(), points[i].GetY(), points[i].GetZ());
        }

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            prevMouseX = curMouseX;
            prevMouseY = curMouseY;
            curMouseX = event.getSceneX();
            curMouseY = event.getSceneY();
            double _dx = curMouseX - prevMouseX;
            double _dy = curMouseY - prevMouseY;
            rotateX.RotationX(_dy);
            rotateY.RotationY(-_dx);
            gc.fillRect(0,0, width, height);

            for (int i = 0; i < 8; i++) {
                newPoints[i] = newPoints[i].MultiplyByMatrix(rotateY).MultiplyByMatrix(rotateX);
            }
            Point toInner = newPoints[1].Substract(newPoints[7]);
            Point tr1 = newPoints[1].Substract(newPoints[0]);
            Point tr2 = newPoints[1].Substract(newPoints[2]);
            Point norm = tr1.CrossProduct(tr2);
            if (norm.ScalarProduct(toInner) > 0) {
                norm = tr2.CrossProduct(tr1);
            }
            if (norm.ScalarProduct(toObs) < 0) {
                for (int i = 0; i < 6; i++) {
                    gc.strokeLine(newPoints[i % 6].GetX() + center.GetX(), newPoints[i % 6].GetY() + center.GetY(), newPoints[(i + 1) % 6].GetX() + center.GetX(), newPoints[(i + 1) % 6].GetY() + center.GetY());
                }
            }

            for (int i = 0; i < 6; i++) {
                toInner = newPoints[6].Substract(newPoints[7]);
                tr1 = newPoints[6].Substract(newPoints[i % 6]);
                tr2 = newPoints[6].Substract(newPoints[(i + 1) % 6]);
                norm = tr1.CrossProduct(tr2);
                if (norm.ScalarProduct(toInner) > 0) {
                    norm = tr2.CrossProduct(tr1);
                }
                if (norm.ScalarProduct(toObs) < 0) {
                    gc.strokeLine(newPoints[i % 6].GetX() + center.GetX(), newPoints[i % 6].GetY() + center.GetY(), newPoints[(i + 1) % 6].GetX() + center.GetX(), newPoints[(i + 1) % 6].GetY() + center.GetY());
                    gc.strokeLine(newPoints[6].GetX() + center.GetX(), newPoints[6].GetY() + center.GetY(), newPoints[i % 6].GetX() + center.GetX(), newPoints[i % 6].GetY() + center.GetY());
                    gc.strokeLine(newPoints[6].GetX() + center.GetX(), newPoints[6].GetY() + center.GetY(), newPoints[(i + 1) % 6].GetX() + center.GetX(), newPoints[(i + 1) % 6].GetY() + center.GetY());
                }
            }
        });

        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    }
}