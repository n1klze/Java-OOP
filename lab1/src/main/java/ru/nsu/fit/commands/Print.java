package ru.nsu.fit.commands;

import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.ArgumentSizeException;
import ru.nsu.fit.exceptions.StackSizeException;

import java.util.Deque;

public class Print implements Command {
    @Override
    public void exec(String[] args, Context executionContext) throws StackSizeException, ArgumentSizeException {
        Deque<Double> stack = executionContext.getStack();

        if (args.length != 1) throw new ArgumentSizeException();
        if (stack.size() == 0) throw new StackSizeException();

        System.out.println(stack.peek());
    }
}
