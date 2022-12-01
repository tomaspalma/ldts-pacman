package org.ldts.pacman.models


import spock.lang.Specification

class ArenaTest extends Specification {
    private def arena

    def setup() {
        arena = new Arena(20, 20, "maps/easy.txt")
    }

    def "Arena should be able to detect if wall is at a certain position"() {
        given:
            def pacman = new Pacman(new Position(5, 5))
            arena.setPacman(pacman)
            def wall = new Wall(new Position(5, 5))
        when:
            arena.addWall(wall)
        then:
            arena.isWallAt(new Position(5, 5)) == true
    }

    def "Arena should be able to return valid index if fixed edible is at a certain position"() {
        given:
            def fixedEdible = new Cherry(new Position(5, 5))
        when:
            arena.addFixedEdible(fixedEdible)
        then:
            arena.getFixedEdibleAt(new Position(5, 5)) != -1
    }

}

