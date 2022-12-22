package org.ldts.pacman.models.sequences

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.arena.levels.sequences.SpecificGhostStartSequence
import org.ldts.pacman.models.game.entities.ghost.Ghost
import org.ldts.pacman.models.game.entities.ghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.states.ChasingState
import org.ldts.pacman.models.game.entities.ghost.states.FrightenedState
import spock.lang.Specification

class StartSequenceTest extends Specification {
    private def sequence
    private def ghost
    private def ghostState
    private def arena

    def setup() {
        ghost = new Pinky(new Position(5, 5), arena)
        ghostState = new ChasingState(ghost)
        sequence = new SpecificGhostStartSequence(ghost, ghostState, 1000)
    }

    def "We should be able to get the ghost of a sequence"() {
        expect:
            sequence.getGhost() == ghost
    }

    def "We should be able to get the state we are going to transition to"() {
        expect:
            sequence.getNewStateToTransitionTo() == ghostState
    }

    def "Wes should be able to get the time activated in millisecondsw"() {
        expect:
            sequence.getTimeToBeActivatedInMilliseconds() == 1000
    }

    def "When executing it should correctly change the state of the affected ghost"() {
        given:
            def ghost = Stub(Ghost.class)
            def prevState = Mock(FrightenedState.class)
            ghost.getCurrentState() >> prevState
            def newState = new ChasingState(ghost)
            def sequence = new SpecificGhostStartSequence(ghost, newState, 1000)
        when:
            sequence.execute()
        then:
            1 * prevState.transitionToState(newState)
    }

}
