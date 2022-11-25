package org.ldts.pacman.controllers

import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.GameActions
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

    def "We should be able to change the direction of pacman"() {
    }

}
