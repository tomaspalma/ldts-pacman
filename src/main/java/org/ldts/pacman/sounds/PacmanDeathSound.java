package org.ldts.pacman.sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class PacmanDeathSound implements SFX {
    private final Clip sound;

    public PacmanDeathSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL resource = getClass().getClassLoader().getResource("sounds/pacman_death.wav");

        assert resource != null;
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(resource);
        sound = AudioSystem.getClip();
        sound.open(audioInputStream);
    }

    @Override
    public void play() {
        sound.setMicrosecondPosition(0);
        sound.start();
    }

    @Override
    public void stop() {
        sound.stop();
    }
}
