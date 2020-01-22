package nuPagadi;

public class Wolf {

    private static Position wolfPosition;
    private String wolfStyle;

    public Wolf(){
        wolfPosition = Position.LEFT_DOWN;
        this.wolfStyle = "-fx-graphic: url(\"./sprites/wolf-left-down.png\")";

    }

    public void changePosition(String position){

        switch (position) {
            case "rightDownButton":
                wolfStyle = "-fx-graphic: url(\"./sprites/wolf-right-down.png\")";
                wolfPosition = Position.RIGHT_DOWN;
                break;
            case "rightUpButton":
                wolfStyle = "-fx-graphic: url(\"./sprites/wolf-right-up.png\")";
                wolfPosition = Position.RIGHT_UP;
                break;
            case "leftUpButton":
                wolfStyle = "-fx-graphic: url(\"./sprites/wolf-left-up.png\")";
                wolfPosition = Position.LEFT_UP;
                break;
            case "leftDownButton":
                wolfStyle = "-fx-graphic: url(\"./sprites/wolf-left-down.png\")";
                wolfPosition = Position.LEFT_DOWN;
                break;
        }

    }

    public String getWolfStyle() {
        return wolfStyle;
    }

    public static Position getWolfPosition() {
        return wolfPosition;
    }


}
