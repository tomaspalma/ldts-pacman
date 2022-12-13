package org.ldts.pacman.controllers

import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.game.entities.ghost.RegularGhost
import spock.lang.Specification

class RegularGhostControllerTest extends Specification {
    private def ghost
    private def regularGhostController
    private def arena
    private def arenaController

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt")
        arenaController = Mock(ArenaController)
        regularGhostController = new RegularGhostController(arenaController, arena)
        ghost = Mock(RegularGhost)
    }

    def "Controller should be able to kill the ghosts it controls"() {
        when:
           regularGhostController.killGhost(ghost)
        then:
            1 * ghost.die();
    }
}
