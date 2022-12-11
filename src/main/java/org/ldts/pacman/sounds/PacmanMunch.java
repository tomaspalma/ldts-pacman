package org.ldts.pacman.sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class PacmanMunch implements SFX {
    private final Clip sound;

    public PacmanMunch() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL resource = getClass().getClassLoader().getResource("sounds/pacman_chomp.wav");

        assert resource != null;
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(resource);
        sound = AudioSystem.getClip();
        sound.open(audioInputStream);
    }

    @Override
    public void play() {
        if (sound.isRunning())
            return;
        sound.setMicrosecondPosition(0);
        sound.start();
    }

    @Override
    public void stop() {
        sound.stop();
    }
}
