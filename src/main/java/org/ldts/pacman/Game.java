package org.ldts.pacman;

import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.gui.GUIForLanterna;

import java.io.IOException;

public class App {

    private GUI gui;

    private void run() {
       try {
           gui = new GUIForLanterna(50, 100);
       } catch(IOException e) {
           e.printStackTrace();
       }
    }

    public static void main(String[] args) {
        new App().run();
    }
}