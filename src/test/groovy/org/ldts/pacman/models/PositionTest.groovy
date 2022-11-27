package org.ldts.pacman.models

import org.ldts.pacman.models.Position
import spock.lang.Specification

class PositionTest extends Specification {
    private def pos;

    def setup() {
        pos = new Position(15, 15);
    }

    def "We should be able to get relative positions from one"() {
        expect:
            pos.getPositionToTheLeft() == new Position(14, 15)
            pos.getPositionToTheRight() == new Position(16, 15)
            pos.getPositionAbove() == new Position(15, 14)
            pos.getPositionBelow() == new Position(15, 16)
    }
}
