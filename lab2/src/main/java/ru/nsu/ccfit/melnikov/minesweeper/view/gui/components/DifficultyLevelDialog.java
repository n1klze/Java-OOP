package ru.nsu.ccfit.melnikov.minesweeper.view.gui.components;

import ru.nsu.ccfit.melnikov.minesweeper.controller.DifficultyLevel;

import javax.swing.*;
import java.awt.*;

public class DifficultyLevelDialog {
    public static final String[] levels = {"Easy", "Medium", "Expert"};
    public static final String iconPath = "/mine.png";
    public static final int[] iconResolution = {96, 96};

    public static DifficultyLevel choose(Component frame) {
        ImageIcon icon = new ImageIcon(
                Toolkit.getDefaultToolkit().
                        getImage(DifficultyLevelDialog.class.getResource(iconPath)).
                        getScaledInstance(iconResolution[0], iconResolution[1], Image.SCALE_DEFAULT));
        String result = (String) JOptionPane.showInputDialog(frame,
                "Choose difficulty level:",
                "Difficulty selection",
                JOptionPane.QUESTION_MESSAGE,
                icon,
                levels,
                levels[0]);

        if (result == null) return DifficultyLevel.NONE;

        return switch (result) {
            case "Easy" -> DifficultyLevel.EASY;
            case "Medium" -> DifficultyLevel.MEDIUM;
            case "Expert" -> DifficultyLevel.EXPERT;
            default -> DifficultyLevel.EASY;
        };
    }
}
