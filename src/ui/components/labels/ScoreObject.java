package ui.components.labels;

public class ScoreObject {

    private int id;
    private String name;
    private String time;
    private String difficulty;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name =name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }

    public String getTime() {
        return time;
    }

    public void setTime(int time){
        this.time = timeIntToString(time);
    }

    private String timeIntToString(int second) {
        int min = second / 60;
        int sec = second % 60;
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
