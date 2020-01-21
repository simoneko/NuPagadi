package nuPagadi;

import java.util.Comparator;

public class HighScore implements Comparable<HighScore> {

    private int points;
    private String name;

    public HighScore(String name, int points){
        this.name = name;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName() + " - " + getPoints();
    }

    @Override
    public int compareTo(HighScore position) {
        return this.points - position.points;
    }
}

class HighScorePointsComparator implements Comparator<HighScore> {
    public int compare(HighScore pos1, HighScore pos2){
        return pos2.getPoints() - pos1.getPoints();
    }
}
