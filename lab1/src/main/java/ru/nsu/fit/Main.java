package ru.nsu.fit;

import ru.nsu.fit.commands.Command;
import ru.nsu.fit.exceptions.CommandCreationException;
import ru.nsu.fit.exceptions.StackSizeException;

import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    static {
        try {
            InputStream stream = Main.class.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
            LOGGER.log(Level.CONFIG, "Logger was successfully configured.");
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "Launch calculator application.");

        LOGGER.log(Level.INFO, "Parsing command line.");
        CommandLineParser commandParser = new CommandLineParser();
        try {
            commandParser.parse(args);
        } catch (RuntimeException except) {
            LOGGER.log(Level.SEVERE, "File " + args[0] + " not found.");
            System.exit(0);
        }

        LOGGER.log(Level.INFO, "Create context.");
        Context executionContext = new Context();

        LOGGER.log(Level.INFO, "Start executing commands.");
        Scanner scanner = new Scanner(commandParser.getIn());
        CommandFactory commandFactory = new CommandFactory();
        while (scanner.hasNextLine()) {
            try {
                String[] arguments = scanner.nextLine().split(" ");

                LOGGER.log(Level.INFO, "Make " + arguments[0] + " command.");
                Command command = commandFactory.create(arguments[0]);
                LOGGER.log(Level.FINEST, "Command successfully created.");
                command.make(arguments, executionContext);
            } catch (StackSizeException except) {
                LOGGER.log(Level.SEVERE, except.getMessage());
                scanner.close();
                System.exit(0);
            } catch (CommandCreationException except) {
                LOGGER.log(Level.WARNING, except.getMessage());
            }
        }
        LOGGER.log(Level.INFO, "Finish executing commands.");
        scanner.close();
    }
}
