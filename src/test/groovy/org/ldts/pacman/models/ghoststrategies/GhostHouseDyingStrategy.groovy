package org.ldts.pacman.models.ghoststrategies

import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.arena.ghosthouse.GhostHouse
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.ghost.Pinky
import spock.lang.Specification

class GhostHouseDyingStrategy extends Specification {
    private def ghostHouseDyingStrategy

    def setup() {
        ghostHouseDyingStrategy = new org.ldts.pacman.models.game.entities.ghost.strategies.dying.GhostHouseDyingStrategy()
    }

    def "It should return the position of our ghost house"() {
       given:
           def arena = Mock(Arena.class)
           def ghostHouse = new GhostHouse(new Position(5, 5), 5, 7)
           def ghost = new Pinky(new Position(10, 12), arena)
           arena.getGhostHouse() >> ghostHouse
       expect:
            ghostHouse.getAvailablePosition() == ghostHouseDyingStrategy.getNextPosition(ghost)
    }
}
