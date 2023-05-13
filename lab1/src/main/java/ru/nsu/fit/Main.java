package ru.nsu.fit;

import ru.nsu.fit.commands.Command;
import ru.nsu.fit.exceptions.ArgumentException;
import ru.nsu.fit.exceptions.CommandCreationException;
import ru.nsu.fit.exceptions.StackSizeException;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author Nikita Melnikov
 */
public class Main {
    private static final char COMMENT_SYMBOL = '#';
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

        CommandFactory commandFactory = new CommandFactory();
        try (Scanner scanner = new Scanner(commandParser.getIn())) {
            while (scanner.hasNextLine()) {
                String[] arguments = scanner.nextLine().split(" ");
                if (arguments[0].charAt(0) == COMMENT_SYMBOL)
                    continue;

                try {
                    LOGGER.log(Level.INFO, "Run " + Arrays.toString(arguments) + " command.");
                    Command command = commandFactory.create(arguments[0]);
                    command.exec(arguments, executionContext);
                    LOGGER.log(Level.FINEST, "Command completed successfully.");
                } catch (CommandCreationException | ArgumentException | RuntimeException except) {
                    LOGGER.log(Level.WARNING, except.getMessage());
                }
            }
        } catch (StackSizeException except) {
            LOGGER.log(Level.SEVERE, except.getMessage());
            System.exit(0);
        }

        LOGGER.log(Level.FINEST, "Application exit successfully.");
    }
}
