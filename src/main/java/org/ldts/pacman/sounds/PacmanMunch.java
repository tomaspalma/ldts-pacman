package org.ldts.pacman.sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class PacmanMunch extends SFX {
    public PacmanMunch() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super("sounds/pacman_chomp.wav");
    }

    @Override
    public void play() {
        if (sound.isRunning())
            return;
        sound.setMicrosecondPosition(0);
        sound.start();
    }
}
