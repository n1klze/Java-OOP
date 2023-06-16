package ru.nsu.fit.exceptions;

public class CommandCreationException extends Exception{
    public CommandCreationException(String command) {
        super("Can not create a command " + command);
    }
}
