package ru.nsu.ccfit.melnikov.minesweeper.view.gui;

import ru.nsu.ccfit.melnikov.minesweeper.controller.Controller;
import ru.nsu.ccfit.melnikov.minesweeper.controller.DifficultyLevel;
import ru.nsu.ccfit.melnikov.minesweeper.model.score.Score;
import ru.nsu.ccfit.melnikov.minesweeper.observer.Observer;
import ru.nsu.ccfit.melnikov.minesweeper.observer.context.*;
import ru.nsu.ccfit.melnikov.minesweeper.view.gui.components.CellButton;
import ru.nsu.ccfit.melnikov.minesweeper.view.gui.components.DifficultyLevelDialog;
import ru.nsu.ccfit.melnikov.minesweeper.view.gui.components.HighScoresDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static java.lang.Math.min;

public class GameSpace extends JFrame implements Observer {
    private final Controller controller;
    private final CellButton[][] cellsField;
    private final JLabel minesCounter = new JLabel();
    private final JLabel gameTimer = new JLabel();
    private static final String TITLE = "Minesweeper";
    private static final String ICON_PATH = "/mine_scaled.png";
    private static final String MINE_ICON = "/mine.png";
    private static final String TIMER_ICON_PATH = "/timer.png";
    private static final String FONT_PATH = "/font.ttf";
    private static final Dimension ICON_RESOLUTION = new Dimension(64, 64);

    public GameSpace(Controller controller) {
        this.controller = controller;
        controller.registerObserver(this);

        cellsField = new CellButton[controller.getHeight()][controller.getWidth()];

        var statusPanel = new JPanel();
        initStatusPanel(statusPanel);
        var fieldPanel = new JPanel(
                new GridLayout(controller.getHeight(), controller.getWidth()));
        initCells(fieldPanel);

        Image icon = Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource(ICON_PATH));

        var menuBar = new JMenuBar();
        menuBar.add(initNewGameMenu());
        menuBar.add(initHighScoresMenu());
        setJMenuBar(menuBar);

        setTitle(TITLE);
        setIconImage(icon);
        calculateSize(statusPanel, menuBar);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(statusPanel, BorderLayout.SOUTH);
        add(fieldPanel);
    }

    public void display() {
        setVisible(true);
        controller.runTimer();
    }

    private void restartGame() {
        DifficultyLevel level = DifficultyLevelDialog.choose(this);
        if (level == DifficultyLevel.NONE)
            return;
        controller.init(level);
        dispose();
    }

    private JMenu initNewGameMenu() {
        var newGameMenu = new JMenu("New game");

        newGameMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                restartGame();
            }
        });

        return newGameMenu;
    }

    private JMenu initHighScoresMenu() {
        var highScoresMenu = new JMenu("Scores");
        var parent = this;
        highScoresMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                var scores = new HighScoresDialog(parent, controller);
                scores.display();
            }
        });

        return highScoresMenu;
    }

    private JLabel initGameTimer() {
        ImageIcon icon = new ImageIcon(
                Toolkit.getDefaultToolkit().
                        getImage(getClass().getResource(TIMER_ICON_PATH)).
                        getScaledInstance(ICON_RESOLUTION.width, ICON_RESOLUTION.height, Image.SCALE_DEFAULT));
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(FONT_PATH));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        gameTimer.setIcon(icon);
        gameTimer.setFont(font.deriveFont(min((float) ICON_RESOLUTION.width, (float) ICON_RESOLUTION.height) * 3 / 4));
        gameTimer.setHorizontalAlignment(SwingConstants.CENTER);
        gameTimer.setVerticalAlignment(SwingConstants.CENTER);

        return gameTimer;
    }

    private JLabel initMinesCounter() {
        ImageIcon icon = new ImageIcon(
                Toolkit.getDefaultToolkit().
                        getImage(getClass().getResource(MINE_ICON)).
                        getScaledInstance(ICON_RESOLUTION.width, ICON_RESOLUTION.height, Image.SCALE_DEFAULT));
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(FONT_PATH));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        minesCounter.setIcon(icon);
        minesCounter.setText(controller.getNumOfMines().toString());
        minesCounter.setFont(font.deriveFont(min((float) ICON_RESOLUTION.width, (float) ICON_RESOLUTION.height) * 3 / 4));
        minesCounter.setHorizontalAlignment(SwingConstants.CENTER);
        minesCounter.setVerticalAlignment(SwingConstants.CENTER);

        return minesCounter;
    }

    private void initStatusPanel(JPanel statusPanel) {
        statusPanel.setLayout(new GridLayout(1, 2));

        statusPanel.add(initMinesCounter());
        statusPanel.add(initGameTimer());
    }

    private void initCells(JPanel fieldPanel) {
        for (int i = 0; i < controller.getHeight(); ++i) {
            for (int j = 0; j < controller.getWidth(); ++j) {
                cellsField[i][j] = new CellButton(i, j);
                cellsField[i][j].addMouseListener(new CellButtonListener());
                fieldPanel.add(cellsField[i][j]);
            }
        }
    }

    private void calculateSize(JPanel statusPanel, JMenuBar menuBar) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int cellSize = min(size.width * 3 / 4 / controller.getWidth(),
                size.height * 3 / 4 / controller.getHeight());

        int fieldPanelWidth = cellSize * controller.getWidth();
        int fieldPanelHeight = cellSize * controller.getHeight();

        size = new Dimension(fieldPanelWidth, fieldPanelHeight + statusPanel.getHeight() + menuBar.getHeight());
        setSize(size);
    }

    private class CellButtonListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                var cell = (CellButton) e.getComponent();
                controller.openCell(cell.getI(), cell.getJ());
            } else if (SwingUtilities.isRightMouseButton(e)) {
                var cell = (CellButton) e.getComponent();
                controller.markCell(cell.getI(), cell.getJ());
            }
        }
    }

    @Override
    public void update(Context context) {
        if (context instanceof MarkedCellContext ctx) {
            var cell = cellsField[ctx.getI()][ctx.getJ()];
            cell.mark(ctx.isMarked());
            minesCounter.setText(((Integer) (controller.getNumOfMines() - controller.getNumOfMarkedCells())).toString());
        } else if (context instanceof OpenedCellContext ctx) {
            var cell = cellsField[ctx.getI()][ctx.getJ()];
            cell.open(ctx.getNumOfMinedNeighbours(), ctx.isMined());
        } else if (context instanceof GameTimerContext ctx) {
            gameTimer.setText(ctx.toString());
        } else if (context instanceof GameOverContext ctx) {
            var time = controller.stopTimer();
            controller.setIsWin(ctx.isWin());
            if (controller.getIsWin()) {
                minesCounter.setText("0");
                var result = new Score(controller.getDifficulty(), time);
                controller.saveScore(result);
                JOptionPane.showMessageDialog(this, "<html><h2>You win!</h2><i>Good job!</i>");
            } else {
                JOptionPane.showMessageDialog(this, "<html><h2>Game over!</h2><i>Try again!</i>");
            }
        }
    }
}
