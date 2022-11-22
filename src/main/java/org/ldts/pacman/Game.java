package org.ldts.pacman;

import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.gui.GUIForLanterna;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    private GUI gui;

    private void run() {
       try {
           gui = new GUIForLanterna(50, 100);
       } catch(IOException | URISyntaxException | FontFormatException e) {
           e.printStackTrace();
       }
    }

    public static void main(String[] args) {
        new Game().run();
    }
}