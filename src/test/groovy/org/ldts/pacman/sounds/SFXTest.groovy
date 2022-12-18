package org.ldts.pacman.sounds

import spock.lang.Specification

import javax.sound.sampled.Clip

class SFXTest extends Specification {
    private def clip

    def setup() {
        clip = Mock(Clip.class)
    }

    def "EatGhostSound"() {
        given:
        def sound = new EatGhostSound(clip);

        when:
        sound.play()

        then:
        1 * clip.isRunning()
        1 * clip.setMicrosecondPosition(0)
        1 * clip.start()
    }
}
