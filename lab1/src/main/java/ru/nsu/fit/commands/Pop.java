package ru.nsu.fit.commands;

import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.ArgumentSizeException;
import ru.nsu.fit.exceptions.StackSizeException;

import java.util.Deque;

public class Pop implements Command {
    /**
     * Pops an element from the top of the stack.
     *
     * @param args             command arguments
     * @param executionContext contains stack of values and named parameters list.
     * @throws StackSizeException    if is not enough elements in the stack.
     * @throws ArgumentSizeException if is too many parameters.
     */
    @Override
    public void exec(String[] args, Context executionContext) throws StackSizeException, ArgumentSizeException {
        Deque<Double> stack = executionContext.getStack();

        if (args.length != 1) throw new ArgumentSizeException();

        if (stack.size() == 0)
            throw new StackSizeException();
        stack.pop();
    }
}
