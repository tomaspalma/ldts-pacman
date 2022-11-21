package org.ldts.pacman.controllers

import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.Pacman
import org.ldts.pacman.models.Position
import spock.lang.Specification

class PacmanControllerTest extends Specification {
    private def arena
    private def pacman
    private def pacmanController

    def setup() {
        arena = new Arena(10, 10)
        pacman = new Pacman(new Position(5, 5))
        arena.setPacman(pacman)
        pacmanController = new PacmanController(arena)
    }

    def "moveLeft"() {
        when:
        pacmanController.movePacmanLeft()

        then:
        arena.getPacman().getPosition() == new Position(4, 5)
    }

    def "moveRight"() {
        when:
        pacmanController.movePacmanRight()

        then:
        arena.getPacman().getPosition() == new Position(6, 5)
    }

    def "moveUp"() {
        when:
        pacmanController.movePacmanUp()

        then:
        arena.getPacman().getPosition() == new Position(5, 4)
    }

    def "moveDown"() {
        when:
        pacmanController.movePacmanDown()

        then:
        arena.getPacman().getPosition() == new Position(5, 6)
    }
}
