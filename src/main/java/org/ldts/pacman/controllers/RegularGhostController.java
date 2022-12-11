package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.*;

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

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        for(RegularGhost regularGhost: regularGhostsToControl) {
            /*if(regularGhost instanceof Clyde || regularGhost instanceof Inky) {
                System.out.println(regularGhost.getClass());
                System.out.println("Current: " + regularGhost.getCurrentState().getClass());
                System.out.println("Previous: " + regularGhost.getPreviousState().getClass());
                System.out.println("Can move to gate: " + regularGhost.canCurrentlyMoveToGhostHouseGate());
            }*/

            if(stateChangedIn(regularGhost)) regularGhost.getCurrentState().applyChangesToGhost();
            
            if(numberOfSteps > 1) {
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

        if(isGhostDead)
            currentGhostState.transitionToState(new GhostHouseState(regularGhost));
    }

    private boolean stateChangedIn(Ghost ghost) {
        return !ghost.getCurrentState().getClass().equals(ghost.getPreviousState().getClass());
    }

    public void killGhost(Ghost ghost) {
        ghost.die();
    }

    private void moveGhost(Ghost ghost, Position newPosition) {
        Position realNewPosition = ghost.switchTile(newPosition);

        ghost.setCurrentDirectionTo(ghost.getCurrentDirection().generateNextDirectionAfterChangeTo(realNewPosition));
        ghost.setPosition(realNewPosition);

        this.reviveDeadGhost((RegularGhost) ghost);

        if(realNewPosition.isOnGatePosition())
            ghost.setCanCurrentlyMoveToGhostHouseGateTo(false);

        checkCollisionWithPacman(ghost, realNewPosition);
    }

    private void checkCollisionWithPacman(Ghost ghost, Position newPosition) {
        boolean onFrightenedState = ghost.getCurrentState() instanceof FrightenedState;
        if(onFrightenedState) return;

        if(newPosition.equals(getModel().getPacman().getPosition()))
            parentController.processPacmanLoseLife();

        if(ghost.getPosition() == getModel().getPacman().getPosition()) parentController.processPacmanLoseLife();
    }
}
