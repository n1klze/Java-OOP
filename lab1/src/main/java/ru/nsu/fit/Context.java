package ru.nsu.fit;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains stack of values and named parameters list.
 *
 * @author Nikita Melnikov
 */
public class Context {
    private Deque<Double> stack = new ArrayDeque<>();
    private Map<String[], Double> namedParametersList = new HashMap<>();

    public Deque<Double> getStack() {
        return stack;
    }

    public Map<String[], Double> getNamedParametersList() {
        return namedParametersList;
    }
}
