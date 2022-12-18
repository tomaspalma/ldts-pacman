package org.ldts.pacman.models

import org.ldts.pacman.models.game.Clock
import spock.lang.Specification

class ClockTest extends Specification {
    private def clock
    private def startTime

    def setup() {
        startTime = System.currentTimeMillis()
        clock = new Clock(startTime)
    }

    def "Initializes elapsedMilliseconds as zero"() {
        expect:
            clock.getElapsedMilliseconds() == 0
    }

    def "We should be able to detect whether the clock is paused or not"() {
        expect:
            clock.isPaused() == false
        when:
            clock.pause()
        then:
            clock.isPaused() == true
        when:
            clock.unpause()
        then:
            clock.isPaused() == false
    }

    /*def "Clock should increase ellapsed milliseconds and should be able to reset"() {
        given:
            def previousElapsed = clock.getElapsedMilliseconds()
        when:
            clock.step()
        then:
            previousElapsed < clock.getElapsedMilliseconds()
        when:
            clock.reset()
        then:
            clock.getElapsedMilliseconds() == 0
    }*/

}
