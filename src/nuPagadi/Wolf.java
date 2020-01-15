package nuPagadi;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Wolf {

    private WolfPosition wolfPosition;
    private String wolfStyle;


    public Wolf(){
        this.wolfPosition = WolfPosition.LEFT_DOWN;
        this.wolfStyle = "-fx-graphic: url(\"./sprites/wolf-left-down.png\")";
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

    public WolfPosition getWolfPosition() {
        return wolfPosition;
    }

    public void setWolfPosition(WolfPosition wolfPosition) {
        this.wolfPosition = wolfPosition;
    }
}
