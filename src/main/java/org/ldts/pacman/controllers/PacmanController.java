package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.*;

import java.io.IOException;
import java.util.HashMap;

public class PacmanController extends Controller<Arena> {
    private GameActions.ControlActions lastAction = GameActions.ControlActions.MOVE_LEFT;
    private String wantedOrientation = "LEFT";
    // Provavelmente também poderemos adicionar uma nova classe chamada SubController para o pacman e o regular ghost
    private ArenaController parentController;
    private Pacman pacman;

    public PacmanController(ArenaController parentController, Arena model) {
        super(model);
        this.parentController = parentController;
        pacman = getModel().getPacman();
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        changeOrientation(action);
        movePacman();
    }

    public void changeOrientation(GameActions.ControlActions action) {
        switch (action) {
            case MOVE_LEFT:
                tryToChangePacmanOrientationTo("LEFT");
                break;
            case MOVE_DOWN:
                tryToChangePacmanOrientationTo("DOWN");
                break;
            case MOVE_RIGHT:
                tryToChangePacmanOrientationTo("RIGHT");
                break;
            case MOVE_UP:
                tryToChangePacmanOrientationTo("UP");
                break;
            default:
                tryToChangePacmanOrientationTo(wantedOrientation);
                break;
        }
    }

    private void tryToChangePacmanOrientationTo(String newPacmanOrientation) {
        Position newPacmanPosition = getNewPositionToMoveTo(newPacmanOrientation);
        if(!getModel().isObstacleAt(newPacmanPosition)) {
            getModel().getPacman().changeOrientation(newPacmanOrientation);
        }
        wantedOrientation = newPacmanOrientation;
    }

    private Position getNewPositionToMoveTo(String newPacmanOrientation) {
        switch(newPacmanOrientation) {
            case "UP": return pacman.getPosition().getPositionAbove();
            case "DOWN": return pacman.getPosition().getPositionBelow();
            case "LEFT": return pacman.getPosition().getPositionToTheLeft();
        }

        return pacman.getPosition().getPositionToTheRight();
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
        Ghost possibleCollidedGhost;

        if (!(getModel().isObstacleAt(position))) {
            pacman.setPosition(position);

            controlPacmanNotOutOfBounds();

            if((theoreticalEdibleIndex = getModel().getFixedEdibleAt(position)) != -1) {
                eatEdible(theoreticalEdibleIndex);
            } else if ((possibleCollidedGhost = getModel().getGhostAt(position)) != null) {
                // Ver se substituo aqui por getGhostAt ou algo do género
                // Até se calhar uma função que faça os dois
                processCollisionWithGhost(possibleCollidedGhost);
            }
        }
    }

    // ALTERAR DEPOIS AQUI EM VEZ DE SER REGULAR GHOSTS TERMOS ALGO A INDICAR SE PODE SER MORTA PELO PACMAN OU NÃO
    private void processCollisionWithGhost(Ghost ghost) {
        switch(ghost.getCollisionWithPacmanResult()) {
            case KILL_GHOST: parentController.getRegularGhostController().killGhost(ghost); break;
            case KILL_PACMAN: pacman.die(getModel().getStartPacmanPosition()); break;
        }
    }

    private void controlPacmanNotOutOfBounds() {
        int pacmanX = pacman.getPosition().getX();
        int pacmanY = pacman.getPosition().getY();
        
        if(pacmanX < 0) pacman.setPosition(new Position(getModel().getWidth(), pacman.getPosition().getY()));
        else if(pacmanX >= getModel().getWidth()) pacman.setPosition(new Position(-1, pacman.getPosition().getY()));
    }

    private void eatEdible(int i) {
        if(getModel().getGeneralFixedEdibleList().get(i) instanceof GameObservable) {
            ((GameObservable) getModel().getGeneralFixedEdibleList().get(i)).notifyObservers();
        }

        getModel().sumScoreWith(1);
        getModel().getGeneralFixedEdibleList().remove(i);
    }

    private void changeLife(int i) {
        pacman.setLivesTo(i);
    }
}
