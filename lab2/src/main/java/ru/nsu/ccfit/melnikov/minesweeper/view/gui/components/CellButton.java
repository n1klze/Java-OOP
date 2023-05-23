package ru.nsu.ccfit.melnikov.minesweeper.view.gui.components;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static java.lang.Math.min;

public class CellButton extends JButton {
    private static final Color[] COLORS_BY_NUM = {Color.CYAN,
            Color.GREEN,
            Color.ORANGE,
            Color.BLUE,
            Color.RED,
            Color.MAGENTA,
            Color.YELLOW,
            Color.BLACK};
    private static final Color CLOSED_CELL_COLOR = Color.BLUE;
    private static final Color OPENED_CELL_COLOR = Color.LIGHT_GRAY;
    private static final String MINE_ICON_PATH = "/mine.png";
    private static final String FLAG_ICON_PATH = "/flag.png";
    private static final String FONT_PATH = "/font.ttf";
    private static final Font FONT;
    private static final Image MINE_ICON;
    private static final Image FLAG_ICON;
    private final int i;
    private final  int j;

    public int getI() {return i;}

    public int getJ() {return j;}

    static {
        MINE_ICON = Toolkit.getDefaultToolkit().getImage(CellButton.class.getResource(MINE_ICON_PATH));
        FLAG_ICON = Toolkit.getDefaultToolkit().getImage(CellButton.class.getResource(FLAG_ICON_PATH));
        try {
            FONT = Font.createFont(Font.TRUETYPE_FONT, CellButton.class.getResourceAsStream(FONT_PATH));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CellButton(int i, int j) {
        super();

        this.i = i;
        this.j = j;
        setBackground(CLOSED_CELL_COLOR);
        setFocusPainted(true);
    }

    public void open(byte numOfMinedNeighbours, boolean isMined) {
        setBackground(OPENED_CELL_COLOR);
        if (isMined) {
            Dimension size = getSize();
            setIcon(new ImageIcon(
                    MINE_ICON.getScaledInstance(3 * size.width / 4,
                            3 * size.height / 4,
                            Image.SCALE_DEFAULT)));
        } else if (numOfMinedNeighbours > 0) {
            setIcon(null);
            setText(Integer.toString(numOfMinedNeighbours));
            setFont(FONT.deriveFont(min((float) getHeight() / 3, (float) getWidth() / 3)));
            setForeground(COLORS_BY_NUM[numOfMinedNeighbours - 1]);
        }
    }

    public void mark(boolean isMarked) {
        if (isMarked) {
            Dimension size = getSize();
            setIcon(new ImageIcon(
                    FLAG_ICON.getScaledInstance(3 * size.width / 4,
                            3 * size.height / 4,
                            Image.SCALE_DEFAULT)));
        } else {
            setIcon(null);
        }
    }
}
