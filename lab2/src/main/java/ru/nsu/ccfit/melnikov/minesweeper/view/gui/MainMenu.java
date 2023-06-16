package ru.nsu.ccfit.melnikov.minesweeper.view.gui;

import ru.nsu.ccfit.melnikov.minesweeper.controller.Controller;
import ru.nsu.ccfit.melnikov.minesweeper.controller.DifficultyLevel;
import ru.nsu.ccfit.melnikov.minesweeper.view.gui.components.BackgroundPanel;
import ru.nsu.ccfit.melnikov.minesweeper.view.gui.components.DifficultyLevelDialog;
import ru.nsu.ccfit.melnikov.minesweeper.view.gui.components.HighScoresDialog;
import ru.nsu.ccfit.melnikov.minesweeper.view.gui.components.MenuButton;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private final Controller controller;
    private static final Dimension WINDOW_SIZE = new Dimension(800, 600);
    private static final String TITLE = "Minesweeper";
    private static final String MAIN_MENU_BACKGROUND = "/main_menu_background.jpg";
    private static final String ICON_PATH = "/mine_scaled.png";

    public MainMenu() {
        controller = new Controller();
        Image icon = Toolkit.getDefaultToolkit().
                getImage(MainMenu.class.getResource(ICON_PATH));

        setTitle(TITLE);
        setIconImage(icon);
        setSize(WINDOW_SIZE);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(createContentPane());
    }

    public void display() {
        setVisible(true);
    }

    private BackgroundPanel createContentPane() {
        var contentPane = new BackgroundPanel(MAIN_MENU_BACKGROUND);
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(createButtonsArea());

        return contentPane;
    }

    private JPanel createButtonsArea() {
        var buttonsArea = new JPanel();
        buttonsArea.setPreferredSize(new Dimension(getWidth() / 4, getHeight() / 4));
        buttonsArea.setLayout(new GridLayout(3, 1));

        var newGameButton = new MenuButton("New game");
        newGameButton.addActionListener(e -> startGame());
        buttonsArea.add(newGameButton);

        var highScoresButton = new MenuButton("High scores");
        highScoresButton.addActionListener(e -> {
            var scores = new HighScoresDialog(this, controller);
            scores.display();
        });
        buttonsArea.add(highScoresButton);

        var exitButton = new MenuButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        buttonsArea.add(exitButton);

        return buttonsArea;
    }

    public void startGame() {
        DifficultyLevel level = DifficultyLevelDialog.choose(this);
        if (level == DifficultyLevel.NONE)
            return;
        controller.init(level);
        this.dispose();
    }
}
