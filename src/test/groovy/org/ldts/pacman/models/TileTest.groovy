package org.ldts.pacman.models

import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.arena.grid.RegularTile
import spock.lang.Specification

class TileTest extends Specification {
    private def arena
    private def pacman
    private def position
    private def tile

    def setup() {
        arena = new Arena(20, 21, "maps/testmap.txt")
        pacman = arena.getPacman()
        position = pacman.getPosition()
        tile = new RegularTile(position, arena)
    }

    def "A tile should be empty at start"() {
        expect:
            !tile.containsPacman()
    }

    def "We should be able to put an element inside a tile"() {
        given:
            tile.put(pacman)
        expect:
            tile.containsPacman()
    }

    def "We should be able to remove an element from a regular tile"() {
        given:
            tile.put(pacman)
        when:
            tile.removeChild(pacman)
        then:
            !tile.containsPacman()
    }

}
