package ru.nsu.fit.exceptions;

public class ArgumentSizeException extends ArgumentException{
    public ArgumentSizeException() {
        super("Bad number of arguments.");
    }
}
