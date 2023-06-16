package ru.nsu.ccfit.melnikov.minesweeper.controller;

public enum DifficultyLevel {
    NONE("None"),
    EASY("Easy"),
    MEDIUM("Medium"),
    EXPERT("Expert");

    private final String level;

    DifficultyLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return level;
    }
}
