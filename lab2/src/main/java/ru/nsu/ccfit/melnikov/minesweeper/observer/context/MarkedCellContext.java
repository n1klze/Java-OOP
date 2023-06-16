package ru.nsu.ccfit.melnikov.minesweeper.observer.context;

public class MarkedCellContext implements Context {
    private final int i;
    private final int j;
    private final boolean isMarked;

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public MarkedCellContext(int i, int j, boolean isMarked) {
        this.i = i;
        this.j = j;
        this.isMarked = isMarked;
    }
}
