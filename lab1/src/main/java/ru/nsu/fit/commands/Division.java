package ru.nsu.fit.commands;

import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.StackSizeException;

import java.util.Deque;

public class Division implements Command {
    /**
     * Divides two numbers from the top of the stack.
     *
     * @param args             command arguments
     * @param executionContext contains stack of values and named parameters list.
     * @throws StackSizeException if not enough elements in the stack.
     */
    @Override
    public void exec(String[] args, Context executionContext) throws StackSizeException {
        Deque<Double> stack = executionContext.getStack();

        if (stack.size() < 2) throw new StackSizeException();

        stack.push(stack.pop() / stack.pop());
    }
}
