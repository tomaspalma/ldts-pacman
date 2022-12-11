package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.EatenPowerPelletObserver;
import org.ldts.pacman.models.PacmanObserver;

import java.io.IOException;

public class ArenaController extends Controller<Arena> implements PacmanObserver {
    private final PacmanController pacmanController;
    private final RegularGhostController regularGhostController;
    private int currentLevel = 0;

    public PacmanController getPacmanController() {
        return pacmanController;
    }

    public RegularGhostController getRegularGhostController() {
        return regularGhostController;
    }

    public ArenaController(Arena model) {
        super(model);

        this.pacmanController = new PacmanController(this, model);
        this.regularGhostController = new RegularGhostController(this, model);
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        if(getModel().getGeneralFixedEdibleList().isEmpty()) game.setState(null); // TODO set state para um menu a dizer que venceu

        switch(action) {
            case EXIT: game.setState(null); break;
            //case SWITCH_TO_PAUSE_MENU: game.setState(new PauseMenu()); break;
            default: stepChildControllers(game, action, time); break;
        }
    }

    public void processPacmanLoseLife() {
        getModel().getPacman().die(getModel().getStartPacmanPosition());
    }

    private void stepChildControllers(Game game, GameActions.ControlActions action, long time) throws IOException {
        pacmanController.step(game, action, time);
        regularGhostController.step(game, action, time);
    }

    @Override
    public void handlePacmanEatFixedEdible() {

    }

    @Override
    public void handlePacmanCollisionWithGhost() {

    }
}
