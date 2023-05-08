package ru.nsu.ccfit.melnikov.minesweeper.observer.context;

public class OpenedCellContext implements Context{
    private final int i;
    private final int j;
    private final boolean isMined;
    private final byte numOfMinedNeighbours;

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isMined() {
        return isMined;
    }

    public byte getNumOfMinedNeighbours() {
        return numOfMinedNeighbours;
    }

    public OpenedCellContext(int i, int j, boolean isMined, byte numOfMinedNeighbours) {
        this.i = i;
        this.j = j;
        this.isMined = isMined;
        this.numOfMinedNeighbours = numOfMinedNeighbours;
    }
}
