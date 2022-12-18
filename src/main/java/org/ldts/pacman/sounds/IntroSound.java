package org.ldts.pacman.sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class IntroSound extends SFX {
    public IntroSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super("sounds/pacman_beginning.wav");
    }

    private IntroSound(Clip sound) {
        super(sound);
    }

    @Override
    public void play() {
        sound.start();
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
