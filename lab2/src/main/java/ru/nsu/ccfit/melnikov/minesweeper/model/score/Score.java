package ru.nsu.ccfit.melnikov.minesweeper.model.score;

import ru.nsu.ccfit.melnikov.minesweeper.controller.DifficultyLevel;

public class Score {
    private final DifficultyLevel level;
    private final int time;

    public Score(DifficultyLevel level, int time) {
        this.level = level;
        this.time = time;
    }

    public DifficultyLevel getLevel() {
        return level;
    }

    public int getTime() {
        return time;
    }
}
