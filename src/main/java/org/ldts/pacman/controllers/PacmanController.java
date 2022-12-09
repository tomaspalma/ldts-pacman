package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.*;

import java.io.IOException;

public class PacmanController extends Controller<Arena> {
    private PacmanDirection wantedDirection;
    private final ArenaController parentController;
    private final Pacman pacman;

    public PacmanController(ArenaController parentController, Arena model) {
        super(model);
        this.parentController = parentController;

        pacman = getModel().getPacman();
        this.pacman.addObserver((GameObserver) parentController);
        this.wantedDirection = (PacmanDirection) pacman.getCurrentDirection();
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        movePacmanAccordingTo(action);
    }

    public void movePacmanAccordingTo(GameActions.ControlActions action) {
        switch (action) {
            case MOVE_LEFT: this.wantedDirection = new PacmanDirectionLeft(this.pacman); break;
            case MOVE_DOWN: this.wantedDirection = new PacmanDirectionDown(this.pacman); break;
            case MOVE_RIGHT: this.wantedDirection = new PacmanDirectionRight(this.pacman); break;
            case MOVE_UP: this.wantedDirection = new PacmanDirectionUp(this.pacman); break;
            default: break;
        }

        movePacman();
    }

    private void movePacman() {
        this.tryToChangeToWantedDirection();

        PacmanDirection currentPacmanDirection = (PacmanDirection) this.pacman.getCurrentDirection();
        Position currentDirectionNextPosition = currentPacmanDirection.getNextPosition();

        boolean isAbleToMoveInNextPosition = !currentDirectionNextPosition.isOnSomeObstaclePosition()
                && !currentDirectionNextPosition.isOnGatePosition();

        if(isAbleToMoveInNextPosition) {
            Position tileTrimmedPacmanPosition = pacman.switchTile(currentDirectionNextPosition);
            pacman.setPosition(tileTrimmedPacmanPosition);

            actIfCollisionWithSpecialEntitiesAt(tileTrimmedPacmanPosition);
        }
    }

    private void tryToChangeToWantedDirection() {
        Position newWantedPacPosition = this.wantedDirection.getNextPosition();

        boolean isAbleToMoveInWantedDirection = !newWantedPacPosition.isOnSomeObstaclePosition()
                && !newWantedPacPosition.isOnGatePosition();

        if(isAbleToMoveInWantedDirection) {
            pacman.setCurrentDirectionTo(this.wantedDirection);

            this.actIfCollisionWithSpecialEntitiesAt(newWantedPacPosition);
        }

    }

    private void actIfCollisionWithSpecialEntitiesAt(Position newPacmanPosition) {
        boolean collidedWithEdible = newPacmanPosition.isOnFixedEdiblePosition();
        boolean collidedWithGhost = newPacmanPosition.isOnSomeGhostPosition();

        if(collidedWithEdible) {
            this.pacman.notifyObserversItAteFixedEdibleAt(newPacmanPosition);
        } else if (collidedWithGhost) {
            this.pacman.notifyObserversItCollidedWithGhostAt(newPacmanPosition);
        }
    }

    public void killPacmanAt(Position position) {
        this.pacman.die(position);
    }

    private void changeLife(int i) {
        pacman.setLivesTo(i);
    }
}
