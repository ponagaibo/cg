package kp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("/scheme2/kp.fxml"));
        final Scene scene = new Scene(root);
        stage.setTitle("Курсовая работа, Понагайбо Анастасия, вариант 13");
        stage.setScene(scene);
        stage.show();
        stage.setMinWidth(800);
        stage.setMinHeight(600);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
