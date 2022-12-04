package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.*;

import java.io.IOException;
import java.util.List;

public class RegularGhostController extends Controller<Arena> {
    private List<RegularGhost> regularGhostsToControl;
    private ArenaController parentController;
    public RegularGhostController(ArenaController parentController, Arena model) {
        super(model);
        this.parentController = parentController;
        this.regularGhostsToControl = getModel().getRegularGhostsList();
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        for(RegularGhost regularGhost: regularGhostsToControl) {
            if(stateChangedIn(regularGhost)) regularGhost.getCurrentState().applyChangesToGhost();

            moveGhost(regularGhost, regularGhost.getCurrentState().getNextPosition(getModel().getPacman().getPosition(), getModel().getGameGrid()));
        }
    }

    private boolean stateChangedIn(Ghost ghost) {
        return !ghost.getCurrentState().getClass().equals(ghost.getPreviousState().getClass());
    }

    public void killGhost(Ghost ghost) {
        ghost.die();
    }

    private void moveGhost(Ghost ghost, Position newPosition) {
       if(ghost.getCurrentState() instanceof FrightenedState) {
           ghost.setCurrentDirectionTo(ghost.getCurrentDirection().generateNextDirectionAfterChangeTo(newPosition));
           ghost.setPosition(newPosition);
       }
    }

    public void actionBasedOnCollisionResult(GameActions.GhostCollisionWithPacman result) {
    }

    public void executeFrightenedBehaviour(RegularGhost regularGhost) {
    }

    public void executeChaseBehaviour(RegularGhost regularGhost) {
        
    }

    public void executeScatterBehaviour(RegularGhost regularGhost) {

    }
}
