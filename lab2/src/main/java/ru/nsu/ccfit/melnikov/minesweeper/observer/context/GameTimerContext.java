package ru.nsu.ccfit.melnikov.minesweeper.observer.context;

import ru.nsu.ccfit.melnikov.minesweeper.model.GameTimer;

public class GameTimerContext implements Context {
    private final int seconds;

    public GameTimerContext(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return GameTimer.toString(seconds);
    }
}
