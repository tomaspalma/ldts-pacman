package org.ldts.pacman.models

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.Vector
import spock.lang.Specification

class PositionTest extends Specification {
    private def pos

    def setup() {
        pos = new Position(15, 15)
    }

    def "We should be able to get relative positions from one"() {
        expect:
            pos.getPositionToTheLeft() == new Position(14, 15)
            pos.getPositionToTheRight() == new Position(16, 15)
            pos.getPositionAbove() == new Position(15, 14)
            pos.getPositionBelow() == new Position(15, 16)
    }

    def "We should be able to get a vector between two points in the direction of point2 to point1"() {
        given:
            def position1 = new Position(4 , 4)
            def position2 = new Position(12, 19)
        expect:
            position1.getVectorTo(position2).equals(new Vector(8, 15))
    }

    def "We should correctly receive the distance between two positions"() {

    }

    def "We should be able to get the closest position to a certain position"() {
        given:
            def position1 = new Position(5, 5)
    }

    def "We should be able to change position attributes"() {
        when:
            pos.setXTo(4)
        then:
            pos == new Position(4, 15)
        when:
            pos.setYTo(5)
        then:
            pos == new Position(4, 5)
    }

    def "We should have an hash code function working correctly"() {
        expect:
            pos.hashCode() == Objects.hash(pos.getX(), pos.getY())
    }

}
