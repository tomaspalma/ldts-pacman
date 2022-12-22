package org.ldts.pacman.models

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.entities.ghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionDown
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionLeft
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionRight
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionUp
import spock.lang.Specification

class GhostDirectionTest extends Specification {
    private def arena
    private def ghostDirection
    private def ghost

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt")
        ghost = new Pinky(new Position(5, 5), arena)
        ghostDirection = new GhostDirectionUp(ghost);
    }

    def "We should be able to get the draw symbol of the current direction using the getter"() {
        expect:
            ghostDirection.getDrawSymbol() == ghost.getDrawSymbol()
    }

    def "We should be able to detect if a ghost switched direction (orientation)"() {
       given:
            ghostDirection = new GhostDirectionUp(new Pinky(new Position(5, 5), arena))
       when:
            def result = ghostDirection.generateNextDirectionAfterChangeTo(new Position(5, 4))
       then:
            result instanceof GhostDirectionUp
       and:
            ghostDirection.generateNextDirectionAfterChangeTo(new Position(5, 6)) instanceof GhostDirectionDown
       and:
            ghostDirection.generateNextDirectionAfterChangeTo(new Position(4, 5)) instanceof GhostDirectionLeft
       and:
            ghostDirection.generateNextDirectionAfterChangeTo(new Position(6, 5)) instanceof GhostDirectionRight
    }

    def "We need to abe able to correctly get possitions to move left"() {
        expect:
            ghostDirection.getPossiblePositionToMoveLeft() == new Position(ghost.getPosition().getX() - 1, ghost.getPosition().getY())
    }

    def "We need to abe able to correctly get possitions to move right"() {
        expect:
            ghostDirection.getPossiblePositionToMoveRight() == new Position(ghost.getPosition().getX() + 1, ghost.getPosition().getY())
    }

    def "We need to abe able to correctly get possitions to move up"() {
        expect:
            ghostDirection.getPossiblePositionToMoveUp() == new Position(ghost.getPosition().getX(), ghost.getPosition().getY() - 1)
    }

    def "We need to abe able to correctly get possitions to move down"() {
        expect:
            ghostDirection.getPossiblePositionToMoveDown() == new Position(ghost.getPosition().getX(), ghost.getPosition().getY() + 1)
    }

    def "BUG: Ghost direction is generating positions that are not valid to move to"() {

    }
}
