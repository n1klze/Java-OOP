package ru.nsu.fit.exceptions;

public class ArgumentSizeException extends ArrayIndexOutOfBoundsException{
    public ArgumentSizeException() {
        super("Not enough arguments.");
    }
}
