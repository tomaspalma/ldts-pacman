package org.ldts.pacman.controllers.menus;

import org.ldts.pacman.Game;
import org.ldts.pacman.controllers.Controller;
import org.ldts.pacman.models.GameActions;
import org.ldts.pacman.models.menus.Menu;
import org.ldts.pacman.sounds.IntroSound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class RegularMenuController extends Controller<Menu> {
    private IntroSound music;

    public RegularMenuController(Menu model) {
        super(model);
        try {
            music = new IntroSound();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        music.play();
    }

    @Override
    public void step(Game game, GameActions.ControlActions action, long time) throws IOException {
        switch (action) {
            case MOVE_DOWN:
                getModel().moveDown();
                break;
            case MOVE_UP:
                getModel().moveUp();
                break;
            case EXIT:
                music.stop();
                game.setState(null);
                break;
            case SELECT:
                music.stop();
                getModel().getCurrentOption().select(game, null);
            default:
                break;
        }
    }
}
