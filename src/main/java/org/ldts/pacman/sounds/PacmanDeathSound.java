package org.ldts.pacman.sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class PacmanDeathSound extends SFX {
    public PacmanDeathSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super("sounds/pacman_death.wav");
    }

    @Override
    public void play() {
        sound.setMicrosecondPosition(0);
        sound.start();
    }
}
