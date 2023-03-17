package ru.nsu.fit.commands;

import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.StackSizeException;

import java.util.Deque;

public class Sqrt implements Command {
    /**
     * Replaces the element from the top of the stack to his square root value.
     *
     * @param args             command arguments
     * @param executionContext contains stack of values and named parameters list.
     * @throws StackSizeException if is too many parameters.
     */
    @Override
    public void exec(String[] args, Context executionContext) throws StackSizeException {
        Deque<Double> stack = executionContext.getStack();

        if (stack.size() == 0) throw new StackSizeException();

        stack.push(Math.sqrt(stack.pop()));
    }
}
