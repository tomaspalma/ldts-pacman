package org.ldts.pacman.controllers;

import org.ldts.pacman.App;
import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.Position;

import java.io.IOException;

public class PacmanController extends Controller<Arena> {
    public PacmanController(Arena model) {
        super(model);
    }

    /*
    @Override
    public void step(App game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case GUI.ACTION.LEFT -> movePacmanLeft();
            case GUI.ACTION.RIGHT -> movePacmanRight();
            case GUI.ACTION.UP -> movePacmanUp();
            case GUI.ACTION.DOWN -> movePacmanDown();
        }
    }
     */

    public void movePacmanLeft() {
        movePacman(getModel().getPacman().getPosition().getLeft());
    }

    public void movePacmanRight() {
        movePacman(getModel().getPacman().getPosition().getRight());
    }

    public void movePacmanUp() {
        movePacman(getModel().getPacman().getPosition().getUp());
    }

    public void movePacmanDown() {
        movePacman(getModel().getPacman().getPosition().getDown());
    }

    private void movePacman(Position position) {
        if (!getModel().isWall(position)) {
            getModel().getPacman().setPosition(position);
            if (getModel().isGhost(position)) {
                // pacman dies, restart the game
            }
        }
    }
}
