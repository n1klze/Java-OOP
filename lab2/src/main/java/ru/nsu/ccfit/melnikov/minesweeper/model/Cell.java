package ru.nsu.ccfit.melnikov.minesweeper.model;

public class Cell {
    private byte value;
    private CellState state;

    public int getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public void incrementValue() {
        ++value;
    }
}
