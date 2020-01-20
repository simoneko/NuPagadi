package nuPagadi;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;

public class GameControl {

    private static int numberOfLives;
    private static int timePlayed;
    private static int eggsCought;
    private static int totalScore;

    public GameControl(){
        numberOfLives = 4;
        timePlayed = 0;
    }

    public static void spawnEggs(BorderPane container){

        Thread t = new Thread(() -> {
            int sleepTime = 4500;

            while (GameControl.getNumberOfLives() > 0){
                Egg.eggRun(container);
                try {
                    Thread.sleep(sleepTime);
                    if(sleepTime >= 2500)
                        sleepTime -= 250;
                    else if(sleepTime > 1000)
                        sleepTime -= 150;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            GameControl.setTotalScore();

            Platform.runLater(() -> {
                Stage gameOverStage = new Stage();
                gameOverStage.initModality(Modality.APPLICATION_MODAL);
//            gameOverStage.initOwner((Window) Window.getWindows());
                VBox popUpBox = new VBox();
                popUpBox.getChildren().add(new Text("You lost"));
                Scene scene = new Scene(popUpBox, 300, 200);
                gameOverStage.setScene(scene);
                gameOverStage.show();
            });




        });

        t.start();

    }

    public static int getNumberOfLives(){
        return numberOfLives;
    }

    public static void setNumberOfLives(int numberOfLives) {
        GameControl.numberOfLives = numberOfLives;
    }


    public static String timePlayedToString(){
        int seconds = timePlayed % 60;
        int minutes = timePlayed / 60;
        if(seconds < 10){
            return "Time: " + minutes + ":0" + seconds;
        } else {
            return "Time: " + minutes + ":" + seconds;
        }
    }

    public static void addSecond(){
        timePlayed++;
    }

    public static int getEggsCought() {
        return eggsCought;
    }

    public static void setEggsCought(int eggsCought) {
        GameControl.eggsCought = eggsCought;
    }

    public static int getTimePlayed() {
        return timePlayed;
    }

    public static int getTotalScore() {
        return totalScore;
    }

    public static void setTotalScore() {
        GameControl.totalScore = GameControl.getEggsCought() + GameControl.getTimePlayed();
        System.out.println(GameControl.totalScore);
    }



}
