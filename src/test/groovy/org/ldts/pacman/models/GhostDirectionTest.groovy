package org.ldts.pacman.models

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.ghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionDown
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionLeft
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionRight
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionUp
import spock.lang.Specification

class GhostDirectionTest extends Specification {
    private def arena
    private def ghostDirection

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt")
    }

    def "We should be able to detect if a ghost switched direction (orientation)"() {
       given:
            ghostDirection = new GhostDirectionUp(new Pinky(new Position(5, 5, arena)));
       when:
            def result = ghostDirection.generateNextDirectionAfterChangeTo(new Position(5, 4, arena));
       then:
            result instanceof GhostDirectionUp
       and:
            ghostDirection.generateNextDirectionAfterChangeTo(new Position(5, 6, arena)) instanceof GhostDirectionDown
       and:
            ghostDirection.generateNextDirectionAfterChangeTo(new Position(4, 5, arena)) instanceof GhostDirectionLeft
       and:
            ghostDirection.generateNextDirectionAfterChangeTo(new Position(6, 5, arena)) instanceof GhostDirectionRight
    }

    def "BUG: Ghost direction is generating positions that are not valid to move to"() {

    }
}
