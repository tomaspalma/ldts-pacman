package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.animations.Animation;
import org.ldts.pacman.models.game.GameActions;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.arena.Arena;
import org.ldts.pacman.models.game.arena.grid.TeletransporterTile;
import org.ldts.pacman.models.game.entities.MovableEntityDirection;
import org.ldts.pacman.models.game.entities.pacman.animations.PacmanAnimation;
import org.ldts.pacman.models.game.entities.pacman.directions.*;
import org.ldts.pacman.models.game.entities.pacman.Pacman;

import java.io.IOException;

public class PacmanController extends Controller<Arena> {
    private PacmanDirection wantedDirection;
    private final ArenaController parentController;
    private Pacman pacman;

    public PacmanController(ArenaController parentController, Arena model) {
        super(model);
        this.parentController = parentController;

        pacman = getModel().getPacman();
        this.pacman.addObserver(parentController);

        MovableEntityDirection currentDirection = pacman.getCurrentDirection();
        this.wantedDirection = (PacmanDirection) currentDirection;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public void setPacmanTo(Pacman pacman) {
        this.pacman = pacman;
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException, InterruptedException {
        movePacmanAccordingTo(action);
        executeAnimations();
    }

    public PacmanDirection getWantedDirection() {
        return wantedDirection;
    }

    public void setWantedDirectionTo(PacmanDirection pacmanDirection) {
        this.wantedDirection = pacmanDirection;
    }

    private void executeAnimations() {
        for(PacmanAnimation pacmanAnimation: this.pacman.getAnimationsToExecute()) {
            pacmanAnimation.step();
        }

        this.pacman.getAnimationsToExecute().removeIf(Animation::isFinished);
    }

    public void movePacmanAccordingTo(GameActions.ControlActions action) throws InterruptedException {
        int pacX = pacman.getPosition().getX();
        int pacY = pacman.getPosition().getY();
        boolean isAbleToReceiveUserInput = !(getModel().getGameGrid().get(pacY - 1).get(pacX) instanceof TeletransporterTile);

        if(isAbleToReceiveUserInput) {
            switch (action) {
                case MOVE_LEFT:
                    this.wantedDirection = new PacmanDirectionLeft(this.pacman);
                    break;
                case MOVE_DOWN:
                    this.wantedDirection = new PacmanDirectionDown(this.pacman);
                    break;
                case MOVE_RIGHT:
                    this.wantedDirection = new PacmanDirectionRight(this.pacman);
                    break;
                case MOVE_UP:
                    this.wantedDirection = new PacmanDirectionUp(this.pacman);
                    break;
                default:
                    break;
            }
        }

        movePacman();
    }

    private void movePacman() throws InterruptedException {
        this.actIfCollisionWithSpecialEntitiesAt(pacman.getPosition());

        this.tryToChangeToWantedDirection();

        PacmanDirection currentPacmanDirection = (PacmanDirection) this.pacman.getCurrentDirection();
        Position currentDirectionNextPosition = currentPacmanDirection.getNextPosition();

        boolean isAbleToMoveInNextPosition = !parentController.getArenaTileAt(currentDirectionNextPosition).containsObstacle()
                && !parentController.getArenaTileAt(currentDirectionNextPosition).containsGate();

        if(isAbleToMoveInNextPosition) {
            Position tileTrimmedPacmanPosition = pacman.switchTile(currentDirectionNextPosition);
            pacman.setPosition(tileTrimmedPacmanPosition);
        }
    }

    private void tryToChangeToWantedDirection() throws InterruptedException {
        Position newWantedPosition = this.wantedDirection.getNextPosition();

        boolean isAbleToMoveInWantedDirection = !parentController.getArenaTileAt(newWantedPosition).containsObstacle()
                && !parentController.getArenaTileAt(newWantedPosition).containsGate();

        if(isAbleToMoveInWantedDirection) {
            pacman.setCurrentDirectionTo(this.wantedDirection);
            this.actIfCollisionWithSpecialEntitiesAt( newWantedPosition);
        }

    }

    private void actIfCollisionWithSpecialEntitiesAt(Position pacmanPosition) throws InterruptedException {
        boolean collidedWithEdible = parentController.getArenaTileAt(pacmanPosition).containsFixedEdible();
        boolean collidedWithGhost = parentController.getArenaTileAt(pacmanPosition).containsGhost();

        if(collidedWithEdible) {
            this.pacman.notifyObserversItAteFixedEdibleAt(pacmanPosition);

        }

        if (collidedWithGhost) {
            this.pacman.notifyObserversItCollidedWithGhostAt(pacmanPosition);
        }
    }
}
