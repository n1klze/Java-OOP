package ru.nsu.fit.commands;

import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.StackSizeException;

import java.util.Deque;

public class Pop implements Command {
    @Override
    public void exec(String[] args, Context executionContext) throws StackSizeException {
        Deque<Double> stack = executionContext.getStack();
        if (stack.size() == 0)
            throw new StackSizeException();
        stack.pop();
    }
}
