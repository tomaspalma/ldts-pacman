package org.ldts.pacman;

import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.gui.GUIForLanterna;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.states.ArenaState;
import org.ldts.pacman.states.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private GUI gui;
    private State currentState;

    public void setState(State state) {
        this.currentState = state;
    }

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new GUIForLanterna(40, 40);
        currentState = new ArenaState(new Arena(40, 40, "maps/easy.txt"));
    }

    private void run() throws IOException, InterruptedException {
        int FPSLimit = 60;
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
    }

    public static void main(String[] args) {
        try {
            new Game().run();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}