package ru.nsu.fit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CommandLineParser {
    private InputStream in = System.in;

    public void parse(String[] args) {
        if (args == null || args.length == 0) {
            return;
        }
        String fileName = args[0];
        try {
            in = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream getIn() {
        return in;
    }
}
