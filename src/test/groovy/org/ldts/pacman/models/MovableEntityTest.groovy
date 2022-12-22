package org.ldts.pacman.models

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.arena.grid.RegularTile
import org.ldts.pacman.models.game.arena.grid.TeletransporterTile
import org.ldts.pacman.models.game.entities.pacman.Pacman
import spock.lang.Specification

class MovableEntityTest extends Specification {
    private def arena
    private def movableEntity

    def setup() {
        arena = new Arena(20, 21, "maps/testmap.txt")
        movableEntity = new Pacman(new Position(0, 1), arena)
        arena.setPacman(movableEntity)
    }

    def "We should be able to switch tiles to another in a regular tile"() {
        given:
            def tile1 = new RegularTile(new Position(0, 1), arena)
            def tile2 = new RegularTile(new Position(1, 1), arena)
            def gameGrid = [[tile1, tile2]]
            arena.getGameGrid() >> gameGrid
        when:
            def result = movableEntity.switchTile(new Position(1, 1))
        then:
            result == new Position(1, 1)
    }

    def "A movable entity should be able to switch tiles in the game grid"() {
        given:
            def tile1 = arena.getGameGrid().get(0).get(0);
            def tile2 = arena.getGameGrid().get(0).get(1);
            tile1.put(movableEntity)
        when:
            movableEntity.switchTile(new Position(1, 1))
        then:
            1 == 1
            tile1.containsPacman() == false
            tile2.containsPacman() == true
    }
}
