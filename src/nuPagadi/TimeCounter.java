package nuPagadi;

public class TimeCounter {

    private String timeString;

    public void startCounting(){

        Thread time = new Thread(() -> {
            int minutes = 0;
            int seconds = 0;

            while(Wolf.getNumberOfLives() > 0) {
                seconds++;
                if(seconds == 60){
                    minutes++;
                    seconds = 0;
                }

                if(seconds < 10){
                    setTimeString("Time: " + minutes + ":0" + seconds);
                } else {
                    setTimeString("Time: " + minutes + ":" + seconds);
                }

                Wolf.setTimePlayed(minutes, seconds);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(getTimeString());
            }


        });

        time.start();

    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public String getTimeString() {
        return timeString;
    }
}
