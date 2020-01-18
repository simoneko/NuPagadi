package nuPagadi;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    @FXML
    private Label wolfLabel;
    @FXML
    private AnchorPane anch;
    @FXML
    private Label lifes;
    @FXML
    private Label timeLabel;


    Wolf wolf = new Wolf();


    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            lifes.setText("Lifes: " + Wolf.getNumberOfLives());
            timeLabel.setText(Wolf.timePlayedToString());
            Wolf.addSecond();
        }
    }));


    public void handleButtonClick(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String id = btn.getId();
        wolf.changePosition(id);
        wolfLabel.setStyle(wolf.getWolfStyle());
    }


    public void initialize(){
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

}
