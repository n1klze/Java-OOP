package ru.nsu.ccfit.melnikov.minesweeper;

import ru.nsu.ccfit.melnikov.minesweeper.view.gui.MainMenu;

public class Main {
    public static void main(String[] args) {
        var mainMenu = new MainMenu();
        mainMenu.exec();
    }
}