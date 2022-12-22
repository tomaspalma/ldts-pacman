package org.ldts.pacman.models

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.Vector
import spock.lang.Specification

class VectorTest extends Specification {
    private def vector

    def setup() {
        vector = new Vector(3, 9)
    }

    def "We should be able to sum this with a position"() {
        when:
            def position = new Position(5, 10)
        then:
            vector.getPositionBasedOnSumWith(position) == new Position(8, 19)
    }

    def "We should be able to compare two equal vectors"() {
        expect:
            vector.equals(new Vector(3, 9))
            new Vector(1, 4).equals(new Vector(1, 4))
            new Vector(5, 1).equals(new Vector(5, 1))
            new Vector(2, 3).equals(new Vector(2, 3))
    }

    def "We should have an hash code function working correctly"() {
        expect:
            vector.hashCode() == Objects.hash(vector.getX(), vector.getY())
    }

    def "It should correctly determine if its equal to itself"() {
        expect:
            vector.equals(vector) == true
    }

    def "It should correctly determine if its equal to another position"() {
        expect:
            vector.equals(new Vector(vector.getX(), vector.getY())) == true
    }

    def "It should know its not equal to another position if the position is null"() {
        expect:
            vector.equals(null) == false
    }

    def "It should know that if an object is not a Position it can't be equal to pos"() {
        expect:
            vector.equals(new Vector(5, 9)) == false
    }

}
