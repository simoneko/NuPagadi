package nuPagadi;

import java.awt.*;

public class Game {

    public static void startNewGame(Label lifesLabel){
        lifesLabel.setText("Lifes: " + Wolf.getNumberOfLives());
    }

}
