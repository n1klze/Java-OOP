package ru.nsu.ccfit.melnikov.minesweeper.model;

import ru.nsu.ccfit.melnikov.minesweeper.observer.Observable;
import ru.nsu.ccfit.melnikov.minesweeper.observer.context.GameOverContext;
import ru.nsu.ccfit.melnikov.minesweeper.observer.context.MarkedCellContext;
import ru.nsu.ccfit.melnikov.minesweeper.observer.context.OpenedCellContext;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Field extends Observable {
    private static final byte MINE = -1;
    private static final byte EMPTY = 0;
    private final int height;
    private final int width;
    private final int numOfMines;
    private final Cell[][] minefield;
    private int numOfMarkedCells = 0;
    private int numOfOpenedCells = 0;
    private boolean isGameOver = false;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getNumOfMines() {
        return numOfMines;
    }

    public int getNumOfMarkedCells() {
        return numOfMarkedCells;
    }

    public Field(int height, int width, int numOfMines) {
        if (height <= 0 || width <= 0)
            throw new IllegalArgumentException("Incorrect field parameters.");

        if (numOfMines <= 0 || numOfMines >= height * width)
            throw new IllegalArgumentException("Bad number of mines.");

        this.height = height;
        this.width = width;
        this.numOfMines = numOfMines;
        this.minefield = new Cell[height][width];
        initCells();
        placeMines();
    }

    private void initCells() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                minefield[i][j] = new Cell();
            }
        }
    }

    private void placeMines() {
        Set<Integer> minePositions = generateMinePositions();

        for (Integer pos : minePositions) {
            int i = pos / width;
            int j = pos % width;
            mineCell(i, j);
        }
    }

    private Set<Integer> generateMinePositions() {
        Set<Integer> minePositions = new HashSet<>();
        var randGenerator = new Random();
        int bound = width * height;

        int currentNumOfMines = 0;
        while (currentNumOfMines != numOfMines) {
            int value = randGenerator.nextInt(bound);
            if (minePositions.contains(value))
                continue;
            minePositions.add(value);
            ++currentNumOfMines;
        }
        return minePositions;
    }

    void mineCell(int i, int j) {
        minefield[i][j].setValue(MINE);
        for (int k = -1; k <= 1; ++k) {
            for (int l = -1; l <= 1; ++l) {
                boolean isUpdatablePos = (k != 0 || l != 0) &&
                        (i + k >= 0 && i + k < height) &&
                        (j + l >= 0 && j + l < width) &&
                        (minefield[i + k][j + l].getValue() != MINE);
                if (isUpdatablePos)
                    minefield[i + k][j + l].incrementValue();
            }
        }
    }

    public void markCell(int i, int j) {
        if (isGameOver) return;
        checkCoordinates(i, j);
        if (minefield[i][j].getState() == CellState.CLOSED) {
            minefield[i][j].setState(CellState.MARKED);
            ++numOfMarkedCells;
        } else if (minefield[i][j].getState() == CellState.MARKED) {
            minefield[i][j].setState(CellState.CLOSED);
            --numOfMarkedCells;
        }
        notifyObservers(
                new MarkedCellContext(i, j, minefield[i][j].getState() == CellState.MARKED));
    }

    public void openCell(int i, int j) {
        if (isGameOver) return;
        checkCoordinates(i, j);
        if (minefield[i][j].getState() == CellState.CLOSED) {
            minefield[i][j].setState(CellState.OPENED);
            notifyObservers(
                    new OpenedCellContext(i,
                            j,
                            minefield[i][j].getValue() == MINE,
                            minefield[i][j].getValue()));
            ++numOfOpenedCells;
            checkGameOver(i, j);
            if (minefield[i][j].getValue() == EMPTY)
                openNeighbours(i, j);
        }
    }

    private void checkGameOver(int i, int j) {
        boolean areAllCellsOpened = (numOfOpenedCells == width * height - numOfMines);

        if (!isGameOver && (minefield[i][j].getValue() == MINE || areAllCellsOpened)) {
            isGameOver = true;
            revealAllMines();
            notifyObservers(new GameOverContext(areAllCellsOpened));
        }
    }

    private void revealAllMines() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (minefield[i][j].getValue() == MINE) {
                    minefield[i][j].setState(CellState.OPENED);
                    notifyObservers(
                            new OpenedCellContext(i,
                                    j,
                                    minefield[i][j].getValue() == MINE,
                                    minefield[i][j].getValue()));
                }
            }
        }
    }

    private void openNeighbours(int i, int j) {
        for (int k = -1; k <= 1; ++k) {
            for (int l = -1; l <= 1; ++l) {
                boolean isRevealablePos = (k != 0 || l != 0) &&
                        (i + k >= 0 && i + k < height) &&
                        (j + l >= 0 && j + l < width) &&
                        (minefield[i + k][j + l].getValue() != MINE);
                if (isRevealablePos)
                    openCell(i + k, j + l);
            }
        }
    }

    private void checkCoordinates(int i, int j) {
        if (i < 0 || i >= height || j < 0 || j >= width)
            throw new IllegalArgumentException("Wrong coordinates.");
    }
}
