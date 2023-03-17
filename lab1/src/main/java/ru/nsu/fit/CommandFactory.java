package ru.nsu.fit;

import ru.nsu.fit.commands.Command;
import ru.nsu.fit.exceptions.CommandCreationException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class CommandFactory {
    public Command create(String commandName) throws CommandCreationException {
        Properties configuration = new Properties();
        Command command;

        try (InputStream stream = CommandFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            configuration.load(stream);
            Class<?> c = Class.forName(configuration.getProperty(commandName));
            command = (Command) c.getConstructor().newInstance();
        } catch (NullPointerException | ClassNotFoundException | InstantiationException | IllegalAccessException |
                 IOException | NoSuchMethodException | InvocationTargetException except) {
            throw new CommandCreationException(commandName);
        }

        return command;
    }
}
