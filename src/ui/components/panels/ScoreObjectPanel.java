package ui.components.panels;

import objects.assets.lang.ResourcesLoader;

public class ScoreObjectPanel {

    private final String name;
    private final int time;
    private final int difficulty;

    public ScoreObjectPanel(String name, int time, int difficulty) {
        this.name = name;
        this.time = time;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        switch (difficulty) {
            case 0:
                return ResourcesLoader.RESOURCE_BUNDLE.getString("easyLabel");
            case 1:
                return ResourcesLoader.RESOURCE_BUNDLE.getString("normalLabel");
            case 2:
                return ResourcesLoader.RESOURCE_BUNDLE.getString("hardLabel");
            default:
                return ResourcesLoader.RESOURCE_BUNDLE.getString("customLabel");
        }
    }

    public String getTime() {
        return timeIntToString(time);
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
