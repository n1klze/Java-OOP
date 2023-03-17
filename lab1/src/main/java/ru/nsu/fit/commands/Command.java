package ru.nsu.fit.commands;

import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.StackSizeException;

public interface Command {
    /**
     * @param args             command arguments
     * @param executionContext contains stack of values and named parameters list.
     * @throws StackSizeException
     * @author Nikita Melnikov
     */
    void exec(String[] args, Context executionContext) throws StackSizeException;
}
