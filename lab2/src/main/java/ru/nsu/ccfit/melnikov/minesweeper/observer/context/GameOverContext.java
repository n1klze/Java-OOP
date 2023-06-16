package ru.nsu.ccfit.melnikov.minesweeper.observer.context;

public class GameOverContext implements Context {
    private boolean isWin;
    private int explodedI;
    private int explodedJ;

    public GameOverContext(boolean isWin, int i, int j) {
        this.isWin = isWin;
        this.explodedI = i;
        this.explodedJ = j;
    }

    public boolean isWin() {
        return isWin;
    }

    public int getExplodedI() {
        return explodedI;
    }

    public int getExplodedJ() {
        return explodedJ;
    }
}
