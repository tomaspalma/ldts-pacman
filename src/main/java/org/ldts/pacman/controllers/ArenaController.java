package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.EatenPowerPelletObserver;
import org.ldts.pacman.models.Ghost;
import org.ldts.pacman.models.PacmanObserver;
import org.ldts.pacman.models.*;

import java.io.IOException;

public class ArenaController extends Controller<Arena> implements PacmanObserver {
    private final PacmanController pacmanController;
    private final RegularGhostController regularGhostController;

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
        if (getModel().getGeneralFixedEdibleList().isEmpty())
            game.setState(null); // TODO set state para um menu a dizer que venceu

        switch (action) {
            case EXIT:
                game.setState(null);
                break;
            // case SWITCH_TO_PAUSE_MENU: game.setState(new PauseMenu()); break;
            default:
                stepChildControllers(game, action, time);
                break;
        }
    }

    public void processPacmanLoseLife() {
        getModel().getPacman().die(getModel().getStartPacmanPosition());
        getModel().restart();
    }

    private void stepChildControllers(Game game, GameActions.ControlActions action, long time) throws IOException {
        pacmanController.step(game, action, time);
        regularGhostController.step(game, action, time);
        getModel().getClock().step();
    }

    @Override
    public void changeOnPacmanEatFixedEdibleAt(Position position) {
        System.out.println("chega aqui?");
        Tile currentTile = getModel().getGameGrid().get(position.getY() - 1).get(position.getX());
        FixedEdible currentEdible = currentTile.getFixedEdible();
        assert (currentEdible != null);

        if (currentEdible instanceof PowerPelletObservable powerPelletObservable) {
            powerPelletObservable.notifyObservers();
        }

        getModel().sumScoreWith(1);
        getModel().removeFromGameGridAt(position, currentEdible);
        getModel().getGeneralFixedEdibleList().remove(currentEdible);
    }

    @Override
    public void changeOnPacmanCollisionWithGhostAt(Position position) {
        Tile currentTile = getModel().getGameGrid().get(position.getY() - 1).get(position.getX());
        Ghost ghost = currentTile.getGhost();

        switch(ghost.getCollisionWithPacmanResult()) {
            case KILL_GHOST: regularGhostController.killGhost(ghost); break;
            case KILL_PACMAN: pacmanController.killPacmanAt(position); break;
            default: break;
        }
    }

    private void restart() {

    }
}
