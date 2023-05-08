package ru.nsu.ccfit.melnikov.minesweeper.view.gui;

import ru.nsu.ccfit.melnikov.minesweeper.controller.Controller;
import ru.nsu.ccfit.melnikov.minesweeper.observer.Observer;
import ru.nsu.ccfit.melnikov.minesweeper.observer.context.Context;
import ru.nsu.ccfit.melnikov.minesweeper.observer.context.MarkedCellContext;
import ru.nsu.ccfit.melnikov.minesweeper.observer.context.OpenedCellContext;
import ru.nsu.ccfit.melnikov.minesweeper.view.gui.components.CellButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.lang.Math.min;

public class GameSpace extends JFrame implements Observer {
    private final Controller controller;
    private final CellButton[][] cellsField;
    private static final String TITLE = "Minesweeper";
    private static final Dimension WINDOW_SIZE = new Dimension(800, 600);

    public GameSpace(Controller controller) {
        this.controller = controller;
        controller.registerObserver(this);

        cellsField = new CellButton[controller.getHeight()][controller.getWidth()];

        var statusPanel = new JPanel();
        initStatusPanel(statusPanel);
        var fieldPanel = new JPanel(
                new GridLayout(controller.getHeight(), controller.getWidth()));
        initCells(fieldPanel);

        setTitle(TITLE);
        //setSize(WINDOW_SIZE);
        calculateSize(statusPanel);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(statusPanel, BorderLayout.EAST);
        add(fieldPanel);
        setVisible(true);
    }

    private void initStatusPanel(JPanel statusPanel) {
        statusPanel.setLayout(new GridLayout(3, 1));
        statusPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        statusPanel.add(new JButton());
        statusPanel.add(new JButton());
        statusPanel.add(new JButton());
    }

    private void initCells(JPanel fieldPanel) {
        for (int i = 0; i < controller.getHeight(); ++i) {
            for (int j = 0; j < controller.getWidth(); ++j) {
                cellsField[i][j] = new CellButton(i, j);
                cellsField[i][j].addMouseListener(new CellButtonListener());
                fieldPanel.add(cellsField[i][j]);
            }
        }
        //fieldPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    private void calculateSize(JPanel statusPanel) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int cellSize = min(size.width / controller.getWidth(),
                size.height / controller.getHeight());

        int fieldPanelHeight = cellSize * controller.getHeight();
        int fieldPanelWidth = cellSize * controller.getWidth();

        size = new Dimension(fieldPanelWidth + statusPanel.getWidth(), fieldPanelHeight);
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
        if (context instanceof MarkedCellContext) {
            var ctx = (MarkedCellContext) context;
            var cell = cellsField[ctx.getI()][ctx.getJ()];
            cell.mark(ctx.isMarked());
        } else if (context instanceof OpenedCellContext) {
            var ctx = (OpenedCellContext) context;
            var cell = cellsField[ctx.getI()][ctx.getJ()];
            cell.open(ctx.getNumOfMinedNeighbours(), ctx.isMined());
        }
    }
}
