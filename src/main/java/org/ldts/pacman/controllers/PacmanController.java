package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.*;
import org.ldts.pacman.models.animations.Animation;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.pacman.animations.PacmanAnimation;
import org.ldts.pacman.models.game.entities.pacman.directions.*;
import org.ldts.pacman.models.game.entities.pacman.Pacman;

import java.io.IOException;

public class PacmanController extends Controller<Arena> {
    private PacmanDirection wantedDirection;
    private final ArenaController parentController;
    private final Pacman pacman;

    public PacmanController(ArenaController parentController, Arena model) {
        super(model);
        this.parentController = parentController;

        pacman = getModel().getPacman();
        this.pacman.addObserver(parentController);

        MovableEntityDirection currentDirection = pacman.getCurrentDirection();
        this.wantedDirection = (PacmanDirection) currentDirection;
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        movePacmanAccordingTo(action);
        executeAnimations();
    }

    private void executeAnimations() {
        for(PacmanAnimation pacmanAnimation: this.pacman.getAnimationsToExecute()) {
            pacmanAnimation.step();
        }

        this.pacman.getAnimationsToExecute().removeIf(Animation::isFinished);
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

        boolean isAbleToMoveInNextPosition = !(parentController.getArenaTileAt(currentDirectionNextPosition).containsObstacle())
                && !(parentController.getArenaTileAt(currentDirectionNextPosition).containsGate());

        if(isAbleToMoveInNextPosition) {
            Position tileTrimmedPacmanPosition = pacman.switchTile(currentDirectionNextPosition);
            pacman.setPosition(tileTrimmedPacmanPosition);

            actIfCollisionWithSpecialEntitiesAt(tileTrimmedPacmanPosition);
        }
    }
    private void tryToChangeToWantedDirection() {
        Position newWantedPosition = this.wantedDirection.getNextPosition();

        boolean isAbleToMoveInWantedDirection = !(parentController.getArenaTileAt(newWantedPosition).containsObstacle())
                && !(parentController.getArenaTileAt(newWantedPosition).containsGate());

        if(isAbleToMoveInWantedDirection) {
            pacman.setCurrentDirectionTo(this.wantedDirection);
            this.actIfCollisionWithSpecialEntitiesAt( newWantedPosition);
        }

    }

    private void actIfCollisionWithSpecialEntitiesAt(Position newPacmanPosition) {
        boolean collidedWithEdible = parentController.getArenaTileAt(newPacmanPosition).containsFixedEdible();
        boolean collidedWithGhost = parentController.getArenaTileAt(newPacmanPosition).containsGhost();

        if(collidedWithEdible)
            this.pacman.notifyObserversItAteFixedEdibleAt(newPacmanPosition);

        if (collidedWithGhost)
            this.pacman.notifyObserversItCollidedWithGhostAt(newPacmanPosition);
    }

    public void killPacmanAt() {
        parentController.processPacmanLoseLife();
    }

    private void changeLife(int i) {
        pacman.setLivesTo(i);
    }
}
