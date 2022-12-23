package org.ldts.pacman.models.ghoststates

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionDown
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionUp
import org.ldts.pacman.models.game.entities.ghost.regularghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.states.ChasingState
import spock.lang.Specification

class GhostStateTest extends Specification {
    def arena
    def ghost
    def state

    def setup() {
        arena = Mock(Arena.class)
        ghost = new Pinky(new Position(5, 5), arena)
        state = new ChasingState(ghost)
        ghost.setCurrentStateTo(state)
    }

    def "It should force the ghost to turn around when it transitions to another state"() {
        given:
            ghost.setCurrentDirectionTo(new GhostDirectionUp(ghost))
        when:
            state.transitionToState(new org.ldts.pacman.models.game.entities.ghost.states.ScatteringState(ghost))
        then:
            ghost.getCurrentDirection() instanceof GhostDirectionDown
    }

    def "When it transitions to a certain state it should 100% change to that state"() {
        when:
            state.transitionToState(new org.ldts.pacman.models.game.entities.ghost.states.DeadState(ghost))
        then:
            ghost.getCurrentState() instanceof org.ldts.pacman.models.game.entities.ghost.states.DeadState
            ghost.getPreviousState() instanceof ChasingState
    }

}
