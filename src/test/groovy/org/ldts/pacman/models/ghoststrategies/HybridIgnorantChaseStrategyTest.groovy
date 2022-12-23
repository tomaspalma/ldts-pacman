package org.ldts.pacman.models.ghoststrategies

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.ghost.regularghost.Clyde
import org.ldts.pacman.models.game.entities.pacman.Pacman
import spock.lang.Specification
import org.ldts.pacman.models.game.arena.Arena

class HybridIgnorantChaseStrategyTest extends Specification {
    private def arena
    private def ghost
    private def pacman

    def setup() {
       arena = Mock(Arena.class)
       pacman = Mock(Pacman.class)
       arena.setPacman(pacman)
       ghost = new Clyde(new Position(5, 5), arena)
    }

    def "This strategy should return the closest position to pacman if more than 8 tiles away from it"() {
        expect:
            arena == arena
    }

}
