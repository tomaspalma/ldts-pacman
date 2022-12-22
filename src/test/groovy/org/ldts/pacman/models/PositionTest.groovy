package org.ldts.pacman.models

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.Vector
import spock.lang.Specification

import java.awt.image.AreaAveragingScaleFilter

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
        given:
            def expected = Math.sqrt(Math.pow(4, 2) + Math.pow(3, 2))
        expect:
            pos.getDistanceTo(new Position(11, 12)) == expected
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

    def "If the list is empty, the closest position is the position itself"() {
        when:
            def result = pos.getClosestPositionFrom(new ArrayList<>())
        then:
            result == pos
    }

    def "It should be able to correctly return the closest position from a given list"() {
        given:
            def pos1 = new Position(13, 11)
            def pos2 = new Position(14, 14)
            def list = new ArrayList<>(Arrays.asList(pos1, pos2))
        expect:
            pos.getClosestPositionFrom(list) == pos2
    }

    def "It should correctly determine if its equal to itself"() {
        expect:
            pos.equals(pos) == true
    }

    def "It should correctly determine if its equal to another position"() {
        expect:
            pos.equals(new Position(pos.getX(), pos.getY())) == true
    }

    def "It should know its not equal to another position if the position is null"() {
        expect:
            pos.equals(null) == false
    }

    def "It should know that if an object is not a Position it can't be equal to pos"() {
        expect:
            pos.equals(new Vector(5, 9)) == false
    }

}
