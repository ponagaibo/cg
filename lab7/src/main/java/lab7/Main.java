package lab7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/scheme/lab7.fxml"));
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("ЛР 7, Понагайбо Анастасия: интерполяционный многочлен Лагранжа по 4 точкам");
        stage.setScene(scene);
        stage.show();
        stage.setMinWidth(900);
        stage.setMinHeight(600);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
