package nuPagadi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label wolf;

    public void handleButtonClick(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String id = btn.getId();

        switch (id) {
            case "rightDownButton":
                wolf.setStyle("-fx-graphic: url(\"./sprites/wolf-right-down.png\")");
                break;
            case "rightUpButton":
                wolf.setStyle("-fx-graphic: url(\"./sprites/wolf-right-up.png\")");
                break;
            case "leftUpButton":
                wolf.setStyle("-fx-graphic: url(\"./sprites/wolf-left-up.png\")");
                break;
            case "leftDownButton":
                wolf.setStyle("-fx-graphic: url(\"./sprites/wolf-left-down.png\")");
                break;
        }
    }

}
