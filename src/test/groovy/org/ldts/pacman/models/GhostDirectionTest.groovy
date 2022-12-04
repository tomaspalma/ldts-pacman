package org.ldts.pacman.models

import spock.lang.Specification

class GhostDirectionTest extends Specification {
    private def arena
    private def ghostDirection

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt")
    }

    def "We should be able to detect if a ghost switched direction (orientation)"() {
       given:
            ghostDirection = new GhostDirectionUp(new Pinky(new Position(5, 5)));
       when:
            def result = ghostDirection.generateNextDirectionAfterChangeTo(new Position(5, 4));
       then:
            result instanceof GhostDirectionUp
       and:
            ghostDirection.generateNextDirectionAfterChangeTo(new Position(5, 6)) instanceof GhostDirectionDown
       and:
            ghostDirection.generateNextDirectionAfterChangeTo(new Position(4, 5)) instanceof GhostDirectionLeft
       and:
            ghostDirection.generateNextDirectionAfterChangeTo(new Position(6, 5)) instanceof GhostDirectionRight
    }

    def "We should be able to detect if a theoretically created position is invalid "() {

    }
}
