package ru.nsu.ccfit.melnikov.minesweeper.view.gui.components;

import ru.nsu.ccfit.melnikov.minesweeper.controller.Controller;
import ru.nsu.ccfit.melnikov.minesweeper.controller.DifficultyLevel;
import ru.nsu.ccfit.melnikov.minesweeper.model.GameTimer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HighScoresDialog extends JDialog {
    private static final String TITLE = "High Scores";
    private static final DifficultyLevel[] DISPLAYED_LEVELS = {DifficultyLevel.EASY,
            DifficultyLevel.MEDIUM, DifficultyLevel.EXPERT};
    private static final String FONT_PATH = "/font.ttf";
    private static final Font FONT;
    private static final float FONT_SIZE = 20;
    private final Dimension WINDOW_SIZE = new Dimension(256, 200);
    private final Controller controller;

    static {
        try {
            Font nonSizedFont = Font.createFont(Font.TRUETYPE_FONT,
                    MenuButton.class.getResourceAsStream(FONT_PATH));
            FONT = nonSizedFont.deriveFont(FONT_SIZE);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HighScoresDialog(JFrame parent, Controller controller) {
        super(parent, true);
        this.controller = controller;

        setTitle(TITLE);
        setSize(WINDOW_SIZE);
        setLocationRelativeTo(null);
        add(new JScrollPane(createTextArea()));
        setVisible(true);
    }

    private JPanel createTextArea() {
        JPanel textArea = new JPanel();
        textArea.setLayout(new BoxLayout(textArea, BoxLayout.Y_AXIS));

        for (DifficultyLevel level : DISPLAYED_LEVELS) {
            int scoreTime = controller.getHighScoreByLevel(level);
            String displayedTime = (scoreTime == Integer.MAX_VALUE) ? "--" : GameTimer.toString(scoreTime);
            var scoreLine = new JLabel(level + " : " + displayedTime + "\n");
            scoreLine.setFont(FONT);
            textArea.add(scoreLine);
        }

        return textArea;
    }
}
