package org.ldts.pacman.models

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.arena.grid.TeletransporterTile
import org.ldts.pacman.models.game.entities.MovableEntity
import org.ldts.pacman.models.game.entities.obstacles.Wall
import org.ldts.pacman.models.game.entities.pacman.Pacman
import spock.lang.Specification

class TeletransporterTileTest extends Specification {
    private def arena
    private def tile

    def setup() {
        arena = Mock(Arena.class)
        tile = new TeletransporterTile(new Position(4, 9), arena)
    }

    def "We should be correctly be able to get its exit tile"() {
        given:
            def tile2 = new TeletransporterTile(new Position(1, 9), arena)
            tile.setExitTile(tile2)
        expect:
            tile.getExitTile() == tile2
    }

    def "When putting something in a tile that is not a movable entity it should throw an exception"() {
        when:
            tile.put(new Wall(new Position(5, 9), arena))
        then:
            thrown(IllegalArgumentException)
    }

    def "We should be able to effectively teletransport an entity to the exit tile"() {
        given:
            tile.setExitTile(new TeletransporterTile(new Position(1, 1), arena))
            def position = new Position(9, 10)
            def entity = Stub(MovableEntity.class)
            entity.getPosition() >> position
        when:
            tile.teletransportToExitTile(entity)
        then:
            position == new Position(1, 1)
    }

    def "When putting an entity into a tile it needs to teletransport it to the exit tile"() {
        given:
            def exittile = new TeletransporterTile(new Position(1, 1), arena)
            tile.setExitTile(exittile)
            def pacman = new Pacman(new Position(4, 4), arena)
        when:
            tile.put(pacman)
        then:
            pacman.getPosition() == exittile.getPosition()
    }

}
