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
    private State state;

    public Game() {
        state = new ArenaState(new Arena(50, 100));
    }

    private void run() throws IOException {
       try {
           gui = new GUIForLanterna(50, 100);
       } catch(IOException | URISyntaxException | FontFormatException e) {
           e.printStackTrace();
       }

       while (this.state != null) {
           // state.step()
       }

       gui.close();
    }

    public void setState(State state) {
        this.state = state;
    }

    public static void main(String[] args) throws IOException {
        new Game().run();
    }
}