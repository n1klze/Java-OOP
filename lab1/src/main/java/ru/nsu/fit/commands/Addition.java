package ru.nsu.fit.commands;

import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.StackSizeException;

import java.util.Deque;

public class Addition implements Command {
    /**
     * Adds two numbers from top of the stack.
     *
     * @param args             command arguments, first argument is command name.
     * @param executionContext contains stack of values and named parameters dictionary.
     * @throws StackSizeException if not enough elements in the stack.
     */
    @Override
    public void exec(String[] args, Context executionContext) throws StackSizeException {
        Deque<Double> stack = executionContext.getStack();

        if (stack.size() < 2) throw new StackSizeException();

        stack.push(stack.pop() + stack.pop());
    }
}
