package ru.nsu.ccfit.melnikov.minesweeper.observer;

import ru.nsu.ccfit.melnikov.minesweeper.observer.context.Context;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable {
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    public void notifyObserver(Context context) {
        for (Observer observer: observers)
            observer.update(context);
    }

    private final List<Observer> observers = new LinkedList<>();
}
