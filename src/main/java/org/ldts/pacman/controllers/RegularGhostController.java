package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.*;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.game.entities.ghost.RegularGhost;
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

            if(numberOfSteps >= 1.3) {
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

    public void killGhost(Ghost ghost) {
        ghost.die();
    }

    private void moveGhost(Ghost ghost, Position newPosition) throws InterruptedException {
        boolean isOnGhostHouseAndCanLeaveIt = (ghost.getPreviousState() instanceof GhostHouseState
                && ghost.getCurrentState().canMoveOutsideGhostHouse());

        if(isOnGhostHouseAndCanLeaveIt) {
            ghost.setPosition(getModel().getGhostHouse().getExitPosition());
            ghost.setPreviousStateTo(ghost.getCurrentState());
            return;
        }

        Position realNewPosition = ghost.switchTile(newPosition);

        ghost.setCurrentDirectionTo(ghost.getCurrentDirection().generateNextDirectionAfterChangeTo(realNewPosition));
        ghost.setPosition(realNewPosition);

        this.reviveDeadGhost((RegularGhost) ghost);

        checkCollisionWithPacman(ghost, realNewPosition);
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

    private void checkCollisionWithPacman(Ghost ghost, Position newPosition) throws InterruptedException {
        boolean onFrightenedState = ghost.getCurrentState() instanceof FrightenedState;
        if(onFrightenedState) return;

        if(newPosition.equals(getModel().getPacman().getPosition()))
            parentController.processPacmanLoseLife();
    }
}
