package ru.nsu.ccfit.melnikov.minesweeper.view.gui.components;

import ru.nsu.ccfit.melnikov.minesweeper.controller.DifficultyLevel;

import javax.swing.*;
import java.awt.*;

public class DifficultyLevelDialog {
    private static final String[] LEVELS = {"Easy", "Medium", "Expert"};
    private static final String ICON_PATH = "/mine.png";
    private static final int[] ICON_RESOLUTION = {96, 96};

    public static DifficultyLevel choose(Component frame) {
        ImageIcon icon = new ImageIcon(
                Toolkit.getDefaultToolkit().
                        getImage(DifficultyLevelDialog.class.getResource(ICON_PATH)).
                        getScaledInstance(ICON_RESOLUTION[0], ICON_RESOLUTION[1], Image.SCALE_DEFAULT));
        String result = (String) JOptionPane.showInputDialog(frame,
                "Choose difficulty level:",
                "Difficulty selection",
                JOptionPane.QUESTION_MESSAGE,
                icon,
                LEVELS,
                LEVELS[0]);

        if (result == null) return DifficultyLevel.NONE;

        return switch (result) {
            case "Easy" -> DifficultyLevel.EASY;
            case "Medium" -> DifficultyLevel.MEDIUM;
            case "Expert" -> DifficultyLevel.EXPERT;
            default -> DifficultyLevel.EASY;
        };
    }
}
