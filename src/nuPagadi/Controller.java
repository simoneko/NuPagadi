package nuPagadi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private Label wolfLabel;
    @FXML
    private AnchorPane anch;

    Wolf wolf = new Wolf();



    public void handleButtonClick(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String id = btn.getId();
        wolf.changePosition(id);
        wolfLabel.setStyle(wolf.getWolfStyle());
    }

}
