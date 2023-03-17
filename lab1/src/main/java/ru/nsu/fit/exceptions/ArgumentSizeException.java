package ru.nsu.fit.exceptions;

public class ArgumentSizeException extends ArrayIndexOutOfBoundsException{
    public ArgumentSizeException() {
        super("Bad number of arguments.");
    }
}
