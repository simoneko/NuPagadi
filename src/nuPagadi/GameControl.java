package nuPagadi;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.*;
import java.util.ArrayList;

public class GameControl {

    private static int numberOfLives;
    private static int timePlayed;
    private static int eggsCought;
    private static int totalScore;

    public GameControl(){
        numberOfLives = 4;
        timePlayed = 0;
    }

    public static void spawnEggs(BorderPane container, StackPane root){

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
                    else if(sleepTime > 800)
                        sleepTime -= 5;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            GameControl.setTotalScore();

            gameOverPopUp(root);

        });

        t.start();

    }

    private static void gameOverPopUp(StackPane root){
        Platform.runLater(() -> {
            Label gameOver = new Label("Game Over");
            Label score = new Label("You have scored " + getTotalScore() + " points");
            Label name = new Label("\nPlease enter your name:");

            TextField enterUserName = new TextField();

            VBox popUpBox = new VBox(gameOver, score, name, enterUserName);
            popUpBox.setId("popUpBox");
            root.getChildren().add(popUpBox);

            enterUserName.setOnKeyPressed(keyEvent -> {
                if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                    String userNameInput = enterUserName.getText().replace(" ", "" );
                    if(userNameInput.length() >= 3){
                        try {
                            saveHighScoreFile(userNameInput);
                            popUpBox.setVisible(false);
                            displayHighScoreList();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        });
    }

    private static void saveHighScoreFile(String userName) throws IOException {

        ArrayList<HighScore> lista = loadHighScoreFile();
        lista.add(new HighScore(userName, GameControl.getTotalScore()));
        lista.sort(new HighScorePointsComparator());

        PrintWriter newFile = new PrintWriter("src/highScores.txt");

        for(HighScore hs : lista) {
            newFile.println(hs.getName() + " - " + hs.getPoints());
        }

        newFile.close();

    }

    private static ArrayList<HighScore> loadHighScoreFile() throws IOException {

        ArrayList<HighScore> lista = new ArrayList<>();
        File file = new File("src/highScores.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while((st = br.readLine()) != null){
            StringBuilder name = new StringBuilder();
            StringBuilder points = new StringBuilder();
            int start;
            int end;
            for(int i = 0; i < st.length(); i++){
                if(String.valueOf(st.charAt(i)).equals(" ") ){
                    for(int j = 0; j < i; j++){
                        name.append(st.charAt(j));
                    }
                    start = i + 3;
                    end = st.length();
                    for(int j = start; j < end; j++){
                        points.append(st.charAt(j));
                    }
                    lista.add(new HighScore(name.toString(), Integer.parseInt(points.toString())));
                    break;
                }
            }
        }

        return lista;

    }

    public static void displayHighScoreList() throws IOException {
        Stage highScoresStage = new Stage();
        highScoresStage.setTitle("NuPogodi HighScores");
        ArrayList<HighScore> lista = loadHighScoreFile();

        ListView listView = new ListView();

        for(HighScore hs : lista) {
            listView.getItems().add(hs);
        }

        HBox hBox = new HBox(listView);

        Scene scene = new Scene(hBox, 300, 300);
        highScoresStage.setScene(scene);
        highScoresStage.initModality(Modality.APPLICATION_MODAL);
        highScoresStage.setResizable(false);
        highScoresStage.show();
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

    private static int getTimePlayed() {
        return timePlayed;
    }

    private static int getTotalScore() {
        return totalScore;
    }

    private static void setTotalScore() {
        GameControl.totalScore = GameControl.getEggsCought() + GameControl.getTimePlayed();
        System.out.println(GameControl.totalScore);
    }

}
