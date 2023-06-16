package ru.nsu.ccfit.melnikov.minesweeper.model;

public class Cell {
    private byte value;
    private CellState state;
    private static final byte EMPTY = 0;

    public Cell() {
        value = EMPTY;
        state = CellState.CLOSED;
    }

    public byte getValue() {
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
