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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class ArenaController extends Controller<Arena> implements PacmanObserver {
    private PacmanController pacmanController;
    private RegularGhostController regularGhostController;
    private int ateGhostPoints = 200;
    private List<SFX> sounds;
    private int currentLevel = 0;

    public PacmanController getPacmanController() {
        return pacmanController;
    }

    public RegularGhostController getRegularGhostController() {
        return regularGhostController;
    }

    public List<SFX> getSounds() {
        return sounds;
    }

    public void setSounds(List<SFX> sounds) {
        this.sounds = sounds;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public ArenaController(Arena model) {
        super(model);

        this.pacmanController = new PacmanController(this, model);
        this.regularGhostController = new RegularGhostController(this, model);

        try {
            sounds = Arrays.asList(new PacmanMunch(), new PacmanDeathSound(), new EatGhostSound());
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(INFO, "Problem with sound or retrieving sound: ", e);
        }
    }

    public int getAteGhostPoints() {
        return ateGhostPoints;
    }

    public void setAteGhostPoints(int ateGhostPoints) {
        this.ateGhostPoints = ateGhostPoints;
    }

    public void setPacmanController(PacmanController pacmanController) {
       this.pacmanController = pacmanController;
    }

    public void setRegularGhostController(RegularGhostController regularGhostController) {
        this.regularGhostController = regularGhostController;
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException {
        this.actIfLevelEnded();

        this.checkConditionsToPauseLevelClock();

        getModel().getLevels().get(currentLevel).step();

        if (getModel().getPacman().getRemainingLives() == 0) {
            RegularMenuState newState = new RegularMenuState(new GameOverMenu("loss"));
            newState.getRealController().setAreSoundsSilenced(this.areSoundsSilenced);
            game.setState(newState);
        }

        switch (action) {
            case EXIT:
                game.setState(null);
                break;
            case SWITCH_TO_PAUSE_MENU:
                game.setState(new PauseMenuState(new PauseMenu((ArenaState) game.getState())));
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
        if(this.currentLevel != getModel().getLevels().size() - 1)
            this.currentLevel = (this.currentLevel + 1) % getModel().getLevels().size();

        this.restoreFixedEdibles();
        getModel().getLevels().get(0).transformItselfIntoAnotherLevel();
        getModel().getPacman().setPosition(getModel().getPacman().getStartPosition());

        regularGhostController.resetGhostPositions();
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
        if(!areSoundsSilenced)
            sounds.get(1).play();

        getModel().getPacman().die();
        this.putCurrentLevelBackToStartPositions();
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(INFO, "A problem occured while trying to sleep thread to paralyse pacman upon dying: ", e);
            Thread.currentThread().interrupt();
        }
    }

    private void stepChildControllers(Game game, GameActions.ControlActions action, long time) throws IOException, InterruptedException {
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
            getModel().getGameGrid().get(fixedEdible.getPosition().getY() - 1).get(fixedEdible.getPosition().getX()).put(fixedEdible);
        }
    }

    @Override
    public void changeOnPacmanEatFixedEdibleAt(Position position) {
        Tile currentTile = getModel().getGameGrid().get(position.getY() - 1).get(position.getX());
        FixedEdible currentEdible = currentTile.getFixedEdible();

        if(currentEdible == null)
            return;

        if (currentEdible instanceof PowerPelletObservable powerPelletObservable) {
            powerPelletObservable.notifyObservers();
        }

        if(!areSoundsSilenced)
            sounds.get(0).play();

        getModel().sumScoreWith(currentEdible.getPoints());
        getModel().removeFromGameGridAt(position, currentEdible);
        getModel().getGeneralFixedEdibleList().remove(currentEdible);
    }

    public Tile getArenaTileAt(Position position) {
        return getModel().getArenaTileAt(position);
    }

    @Override
    public void changeOnPacmanCollisionWithGhostAt(Position position) throws InterruptedException {
        Tile currentTile = getModel().getGameGrid().get(position.getY() - 1).get(position.getX());
        Ghost ghost = currentTile.getGhost();

        GameActions.GhostCollisionWithPacman collisionWithPacmanResult = ghost.getCollisionWithPacmanResult();
        switch(collisionWithPacmanResult) {
            case KILL_GHOST:
                regularGhostController.killGhost(ghost);
                getModel().sumScoreWith(this.ateGhostPoints);

                if(!areSoundsSilenced)
                    sounds.get(2).play();

                break;
            case KILL_PACMAN:
                ateGhostPoints = 200;
                this.processPacmanLoseLife();
                break;
            default: break;
        }
    }
}
