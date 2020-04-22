package objects.data;

import objects.type.DifficultyType;

public class Difficulties {

    public static final Difficulty easy = new Difficulty(9, 9, 10, DifficultyType.EASY);
    public static final Difficulty normal = new Difficulty(16, 16, 40, DifficultyType.NORMAL);
    public static final Difficulty hard = new Difficulty(30, 16, 99, DifficultyType.HARD);

}
