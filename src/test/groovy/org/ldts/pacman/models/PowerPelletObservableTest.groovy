package org.ldts.pacman.models

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.fixededibles.PowerPellet

import spock.lang.Specification

class PowerPelletObservableTest extends Specification {
    private def powerPellet
    private def arena

    def setup() {
        arena = Mock(Arena.class)
        powerPellet = new PowerPellet(new Position(6, 9), arena)
    }

    def "it should be able to notify its observers"() {
        given:
            def observer = Mock(EatenPowerPelletObserver.class)
            powerPellet.addObserver(observer)
        when:
            powerPellet.notifyObservers()
        then:
           1 * observer.handlePowerPelletBeingEaten()
    }
}
