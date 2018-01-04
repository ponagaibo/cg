package lab3.DialogMenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lab3.Lab;

public class DiagController {

    @FXML
    private TextField r;

    @FXML
    private TextField g;

    @FXML
    private TextField b;

    @FXML
    private Button butt;

    public void initialize() {
        butt.setOnAction(event -> {
            Lab.light.setR(Integer.parseInt(r.getText()));
            Lab.light.setG(Integer.parseInt(g.getText()));
            Lab.light.setB(Integer.parseInt(b.getText()));
        });
    }
}
