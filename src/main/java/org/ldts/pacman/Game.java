package org.ldts.pacman;

import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.gui.GUIForLanterna;
import org.ldts.pacman.models.menus.MainMenu;
import org.ldts.pacman.states.State;
import org.ldts.pacman.states.menus.RegularMenuState;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class Game {
    private final GUI gui;
    private State currentState;

    public State getState() {
        return currentState;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public Game(int width, int height, GUI gui, State currentState) {
        this.gui = gui;
        this.currentState = currentState;
    }

    private void run() throws IOException, InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
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
            new Game(21, 21, new GUIForLanterna(21, 21),
                new RegularMenuState(new MainMenu())).run();
        } catch(Exception e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(INFO, "A problem has occured running the game loop: ", e);
        }
    }
}