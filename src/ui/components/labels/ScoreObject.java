package ui.components.labels;

public class ScoreObject {

    private final String name;
    private final String time;
    private final String difficulty;

    public ScoreObject(String name, int time, String difficulty) {
        this.name = name;
        this.time = timeIntToString(time);
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getDifficulty() {
        return difficulty;
    }

    private String timeIntToString(int time) {
        int min = time / 60;
        int sec = time % 60;
        String minutesString = Integer.toString(min);
        String secondsString = Integer.toString(sec);
        if (min / 10 == 0) minutesString = "0" + minutesString;
        if (sec / 10 == 0) secondsString = "0" + secondsString;
        return minutesString + ":" + secondsString;
    }

    @Override
    public String toString() {
        return "ScoreObject{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
