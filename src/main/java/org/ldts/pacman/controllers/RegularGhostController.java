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
            if(stateChangedIn(regularGhost)) regularGhost.getCurrentState().applyChangesToGhost();
            
            if(numberOfSteps > 3) {
                moveGhost(regularGhost, regularGhost.getCurrentState().getNextPosition());
                numberOfSteps = 0;
            } else {
                numberOfSteps++;
            } 
            
        }
    }

    private boolean stateChangedIn(Ghost ghost) {
        return !ghost.getCurrentState().getClass().equals(ghost.getPreviousState().getClass());
    }

    public void killGhost(Ghost ghost) {
        ghost.die();
    }

    private void moveGhost(Ghost ghost, Position newPosition) {
        int ghostX = ghost.getPosition().getX();
        int ghostY = ghost.getPosition().getY();
        
        if(ghost.getCurrentState() instanceof FrightenedState) {
            getModel().getGameGrid().get(ghostY - 1).get(ghostX).removeChild(ghost);            
            getModel().getGameGrid().get(newPosition.getY() - 1).get(newPosition.getX()).addChild(ghost);

            ghost.setCurrentDirectionTo(ghost.getCurrentDirection().generateNextDirectionAfterChangeTo(newPosition));
            ghost.setPosition(newPosition);
        }
    }

    public void actionBasedOnCollisionResult(GameActions.GhostCollisionWithPacman result) {
    }
}
