package lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab3.Misc.Light;

public class Lab extends Application {

    public static Light light = new Light(55, 55, 55);

    @Override
    public void start(Stage stage) throws Exception {
        final Parent root2 = FXMLLoader.load(getClass().getResource("/lab3.fxml"));
        final Scene scene2 = new Scene(root2);
        stage.setTitle("Лабораторная работа №3(Цилиндр), Понагайбо Анастасия");
        stage.setScene(scene2);
        stage.show();
        stage.setMinWidth(scene2.getWindow().getWidth());
        stage.setMinHeight(scene2.getWindow().getHeight());
    }
}
