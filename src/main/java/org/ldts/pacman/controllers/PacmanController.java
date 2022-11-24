package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.Position;

import java.io.IOException;

public class PacmanController extends Controller<Arena> {
    public PacmanController(Arena model) {
        super(model);
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        switch (action) {
            case MOVE_LEFT -> movePacmanLeft();
            case MOVE_RIGHT -> movePacmanRight();
            case MOVE_UP -> movePacmanUp();
            case MOVE_DOWN -> movePacmanDown();
        }
    }

    public void movePacmanLeft() {
        movePacman(getModel().getPacman().getPosition().getPositionToTheLeft());
    }

    public void movePacmanRight() {
        movePacman(getModel().getPacman().getPosition().getPositionToTheRight());
    }

    public void movePacmanUp() {
        movePacman(getModel().getPacman().getPosition().getPositionAbove());
    }

    public void movePacmanDown() {
        movePacman(getModel().getPacman().getPosition().getPositionBelow());
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
