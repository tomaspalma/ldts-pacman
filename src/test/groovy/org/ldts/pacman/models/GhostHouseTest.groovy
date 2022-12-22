package org.ldts.pacman.models

import org.ldts.pacman.models.game.arena.ghosthouse.GhostHouse
import org.ldts.pacman.models.game.Position
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

    def "We neeed to be able to get what's the exit position of the ghost house"() {
        given:
            def pos = ghostHouse.getGatePosition()
        expect:
            ghostHouse.getExitPosition() == new Position(pos.getX(), pos.getY() - 1)
    }

    def "We should be able to correctly get an available position from inside the ghost house"() {
        given:
            def pos = ghostHouse.getUpperLeftPosition()
            ghostHouse.getGhostHolder().clear();
        expect:
            ghostHouse.getAvailablePosition() == new Position(pos.getX() + 1, pos.getY() + 1)
    }

}
