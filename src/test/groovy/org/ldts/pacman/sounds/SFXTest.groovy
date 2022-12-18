package org.ldts.pacman.sounds

import spock.lang.Specification

import javax.sound.sampled.Clip

class SFXTest extends Specification {
    private def clip

    def setup() {
        clip = Mock(Clip.class)
    }
}
