package ru.nsu.fit.commands;

import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.ArgumentFormatException;
import ru.nsu.fit.exceptions.ArgumentSizeException;

import java.util.Map;
import java.util.regex.Pattern;

public class Define implements Command {
    /**
     * Define string key with numeric variable
     *
     * @param args             command arguments
     * @param executionContext contains stack of values and named parameters dictionary.
     * @throws ArgumentSizeException   if not enough elements in the stack.
     * @throws ArgumentFormatException if first argument is numeric or second argument is non-numeric.
     */
    @Override
    public void exec(String[] args, Context executionContext) throws ArgumentSizeException, ArgumentFormatException {
        Map<String, Double> namedParameters = executionContext.getNamedParametersDictionary();

        if (args.length != 3) throw new ArgumentSizeException();

        try {
            if (Pattern.compile("-?\\d+(\\.\\d+)?").matcher(args[1]).matches())
                throw new ArgumentFormatException("First argument cannot be a numeric: " + args[1]);
            if (namedParameters.containsKey(args[2])) {
                namedParameters.put(args[1], namedParameters.get(args[2]));
            } else {
                namedParameters.put(args[1], Double.valueOf(args[2]));
            }
        } catch (NumberFormatException except) {
            throw new ArgumentFormatException("Bad format of second argument: " + args[2]);
        }
    }
}
