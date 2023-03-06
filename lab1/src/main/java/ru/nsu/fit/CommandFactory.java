package ru.nsu.fit;

import ru.nsu.fit.commands.Command;

import java.io.IOException;
import java.util.Properties;

public class CommandFactory {
    public Command create(String commandName) {
        Properties configuration = new Properties();
        Command command;

        try {
            configuration.load(CommandFactory.class.getClassLoader().getResourceAsStream("config.properties"));
            Class c = Class.forName(configuration.getProperty(commandName));
            command = (Command) c.newInstance();
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException except) {
            throw new RuntimeException(except);
        }

        return command;
    }
}
