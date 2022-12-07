package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.*;

import java.io.IOException;

public class PacmanController extends Controller<Arena> {
    private PacmanDirection wantedOrientation;
    // Provavelmente também poderemos adicionar uma nova classe chamada SubController para o pacman e o regular ghost
    private final ArenaController parentController;
    private final Pacman pacman;

    public PacmanController(ArenaController parentController, Arena model) {
        super(model);
        this.parentController = parentController;
        pacman = getModel().getPacman();
        this.wantedOrientation = (PacmanDirection) pacman.getCurrentDirection();
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        changeOrientation(action);
        movePacmanMiddleware();
    }

    public void changeOrientation(GameActions.ControlActions action) {
        // isto está a dar assign de novo à variável
        switch (action) {
            case MOVE_LEFT: wantedOrientation = new PacmanDirectionLeft(this.pacman); break;
            case MOVE_DOWN: wantedOrientation = new PacmanDirectionDown(this.pacman); break;
            case MOVE_RIGHT: wantedOrientation = new PacmanDirectionRight(this.pacman); break;
            case MOVE_UP: wantedOrientation = new PacmanDirectionUp(this.pacman); break;
            default: break;
        }

        tryToChangePacmanOrientationTo(wantedOrientation);
    }

    private void tryToChangePacmanOrientationTo(PacmanDirection newPacmanDirection) {
        Position newPacmanPosition = newPacmanDirection.getNextPosition();
        if(!getModel().isObstacleAt(newPacmanPosition)) {
            getModel().getPacman().setCurrentDirectionTo(newPacmanDirection);
        }

        wantedOrientation = newPacmanDirection;
    }

    public void movePacmanMiddleware() {
        PacmanDirection currentPacmanDirection = (PacmanDirection) this.pacman.getCurrentDirection();

        if(pacman.getCurrentDirection() != null) {
           pacman.setCurrentDirectionTo(currentPacmanDirection);
        }

        movePacman(currentPacmanDirection.getNextPosition());
    }

    private void movePacman(Position newPosition) {
        if (!newPosition.isOnSomeObstaclePosition()) {
            pacman.setPosition(newPosition);

            this.pacman.switchTile(newPosition);

            if(newPosition.isOnFixedEdiblePosition()) {
                eatEdibleAt(newPosition);
            } else if (newPosition.isOnSomeGhostPosition()) {
                processCollisionWithGhostAt(newPosition);
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
            default: break;
        }
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
