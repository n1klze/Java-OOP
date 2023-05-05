package ru.nsu.ccfit.melnikov.minesweeper.view.gui.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MenuButton extends JButton {
    public static final int FONT_SIZE = 20;

    public MenuButton(String text) {
        super();

        setText(text);
        //super.setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(Color.CYAN);
        setBorder(new LineBorder(Color.DARK_GRAY, 1));
        setFont(new Font(Font.DIALOG, Font.BOLD, FONT_SIZE));
        //setForeground(Color.WHITE);
    }
}
