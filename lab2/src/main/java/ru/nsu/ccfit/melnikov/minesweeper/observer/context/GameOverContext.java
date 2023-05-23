package ru.nsu.ccfit.melnikov.minesweeper.observer.context;

public class GameOverContext implements Context {
    private boolean isWin;

    public GameOverContext(boolean isWin) {
        this.isWin = isWin;
    }

    public boolean isWin() {
        return isWin;
    }
}
