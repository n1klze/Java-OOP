package ru.nsu.fit.commands;

import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.ArgumentSizeException;

import java.util.Deque;
import java.util.Map;

public class Push implements Command{
    @Override
    public void exec(String[] args, Context executionContext) {
        Deque<Double> stack = executionContext.getStack();
        Map<String, Double> namedParameters = executionContext.getNamedParametersList();

        if (args.length < 2) throw new ArgumentSizeException();

        try {
            Double currentValue;
            if (namedParameters.containsKey(args[1])) {
                currentValue = namedParameters.get(args[1]);
            } else {
                currentValue = Double.valueOf(args[1]);
            }
            stack.push(currentValue);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
}
