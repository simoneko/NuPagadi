package nuPagadi;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Wolf {

    private WolfPosition wolfPosition;
    private String wolfStyle;
    private static int numberOfLives;
    private static int timePlayed;


    public Wolf(){
        this.wolfPosition = WolfPosition.LEFT_DOWN;
        this.wolfStyle = "-fx-graphic: url(\"./sprites/wolf-left-down.png\")";
        numberOfLives = 4;
        timePlayed = 0;
    }

    public void changePosition(String position){

        switch (position) {
            case "rightDownButton":
                wolfStyle = "-fx-graphic: url(\"./sprites/wolf-right-down.png\")";
                wolfPosition = WolfPosition.RIGHT_DOWN;
                break;
            case "rightUpButton":
                wolfStyle = "-fx-graphic: url(\"./sprites/wolf-right-up.png\")";
                wolfPosition = WolfPosition.RIGHT_UP;
                break;
            case "leftUpButton":
                wolfStyle = "-fx-graphic: url(\"./sprites/wolf-left-up.png\")";
                wolfPosition = WolfPosition.LEFT_UP;
                break;
            case "leftDownButton":
                wolfStyle = "-fx-graphic: url(\"./sprites/wolf-left-down.png\")";
                wolfPosition = WolfPosition.LEFT_DOWN;
                break;
        }

    }

    public String getWolfStyle() {
        return wolfStyle;
    }

    public static int getNumberOfLives(){
        return numberOfLives;
    }

    public static void setTimePlayed(int minutes, int seconds) {
        timePlayed = minutes*60 + seconds;
    }

    public static int getTimePlayed() {
        return timePlayed;
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

    public WolfPosition getWolfPosition() {
        return wolfPosition;
    }

    public void setWolfPosition(WolfPosition wolfPosition) {
        this.wolfPosition = wolfPosition;
    }
}
