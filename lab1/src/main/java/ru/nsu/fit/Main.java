package ru.nsu.fit;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    static {
        try {
            InputStream stream = Main.class.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "Launch calculator application.");
    }
}
