package org.ldts.pacman.models.pacmananimations

import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.game.Clock
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.pacman.Pacman
import org.ldts.pacman.models.game.entities.pacman.animations.PacmanEatingAnimation
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirection
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionDown
import spock.lang.Specification

class PacmanEatingTest extends Specification {
    private def pacman
    private def arena
    private def internalClock
    private def pacAnimation

    def setup() {
        arena = Mock(Arena.class)
        pacman = new Pacman(new Position(5, 5), arena)
        internalClock = new Clock(System.currentTimeMillis())
        pacAnimation = new PacmanEatingAnimation(1000, internalClock, pacman)
    }

    def "Animation step should increase by 1000001"() {
        given:
            def previous = pacAnimation.getControl()
        when:
            pacAnimation.step()
        then:
            pacAnimation.getControl() == (previous + 100000001)
    }

    def "We should be able to use the getter for duration in milliseconds"() {
        expect:
            pacAnimation.getDurationInMilliseconds() == 1000
    }

    def "We should be able to get the internal clock of the animation"() {
        expect:
            internalClock == pacAnimation.getInternalClock()
    }

    def "Pacman eating animation should never be finished"() {
        given:
            def clockStub = Stub(Clock.class)
            def pacAnimation2 = new PacmanEatingAnimation(1000, clockStub, pacman)
            clockStub.getElapsedMilliseconds() >> 20000
        expect:
            pacAnimation2.isFinished() == false
    }

}
