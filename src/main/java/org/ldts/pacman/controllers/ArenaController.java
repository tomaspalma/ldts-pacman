package org.ldts.pacman.controllers;

import org.ldts.pacman.Game;
import org.ldts.pacman.sounds.IntroSound;
import org.ldts.pacman.sounds.PacmanMunch;
import org.ldts.pacman.sounds.SFX;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.Ghost;
import org.ldts.pacman.models.PacmanObserver;
import org.ldts.pacman.models.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ArenaController extends Controller<Arena> implements PacmanObserver {
    private final PacmanController pacmanController;
    private final RegularGhostController regularGhostController;
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
            sounds = Arrays.asList(new PacmanMunch());
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        if (getModel().getGeneralFixedEdibleList().isEmpty())
            game.setState(null); // TODO set state para um menu a dizer que venceu

        switch (action) {
            case EXIT:
                game.setState(null);
                break;
            // case SWITCH_TO_PAUSE_MENU: entities.setState(new PauseMenu()); break;
            default:
                stepChildControllers(game, action, time);
                break;
        }
    }

    public void processPacmanLoseLife() {
        getModel().getPacman().die();
        getModel().restart();
    }

    private void stepChildControllers(Game game, GameActions.ControlActions action, long time) throws IOException {
        pacmanController.step(game, action, time);
        regularGhostController.step(game, action, time);
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
