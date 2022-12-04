package org.ldts.pacman.controllers

import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.Cherry
import org.ldts.pacman.models.Pacman
import org.ldts.pacman.models.Position
import org.ldts.pacman.models.Wall
import spock.lang.Specification

class PacmanControllerTest extends Specification {
    private def arena
    private def pacman
    private def pacmanController

    def setup() {
        arena = new Arena(20, 20, "maps/easy.txt")
        pacmanController = new PacmanController(new ArenaController(arena), arena);
    }

    def "Arena should be able to detect if wall is at a certain position"() {
        given:
            pacman = new Pacman(new Position(5, 5))
            arena.setPacman(pacman)
            def wall = new Wall(new Position(5, 5))
        when:
            arena.addObstacle(wall)
        then:
            arena.isObstacleAt(new Position(5, 5)) == true
    }

    def "Arena should be able to return valid index if fixed edible is at a certain position"() {
        given:
            def fixedEdible = new Cherry(new Position(5, 5))
        when:
            arena.addToGeneralFixedEdibleList(fixedEdible)
        then:
            arena.getFixedEdibleAt(new Position(5, 5)) != -1
    }

}
