package ru.nsu.fit.commands;

import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.ArgumentSizeException;

import java.util.Deque;
import java.util.Map;

public class Push implements Command {
    /**
     * Pushes an element onto the top of the stack
     *
     * @param args             command arguments
     * @param executionContext contains stack of values and named parameters list.
     * @throws ArgumentSizeException if is too many parameters.
     */
    @Override
    public void exec(String[] args, Context executionContext) throws ArgumentSizeException {
        Deque<Double> stack = executionContext.getStack();
        Map<String, Double> namedParameters = executionContext.getNamedParametersDictionary();

        if (args.length != 2) throw new ArgumentSizeException();

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
