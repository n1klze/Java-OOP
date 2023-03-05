package ru.nsu.fit.commands;

import ru.nsu.fit.Context;

public interface Command {
    /**
     * @param args             command arguments.
     * @param executionContext contains stack of values and named parameters list.
     * @author Nikita Melnikov
     */
    void make(String[] args, Context executionContext);
}
