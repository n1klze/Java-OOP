package ru.nsu.fit.commands;

import ru.nsu.fit.Context;

import java.util.Deque;

public class Pop implements Command {
    @Override
    public void make(String[] args, Context executionContext) {
        Deque<Double> stack = executionContext.getStack();
        //if (stack.size() == 0) throw new exception
        stack.pop();
    }
}
