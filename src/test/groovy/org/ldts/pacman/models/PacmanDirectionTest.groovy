package org.ldts.pacman.models

import spock.lang.Specification

class PacmanDirectionTest extends Specification {
    private def direction
    private def pacman
    private def pacmanX
    private def pacmanY
    private def arena

    def setup() {
        arena = new Arena(20, 21, "maps/testmap.txt");
        pacman = arena.getPacman()
        pacmanX = pacman.getPosition().getX()
        pacmanY = pacman.getPosition().getY()
    }

    def "We should be able to get correct forward position from down direction"() {
        given:
            def direction = new PacmanDirectionDown(pacman)
        expect:
            direction.getGhostTargetTileWithForwardLevel(2) == new Position(pacmanX, pacmanY + 2, arena)
    }

    def "We should be able to get correct forward position from left direction"() {
        given:
            def direction = new PacmanDirectionLeft(pacman)
        expect:
            direction.getGhostTargetTileWithForwardLevel(3) == new Position(pacmanX - 3, pacmanY , arena)
    }
    def "We should be able to get correct forward position from right direction"() {
        given:
            def direction = new PacmanDirectionRight(pacman)
        expect:
            direction.getGhostTargetTileWithForwardLevel(5) == new Position(pacmanX + 5, pacmanY, arena)
    }
    def "We should be able to get correct forward position from up direction"() {
        given:
            def direction = new PacmanDirectionUp(pacman)
        expect:
            direction.getGhostTargetTileWithForwardLevel(2) == new Position(pacmanX - 2, pacmanY - 2, arena)
    }
}
