package org.ldts.pacman.models

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.entities.fixededibles.FixedEdible
import org.ldts.pacman.models.game.entities.fixededibles.PowerPellet
import spock.lang.Specification

class FixedEdiblesTest extends Specification  {
    private def edible
    private def arena

    def setup() {
        arena = Mock(Arena.class)
        edible = new PowerPellet(new Position(5, 5), arena)
    }

    def "We should be able to get its points"() {
        expect:
            edible.getPoints() == 50
    }

}
