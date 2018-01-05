package lab4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class Controller {

    @FXML
    private Slider r;

    @FXML
    private Slider g;

    @FXML
    private Slider b;

    @FXML
    private Slider amb;

    @FXML
    private Slider dif;

    @FXML
    private Slider spec;

    @FXML
    private Button bt;

    @FXML
    private void initialize() {
        bt.setOnAction(event -> {
            //System.out.println(1);
            Lab.red = (float) r.getValue();
            Lab.green = (float) g.getValue();
            Lab.blue = (float) b.getValue();
            Lab.ambK = (float) amb.getValue();
            Lab.difK = (float) dif.getValue();
            Lab.specK = (float) spec.getValue();
        });
    }
}
