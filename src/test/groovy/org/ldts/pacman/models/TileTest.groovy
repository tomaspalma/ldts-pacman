package org.ldts.pacman.models

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.arena.grid.RegularTile
import org.ldts.pacman.models.game.entities.EmptySpace
import org.ldts.pacman.models.game.entities.ghost.Pinky
import org.ldts.pacman.models.game.entities.pacman.Pacman
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

    def "We need to be able to get the position of a tile"() {
        expect:
            tile.getPosition() == position
    }

    def "We need to be able to return the arena a teletransporter tile is in"() {
        expect:
            tile.getArena() == arena
    }

    def "If the children of a tile are empty we should add an EmptySpace"() {
        given:
            tile.getChildrenEntities().clear()
            tile.put(pacman)
        when:
            tile.removeChild(pacman)
        then:
            tile.getChildrenEntities().get(0) instanceof EmptySpace
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

    def "We need to be able to correctly get the chidlren entities of a tile"() {
        given:
            def p1 = new Pacman(new Position(4, 5), arena)
            def p2 = new Pinky(new Position(6, 9), arena)
        when:
            tile.put(p1)
            tile.put(p2)
        then:
            tile.getChildrenEntities().get(0) == p1
            tile.getChildrenEntities().get(1) == p2
    }

    def "We should be able to remove an element from a regular tile"() {
        given:
            tile.put(pacman)
        when:
            tile.removeChild(pacman)
        then:
            !tile.containsPacman()
    }

    def "We need to be able to return pacman if the tile contains and we want pacman"() {
        given:
            tile.put(pacman)
        expect:
            tile.getPacman() == pacman
    }

}
