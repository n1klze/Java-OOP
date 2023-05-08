package ru.nsu.ccfit.melnikov.minesweeper.controller;

import ru.nsu.ccfit.melnikov.minesweeper.model.Field;
import ru.nsu.ccfit.melnikov.minesweeper.observer.Observer;
import ru.nsu.ccfit.melnikov.minesweeper.view.gui.GameSpace;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    private Field gamefield;
    private static final Map<DifficultyLevel, int[]> difficultyLevelParameters = new HashMap<>();

    static {
        difficultyLevelParameters.put(DifficultyLevel.EASY, new int[] {9, 9, 10});
        difficultyLevelParameters.put(DifficultyLevel.MEDIUM, new int[] {16, 16, 40});
        difficultyLevelParameters.put(DifficultyLevel.EXPERT, new int[] {16, 30, 99});
    }

    public void init(DifficultyLevel level) {
        int[] levelParameters = difficultyLevelParameters.get(level);
        gamefield = new Field(levelParameters[0], levelParameters[1], levelParameters[2]);
        new GameSpace(this);
    }

    public void openCell(int i, int j) {
        gamefield.openCell(i, j);
    }

    public void markCell(int i, int j) {
        gamefield.markCell(i, j);
    }

    public int getHeight() {
        return gamefield.getHeight();
    }

    public int getWidth(){
        return gamefield.getWidth();
    }

    public void registerObserver(Observer obs) {
        gamefield.registerObserver(obs);
    }
}
