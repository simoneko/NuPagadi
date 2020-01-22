package nuPagadi;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Egg {

    private Position eggPosition;

    private Egg(Position eggPosition){
        this.eggPosition = eggPosition;
    }

    public static void eggRun(BorderPane container){

        Thread t = new Thread(() -> {
            Egg egg = new Egg(Egg.randomPosition());
            Ellipse eggEllipse = new Ellipse(15, 12, 10, 13);
            eggEllipse.setFill(Color.rgb(254, 217, 144));
            eggEllipse.setStroke(Color.BLACK);
            eggEllipse.setStrokeWidth(2);

            Platform.runLater(() -> {
                container.getChildren().add(eggEllipse);
            });

            egg.eggPath(egg.getEggPosition(), eggEllipse);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(Wolf.getWolfPosition() != egg.getEggPosition()){
                GameControl.setNumberOfLives(GameControl.getNumberOfLives() - 1);
                egg.eggFalls(egg.getEggPosition(), eggEllipse);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    container.getChildren().remove(eggEllipse);
                });

            } else {
                if(GameControl.getNumberOfLives() > 0)
                    GameControl.setEggsCought(GameControl.getEggsCought() + 1);

                Platform.runLater(() -> {
                    container.getChildren().remove(eggEllipse);
                });
            }

        });

        t.start();

    }

    private void eggPath(Position position, Ellipse eggEllipse){

        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;

        if(position == Position.LEFT_UP){
            startX = 260;
            startY = 55;
            endX = 415;
            endY = 145;
        } else if(position == Position.LEFT_DOWN){
            startX = 260;
            startY = 185;
            endX = 385;
            endY = 255;
        } else if (position == Position.RIGHT_UP){
            startX = 735;
            startY = 60;
            endX = 585;
            endY = 150;
        } else if (position == Position.RIGHT_DOWN){
            startX = 735;
            startY = 185;
            endX = 615;
            endY = 260;
        }

        PathTransition pathTransition = new PathTransition(Duration.seconds(5), eggEllipse);
        Path path = new Path();

        path.getElements().add(new MoveTo(startX, startY));
        path.getElements().add(new LineTo(endX, endY));

        pathTransition.setNode(eggEllipse);
        pathTransition.setPath(path);

        pathTransition.play();
    }

    private void eggFalls(Position position, Ellipse eggEllipse){
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;

        if(position == Position.LEFT_UP){
            startX = 415;
            startY = 145;
            endX = 435;
            endY = 390;
        } else if(position == Position.LEFT_DOWN){
            startX = 385;
            startY = 255;
            endX = 405;
            endY = 390;
        } else if (position == Position.RIGHT_UP){
            startX = 585;
            startY = 150;
            endX = 565;
            endY = 390;
        } else if (position == Position.RIGHT_DOWN){
            startX = 615;
            startY = 260;
            endX = 595;
            endY = 390;
        }

        PathTransition pathFallTransition = new PathTransition(Duration.seconds(0.5), eggEllipse);
        Path pathFall = new Path();
        pathFall.getElements().add(new MoveTo(startX, startY));
        pathFall.getElements().add(new LineTo(endX, endY));
        pathFallTransition.setNode(eggEllipse);
        pathFallTransition.setPath(pathFall);

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), eggEllipse);
        rotateTransition.setFromAngle(0);

        if(position == Position.LEFT_UP || position == Position.LEFT_DOWN) {
            rotateTransition.setToAngle(90);
        } else {
            rotateTransition.setToAngle(-90);
        }

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), eggEllipse);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.1);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(pathFallTransition, rotateTransition, fadeTransition);

        parallelTransition.play();

    }

    private static Position randomPosition(){
        int random = (int)Math.floor(Math.random()*4);
        Position returnPosition;

        switch(random){
            case 0:
                returnPosition = Position.LEFT_UP;
                break;
            case 1:
                returnPosition = Position.LEFT_DOWN;
                break;
            case 2:
                returnPosition = Position.RIGHT_UP;
                break;
            default:
                returnPosition = Position.RIGHT_DOWN;
                break;
        }

        return returnPosition;

    }

    private Position getEggPosition() {
        return eggPosition;
    }
}
