package ru.nsu.ccfit.melnikov.minesweeper.view.gui.components;

import ru.nsu.ccfit.melnikov.minesweeper.controller.DifficultyLevel;

import javax.swing.*;
import java.awt.*;

public class DifficultyLevelDialog {
    private static final String[] LEVELS = {DifficultyLevel.EASY.toString(),
            DifficultyLevel.MEDIUM.toString(), DifficultyLevel.EXPERT.toString()};
    private static final String ICON_PATH = "/mine.png";
    private static final String TITLE = "Difficulty selection";
    private static final String MESSAGE = "Choose difficulty level:";
    private static final Dimension ICON_RESOLUTION = new Dimension(96, 96);

    public static DifficultyLevel choose(Component frame) {
        ImageIcon icon = new ImageIcon(
                Toolkit.getDefaultToolkit().
                        getImage(DifficultyLevelDialog.class.getResource(ICON_PATH)).
                        getScaledInstance(ICON_RESOLUTION.width, ICON_RESOLUTION.height, Image.SCALE_DEFAULT));
        String result = (String) JOptionPane.showInputDialog(frame,
                MESSAGE,
                TITLE,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                LEVELS,
                LEVELS[0]);

        if (result == null) return DifficultyLevel.NONE;

        return DifficultyLevel.valueOf(result.toUpperCase());
    }
}
