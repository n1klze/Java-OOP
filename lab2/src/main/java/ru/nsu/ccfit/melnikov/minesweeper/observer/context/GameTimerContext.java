package ru.nsu.ccfit.melnikov.minesweeper.observer.context;

public class GameTimerContext implements Context {
    private final int seconds;

    public GameTimerContext(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return String.format("%d:%02d:%02d", seconds / 3600, seconds / 60 % 60, seconds % 60);
    }
}
