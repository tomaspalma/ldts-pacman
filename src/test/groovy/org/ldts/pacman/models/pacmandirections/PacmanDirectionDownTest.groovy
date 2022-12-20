package org.ldts.pacman.models.pacmandirections

import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.pacman.Pacman
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionDown
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionUp
import spock.lang.Specification

class PacmanDirectionDownTest extends Specification {
    private def arena
    private def pacPos
    private def pacman
    private def direction

    def setup() {
        arena = Mock(Arena.class)
        pacPos = Mock(Position.class)
        pacman = new Pacman(pacPos, arena)
        direction = new PacmanDirectionDown(pacman)
    }

    def "We should be able to get the correct draw symbol of pacman depending on its direction"() {
        given:
        pacman.setCurrentDirectionTo(direction)
        expect:
        pacman.getCurrentDirection().getDrawSymbol() == ">"
    }

     def "It should return the correct position in order to pacman to continue moving in this direction"() {
        given:
            pacman.setPosition(new Position(4, 9))
        expect:
            direction.getNextPosition() == new Position(4, 10)
    }
}
