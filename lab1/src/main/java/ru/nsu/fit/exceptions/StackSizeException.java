package ru.nsu.fit.exceptions;

public class StackSizeException extends Exception {
    public StackSizeException() {
        super("Stack underflow.");
    }
}
