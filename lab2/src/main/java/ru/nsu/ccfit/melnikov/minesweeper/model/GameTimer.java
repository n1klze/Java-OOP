package ru.nsu.ccfit.melnikov.minesweeper.model;

import ru.nsu.ccfit.melnikov.minesweeper.observer.Observable;
import ru.nsu.ccfit.melnikov.minesweeper.observer.context.GameTimerContext;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Observable {
    private Timer timer;
    private int seconds;
    private boolean isRunning;

    private class GameTimerTask extends TimerTask {
        @Override
        public void run() {
            ++seconds;
            notifyObservers(new GameTimerContext(seconds));
        }
    }

    public void run() {
        if (isRunning) return;

        seconds = -1;
        isRunning = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new GameTimerTask(), 0, 1000);
    }

    public int stop() {
        if (!isRunning) return -1;

        isRunning = false;
        timer.cancel();
        timer.purge();

        return seconds;
    }

    public static String toString(int seconds) {
        return String.format("%d:%02d:%02d", seconds / 3600, seconds / 60 % 60, seconds % 60);
    }
}
