package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.models.menus.GameOverMenu;
import org.ldts.pacman.models.menus.PauseMenu;
import org.ldts.pacman.sounds.*;
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
import org.ldts.pacman.states.ArenaState;
import org.ldts.pacman.states.menus.PauseMenuState;
import org.ldts.pacman.states.menus.RegularMenuState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;

public class ArenaController extends Controller<Arena> implements PacmanObserver {
    private final PacmanController pacmanController;
    private final RegularGhostController regularGhostController;
    private int ateGhostPoints = 200;
    private List<SFX> sounds;
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

        try {
            sounds = Arrays.asList(new PacmanMunch(), new PacmanDeathSound(), new EatGhostSound());
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.actIfLevelEnded();

        this.checkConditionsToPauseLevelClock();

        getModel().getLevels().get(currentLevel).step();

        if (getModel().getPacman().getRemainingLives() == 0)
            game.setState(new RegularMenuState(new GameOverMenu("loss")));

        switch (action) {
            case EXIT:
                game.setState(null);
                break;
            case SWITCH_TO_PAUSE_MENU:
                game.setState(new PauseMenuState(new PauseMenu((ArenaState) game.getArenaState())));
                break;
            default:
                stepChildControllers(game, action, time);
                break;
        }
    }

    private void actIfLevelEnded() {
        boolean levelEnded = getModel().getGeneralFixedEdibleList().isEmpty();
        if(levelEnded) {
            this.switchToNextLevel();
        }
    }

    private void switchToNextLevel() {
        this.currentLevel = (this.currentLevel + 1) % getModel().getLevels().size();
        this.restoreFixedEdibles();
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

    private void restoreFixedEdibles() {
        getModel().getGeneralFixedEdibleList().clear();
        for(FixedEdible fixedEdible: getModel().getEatenFixedEdiblePool()) {
            getModel().getGeneralFixedEdibleList().add(fixedEdible);
        }
    }

    @Override
    public void changeOnPacmanEatFixedEdibleAt(Position position) {
        Tile currentTile = getModel().getGameGrid().get(position.getY() - 1).get(position.getX());
        FixedEdible currentEdible = currentTile.getFixedEdible();
        assert (currentEdible != null);

        if (currentEdible instanceof PowerPelletObservable powerPelletObservable) {
            powerPelletObservable.notifyObservers();
        }

        sounds.get(0).play();
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
                getModel().sumScoreWith(this.ateGhostPoints);
                sounds.get(2).play();
                break;
            case KILL_PACMAN:
                ateGhostPoints = 200;
                pacmanController.killPacmanAt();
                sounds.get(1).play();
                break;
            default: break;
        }
    }
}
