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

    public void stop() {
        if (!isRunning) return;

        isRunning = false;
        timer.cancel();
        timer.purge();
    }
}
