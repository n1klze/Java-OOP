package ru.nsu.ccfit.melnikov.minesweeper.view.gui.components;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private final Image backgroundImage;

    public BackgroundPanel(String pathToImage) {
        this.backgroundImage = Toolkit.getDefaultToolkit().
                getImage(BackgroundPanel.class.getResource(pathToImage));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
