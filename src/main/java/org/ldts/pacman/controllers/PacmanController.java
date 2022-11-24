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
            case MOVE_LEFT:
                getModel().getPacman().changeOrientation("LEFT");
                break;
            case MOVE_DOWN:
                getModel().getPacman().changeOrientation("DOWN");
                break;
            case MOVE_RIGHT:
                getModel().getPacman().changeOrientation("RIGHT");
                break;
            case MOVE_UP:
                getModel().getPacman().changeOrientation("UP");
                break;
            default:
                break;
        }
        movePacman();
    }

    public void movePacman() {
        switch (getModel().getPacman().getDrawSymbol()) {
            case "D":
                movePacmanUp();
                break;
            case "C":
                movePacmanDown();
                break;
            case "A":
                movePacmanLeft();
                break;
            case "B":
                movePacmanRight();
                break;
            default:
                break;
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
