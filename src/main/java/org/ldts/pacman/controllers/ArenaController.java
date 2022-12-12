package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.game.Clock;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.arena.grid.Tile;
import org.ldts.pacman.models.game.entities.fixededibles.FixedEdible;
import org.ldts.pacman.models.game.entities.ghost.Ghost;
import org.ldts.pacman.models.PacmanObserver;
import org.ldts.pacman.models.*;
import org.ldts.pacman.models.game.entities.ghost.RegularGhost;
import org.ldts.pacman.models.game.entities.ghost.states.FrightenedState;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        boolean levelEnded = getModel().getGeneralFixedEdibleList().isEmpty();
        if(levelEnded) {
            this.switchToNextLevel();
            this.currentLevel = (this.currentLevel + 1) % getModel().getLevels().size();
        }

        this.checkConditionsToPauseLevelClock();

        getModel().getLevels().get(currentLevel).step();

        switch (action) {
            case EXIT:
                game.setState(null);
                break;
            //case SWITCH_TO_PAUSE_MENU: entities.setState(new PauseMenu()); break;
            default:
                stepChildControllers(game, action, time);
                break;
        }
    }

    private void switchToNextLevel() {
        this.currentLevel = (this.currentLevel + 1) % getModel().getLevels().size();
    }

    private void checkConditionsToPauseLevelClock() {
        Clock levelClock = getModel().getLevels().get(this.currentLevel).getClock();
        for(RegularGhost regularGhost: getModel().getRegularGhostsList()) {
            if(regularGhost.getCurrentState() instanceof FrightenedState) {
               levelClock.pause();
               return;
            }
        }

        if(levelClock.isPaused())
            levelClock.unpause();
    }

    public void processPacmanLoseLife() {
        getModel().getPacman().die();
        this.putCurrentLevelBackToStartPositions(); 
    }

    private void stepChildControllers(Game game, GameActions.ControlActions action, long time) throws IOException {
        pacmanController.step(game, action, time);
        regularGhostController.step(game, action, time);
    }

    private void putCurrentLevelBackToStartPositions() {
        regularGhostController.putGhostsBackInInitialState();
        getModel().getLevels().get(this.currentLevel).restart();
    }

    @Override
    public void changeOnPacmanEatFixedEdibleAt(Position position) {
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

    public Tile getArenaTileAt(Position position) {
        return getModel().getArenaTileAt(position);
    }

    @Override
    public void changeOnPacmanCollisionWithGhostAt(Position position) {
        Tile currentTile = getModel().getGameGrid().get(position.getY() - 1).get(position.getX());
        Ghost ghost = currentTile.getGhost();

        GameActions.GhostCollisionWithPacman collisionWithPacmanResult = ghost.getCollisionWithPacmanResult();
        switch(collisionWithPacmanResult) {
            case KILL_GHOST:
                regularGhostController.killGhost(ghost);
                break;
            case KILL_PACMAN: pacmanController.killPacmanAt(); break;
            default: break;
        }
    }
}
