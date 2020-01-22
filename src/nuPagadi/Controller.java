package nuPagadi;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.util.Duration;
import java.io.IOException;

public class Controller {

    @FXML
    private Label wolfLabel;
    @FXML
    private Label lifes;
    @FXML
    private Label timeLabel;
    @FXML
    private BorderPane container;
    @FXML
    private Label eggsLabel;
    @FXML
    private StackPane root;


    GameControl game = new GameControl();
    private Wolf wolf = new Wolf();


    private Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {

        if(GameControl.getNumberOfLives() > 0)
            GameControl.addSecond();
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

    private void newGame(StackPane root){
        container.setVisible(true);
        uiRefresh.setCycleCount((Timeline.INDEFINITE));
        uiRefresh.play();
        GameControl.spawnEggs(container, root);
    }

    private void welcomeScreen(){
        Button newGameButton = new Button("New Game");
        Button highScoresButton = new Button("High Scores");
        Button quitGameButton = new Button("Quit");

        VBox vBox = new VBox(newGameButton, highScoresButton, quitGameButton);
        vBox.setTranslateX(400);
        vBox.setTranslateY(165);
        vBox.setSpacing(5);
        root.getChildren().add(vBox);

        newGameButton.setOnMouseClicked(mouseEvent -> {
            vBox.setVisible(false);
            newGame(root);
        });

        highScoresButton.setOnMouseClicked(mouseEvent -> {
            try {
                GameControl.displayHighScoreList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        quitGameButton.setOnMouseClicked(mouseEvent -> {
            Platform.exit();
            System.exit(0);
        });

    }

    public void initialize() {

        container.setVisible(false);
        welcomeScreen();

    }

}
