package org.ldts.pacman.models

import org.ldts.pacman.models.game.GhostHouse
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.ghost.states.GhostHouseState
import spock.lang.Specification

class GhostHouseTest extends Specification {
    private def ghostHouse

    def setup() {
        ghostHouse = new GhostHouse(new Position(4, 4), 20, 21)
    }

    def "We need to be able to get the width of the ghost house"() {
        expect:
            ghostHouse.getWidth() == 20
    }

    def "We need to be able to get the height of the ghost house"() {
        expect:
            ghostHouse.getHeight() == 21
    }

}