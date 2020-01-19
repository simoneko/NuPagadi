package nuPagadi;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
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
    @FXML
    private BorderPane container;
    @FXML
    private Label eggsLabel;


    GameControl game = new GameControl();
    private Wolf wolf = new Wolf();


    private Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {

            if(GameControl.getNumberOfLives() > 0)
                GameControl.addSecond();
        }
    }));

    private Timeline uiRefresh = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

            if(GameControl.getNumberOfLives() >= 0) {
                lifes.setText("Lifes: " + GameControl.getNumberOfLives());
                eggsLabel.setText("Eggs: " + GameControl.getEggsCought());
                timeLabel.setText(GameControl.timePlayedToString());
            }
        }
    }));


    public void handleButtonClick(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String id = btn.getId();
        wolf.changePosition(id);
        wolfLabel.setStyle(wolf.getWolfStyle());
    }


    public void initialize() throws InterruptedException {

        uiRefresh.setCycleCount((Timeline.INDEFINITE));
        uiRefresh.play();


        GameControl.spawnEggs(container);



    }



}
