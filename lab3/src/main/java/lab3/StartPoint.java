package lab3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        final Parent root1 = FXMLLoader.load(getClass().getResource("/dmenu.fxml"));
        final Scene scene1 = new Scene(root1);
        stage.setScene(scene1);
        stage.setTitle("Color parameters");
        stage.setMinWidth(scene1.getWindow().getWidth());
        stage.setMinHeight(scene1.getWindow().getHeight());
        stage.show();
        Platform.runLater(() -> {
            try {
                new Lab().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
