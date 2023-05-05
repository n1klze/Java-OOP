package ru.nsu.ccfit.melnikov.minesweeper.view.gui.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class MenuButton extends JButton {
    private static final String FONT_PATH = "/font.ttf";
    private static final Font FONT;
    private static final float FONT_SIZE = 20;

    static {
        try {
            Font nonSizedFont = Font.createFont(Font.TRUETYPE_FONT,
                    MenuButton.class.getResourceAsStream(FONT_PATH));
            FONT = nonSizedFont.deriveFont(FONT_SIZE);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MenuButton(String text) {
        super();

        setText(text);
        setFont(FONT);
        //super.setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(Color.CYAN);
        setBorder(new LineBorder(Color.DARK_GRAY, 1));
        //setFont(new Font(Font.DIALOG, Font.BOLD, FONT_SIZE));
        //setForeground(Color.WHITE);
    }
}
