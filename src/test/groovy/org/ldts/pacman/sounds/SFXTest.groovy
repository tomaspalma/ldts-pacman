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
        def sound = new EatGhostSound(clip)

        when:
        sound.play()

        then:
        1 * clip.isRunning()
        1 * clip.setMicrosecondPosition(0)
        1 * clip.start()
    }

    def "IntroSound"() {
        given:
        def sound = new IntroSound(clip)

        when:
        sound.play()

        then:
        1 * clip.start()
        1 * clip.loop(Clip.LOOP_CONTINUOUSLY)
    }

    def "PacmanDeathSound"() {
        given:
        def sound = new PacmanDeathSound(clip)

        when:
        sound.play()

        then:
        1 * clip.setMicrosecondPosition(0)
        1 * clip.start()
    }

    def "PacmanMunch"() {
        given:
        def sound = new PacmanMunch(clip)

        when:
        sound.play()

        then:
        1 * clip.isRunning()
        1 * clip.setMicrosecondPosition(0)
        1 * clip.start()
    }

    def "When calling the stop method, it should correctly call the stop method of its sound"() {
        given:
            def clip = Mock(Clip.class)
            def sound = new PacmanMunch(clip)
        when:
            sound.stop()
        then:
            1 * clip.stop()
    }
}
