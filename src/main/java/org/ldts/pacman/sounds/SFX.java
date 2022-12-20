package org.ldts.pacman.sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public abstract class SFX {
    protected Clip sound;

    protected SFX(String soundFile) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        URL resource = getClass().getClassLoader().getResource(soundFile);

        assert resource != null;
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(resource);
        sound = AudioSystem.getClip();
        sound.open(audioInputStream);
        FloatControl gainControl = (FloatControl) (sound.getControl(FloatControl.Type.MASTER_GAIN));
        gainControl.setValue(-20.0f);
    }

    protected SFX(Clip sound) {
        this.sound = sound;
    }

    public abstract void play();

    public void stop() {
        sound.stop();
    }
}
