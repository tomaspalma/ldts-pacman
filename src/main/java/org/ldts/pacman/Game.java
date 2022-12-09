package org.ldts.pacman;

import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.gui.GUIForLanterna;
import org.ldts.pacman.models.menus.MainMenu;
import org.ldts.pacman.states.State;
import org.ldts.pacman.states.menus.RegularMenuState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final GUI gui;
    private State currentState;
    private final int width;
    private final int height;

    public State getArenaState() {
        return currentState;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.width = 21;
        this.height = 21;
        this.gui = new GUIForLanterna(width, height);
        currentState = new RegularMenuState(new MainMenu());
    }

    private void run() throws IOException, InterruptedException {
        int FPSLimit = 10;
        int frameTime = 1000 / FPSLimit;

        while(this.currentState != null) {
            long startTime = System.currentTimeMillis();

            currentState.step(this, gui, startTime);

            long elaspedTime = System.currentTimeMillis() - startTime;
            long potentialTimeForProcessToSleep = frameTime - elaspedTime;

            if(potentialTimeForProcessToSleep > 0) {
                Thread.sleep(potentialTimeForProcessToSleep);
            }
        }
        gui.close();
    }

    public static void main(String[] args) {
        try {
            new Game().run();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}