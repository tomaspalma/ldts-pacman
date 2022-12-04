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

    private void switchTile(Position position) {
        int pacmanX = getModel().getPacman().getPosition().getX();
        int pacmanY = getModel().getPacman().getPosition().getY();
        
        getModel().getGameGrid().get(pacmanY - 1).get(pacmanX).removeChild(pacman);
        getModel().getGameGrid().get(position.getY() - 1).get(position.getX()).addChild(pacman);
    }

    private void movePacman(Position position) {

        if (!position.isOnSomeObstaclePosition()) {
            this.switchTile(position);

            pacman.setPosition(position);

            controlNotOutOfBounds(position);

            if(position.isOnFixedEdiblePosition()) {
                eatEdibleAt(position);
            } else if (position.isOnSomeGhostPosition()) {
                processCollisionWithGhostAt(position);
            }
        }
    }

    private void processCollisionWithGhostAt(Position position) {
        Tile currentTile = getModel().getGameGrid().get(position.getY() - 1).get(position.getX());
        Ghost ghost = currentTile.getGhost();
        assert(ghost != null);
        
        switch(ghost.getCollisionWithPacmanResult()) {
            case KILL_GHOST: parentController.getRegularGhostController().killGhost(ghost); break;
            case KILL_PACMAN: parentController.processPacmanLoseLife(); break;
        }
    }

    private void controlNotOutOfBounds(Position position) {
        int x = position.getY();
        int y = position.getY();
        
        if(x < 0) pacman.setPosition(new Position(getModel().getWidth() - 1, y, getModel()));
        else if(x > getModel().getWidth()) pacman.setPosition(new Position(3, y, getModel()));
    }

    private void eatEdibleAt(Position position) {
        Tile currentTile = getModel().getGameGrid().get(position.getY() - 1).get(position.getX());
        FixedEdible currentEdible = currentTile.getFixedEdible();
        assert(currentEdible != null);

        if(currentEdible instanceof GameObservable) {
            ((GameObservable) currentEdible).notifyObservers();
        }

        getModel().sumScoreWith(1);
        getModel().removeFromGameGridAt(position, currentEdible);
        getModel().getGeneralFixedEdibleList().remove(currentEdible);
    }

    private void changeLife(int i) {
        pacman.setLivesTo(i);
    }
}
