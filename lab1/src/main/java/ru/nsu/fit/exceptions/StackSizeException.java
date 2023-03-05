package ru.nsu.fit.exceptions;

public class StackSizeException extends Exception {
    public StackSizeException() {
        super("Not enough elements in the stack.");
    }
}
