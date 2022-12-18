package org.ldts.pacman.sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class EatGhostSound extends SFX {
    public EatGhostSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super("sounds/pacman_eatghost.wav");
    }

    private EatGhostSound(Clip sound) {
        super(sound);
    }

    @Override
    public void play() {
        if (sound.isRunning())
            return;
        sound.setMicrosecondPosition(0);
        sound.start();
    }
}
