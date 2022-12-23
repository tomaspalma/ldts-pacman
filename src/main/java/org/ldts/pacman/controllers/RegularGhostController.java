package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.game.GameActions;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.arena.Arena;
import org.ldts.pacman.models.game.entities.ghost.*;
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirection;
import org.ldts.pacman.models.game.entities.ghost.regularghost.Blinky;
import org.ldts.pacman.models.game.entities.ghost.regularghost.RegularGhost;
import org.ldts.pacman.models.game.entities.ghost.states.*;

import java.io.IOException;
import java.util.List;

public class RegularGhostController extends Controller<Arena> {
    private final List<RegularGhost> regularGhostsToControl;
    private final ArenaController parentController;
    private int numberOfSteps = 0;

    public RegularGhostController(ArenaController parentController, Arena model) {
        super(model);
        this.parentController = parentController;
        this.regularGhostsToControl = getModel().getRegularGhostsList();
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException, InterruptedException {
        for(RegularGhost regularGhost: regularGhostsToControl) {
            if(stateChangedIn(regularGhost))
                regularGhost.getCurrentState().applyChangesToGhost();

            if(numberOfSteps >= 1.1) {
                moveGhost(regularGhost, regularGhost.getCurrentState().getNextPosition());
                numberOfSteps = 0;
            } else {
                numberOfSteps++;
            }
        }
    }

    private void reviveDeadGhost(RegularGhost regularGhost) {
        GhostState currentGhostState = regularGhost.getCurrentState();

        boolean isGhostDead = currentGhostState instanceof DeadState;

        if(isGhostDead) {
            regularGhost.setPreviousStateTo(new GhostHouseState(regularGhost));
            regularGhost.setCurrentStateTo(new ChasingState(regularGhost));
        }
    }

    private boolean stateChangedIn(Ghost ghost) {
        return !ghost.getCurrentState().getClass().equals(ghost.getPreviousState().getClass());
    }

    public void resetGhostPositions() {
        Position newGhostPosition = null;
        for(RegularGhost ghost: getModel().getRegularGhostsList()) {
            if(ghost instanceof Blinky) {
                newGhostPosition = getModel().getGhostHouse().getExitPosition();
            } else {
                newGhostPosition = getModel().getGhostHouse().getAvailablePosition();

                getModel().getGhostHouse().getGhostHolder().add(ghost);
                ghost.setCurrentStateTo(new GhostHouseState(ghost));
                ghost.setPreviousStateTo(new GhostHouseState(ghost));
            }

            ghost.setPosition(ghost.switchTile(newGhostPosition));
        }
    }

    public void killGhost(Ghost ghost) {
        ghost.die();
    }

    private void moveGhost(Ghost ghost, Position newPosition) throws InterruptedException {
        checkCollisionWithPacman(ghost, ghost.getPosition());

        boolean isOnGhostHouseAndCanLeaveIt = (ghost.getPreviousState() instanceof GhostHouseState
                && ghost.getCurrentState().canMoveOutsideGhostHouse());

        if(isOnGhostHouseAndCanLeaveIt) {
            ghost.setPosition(ghost.switchTile(getModel().getGhostHouse().getExitPosition()));
            ghost.setPreviousStateTo(ghost.getCurrentState());
            getModel().getGhostHouse().getGhostHolder().remove(ghost);
            return;
        }

        Position realNewPosition = ghost.switchTile(newPosition);

        GhostDirection currentGhostDirection = (GhostDirection) ghost.getCurrentDirection();
        ghost.setCurrentDirectionTo(currentGhostDirection.generateNextDirectionAfterChangeTo(realNewPosition));
        ghost.setPosition(realNewPosition);

        this.reviveDeadGhost((RegularGhost) ghost);

    }

    public void putGhostsBackInInitialState() {
        Position ghostStartPosition = null;
        for (RegularGhost regularGhost : getModel().getRegularGhostsList()) {
            ghostStartPosition = regularGhost.getStartPosition();
            regularGhost.switchTile(ghostStartPosition);
            regularGhost.setPosition(ghostStartPosition);

            regularGhost.returnToOriginalState();
        }
    }

    private void checkCollisionWithPacman(Ghost ghost, Position position) {
        if(position == null)
            return;

        boolean onFrightenedState = ghost.getCurrentState() instanceof FrightenedState;
        boolean onDeadState = ghost.getCurrentState() instanceof  DeadState;
        if(onFrightenedState || onDeadState) return;

        if(position.equals(getModel().getPacman().getPosition())) {
            parentController.processPacmanLoseLife();
        }
    }
}
