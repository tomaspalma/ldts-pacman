package org.ldts.pacman.models

import org.ldts.pacman.models.Position
import spock.lang.Shared
import spock.lang.Specification

class PositionTest extends Specification {
    private def arena
    private def pos

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt");
        pos = new Position(15, 15);
    }

    def "We should be able to get relative positions from one"() {
        expect:
            pos.getPositionToTheLeft() == new Position(14, 15)
            pos.getPositionToTheRight() == new Position(16, 15)
            pos.getPositionAbove() == new Position(15, 14)
            pos.getPositionBelow() == new Position(15, 16)
    }

    def "We should be able to correctly detect if a position is out of bounds"() {
        expect:
            new Position(22, 22).isOutOfBounds() == true
            new Position(19, 19).isOutOfBounds() == false
            new Position(40, 10).isOutOfBounds() == true
            new Position(10, 40).isOutOfBounds() == true
            new Position(-1, 5).isOutOfBounds() == true
    }

    def "We should be able to detect if we are on a ghost position"() {
        expect:
            new Position(7, 5).isOnSomeGhostPosition() == true
            new Position(8, 5).isOnSomeGhostPosition() == false
    }

    def "We should be able to detect if we are on an obstacle position"() {
        expect:
            new Position(0, 1).isOnSomeObstaclePosition()
            new Position(7, 18).isOnSomeObstaclePosition()
    }

    def cleanup() {
        Arena.gameGrid.clear()
    }
}
