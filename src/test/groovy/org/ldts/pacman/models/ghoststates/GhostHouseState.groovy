package org.ldts.pacman.models.ghoststates

import org.ldts.pacman.models.game.GameActions
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.entities.ghost.Clyde
import spock.lang.Specification

class GhostHouseState extends Specification {
    private def state
    private def ghost
    private def arena

    def setup() {
        arena = Mock(Arena.class)
        ghost = new Clyde(new Position(5, 5), arena)
        state = new org.ldts.pacman.models.game.entities.ghost.states.DeadState(ghost)
    }

    def "It should return NONE when colliding with pacman"() {
        expect:
            state.collideWithPacmanResult() == GameActions.GhostCollisionWithPacman.NONE
    }
}
