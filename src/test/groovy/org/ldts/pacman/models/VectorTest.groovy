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
}
