package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.*;

import java.io.IOException;

public class PacmanController extends Controller<Arena> {
    private GameActions.ControlActions lastAction = GameActions.ControlActions.MOVE_LEFT;
    private Pacman pacman;

    public PacmanController(Arena model) {
        super(model);
        pacman = getModel().getPacman();
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        changeOrientation(action);
        movePacman();
    }

    public void changeOrientation(GameActions.ControlActions action) {
        boolean userPressedMovementKey = true;

        switch (action) {
            case MOVE_LEFT:
                if (!getModel().isWallAt(pacman.getPosition().getPositionToTheLeft()))
                    getModel().getPacman().changeOrientation("LEFT");
                break;
            case MOVE_DOWN:
                if (!getModel().isWallAt(pacman.getPosition().getPositionBelow()))
                    getModel().getPacman().changeOrientation("DOWN");
                break;
            case MOVE_RIGHT:
                if (!getModel().isWallAt(pacman.getPosition().getPositionToTheRight()))
                    getModel().getPacman().changeOrientation("RIGHT");
                break;
            case MOVE_UP:
                if (!getModel().isWallAt(pacman.getPosition().getPositionAbove()))
                    getModel().getPacman().changeOrientation("UP");
                break;
            default:
                changeOrientation(lastAction);
                userPressedMovementKey = false;
                break;
        }

        if(userPressedMovementKey) lastAction = action;
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
        movePacman(pacman.getPosition().getPositionToTheLeft());
    }

    public void movePacmanRight() {
        movePacman(pacman.getPosition().getPositionToTheRight());
    }

    public void movePacmanUp() {
        movePacman(pacman.getPosition().getPositionAbove());
    }

    public void movePacmanDown() {
        movePacman(pacman.getPosition().getPositionBelow());
    }

    private void movePacman(Position position) {
        int theoreticalEdibleIndex;
        int pacmanX;

        if (!(getModel().isWallAt(position))) {
            pacman.setPosition(position);

            pacmanX = pacman.getPosition().getX();
            if(pacmanX < 0) pacman.setPosition(new Position(getModel().getWidth(), pacman.getPosition().getY()));
            else if(pacmanX >= getModel().getWidth()) pacman.setPosition(new Position(-1, pacman.getPosition().getY()));

            if((theoreticalEdibleIndex = getModel().getFixedEdibleAt(position)) != -1) {
                eatEdible(theoreticalEdibleIndex);
            } else if (getModel().isGhostAt(position)) {
                // pacman dies, restart the game
            }
        }
    }

    private void eatEdible(int i) {
        // TODO Substituir depois pelo score do edible em específico quando isso ficar feito
        getModel().sumScoreWith(1);
        getModel().getFixedEdibles().remove(i);
    }
}
