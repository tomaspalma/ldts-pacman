package org.ldts.pacman.models.sequences

import org.ldts.pacman.models.game.arena.levels.sequences.GhostDuringStateSequence
import org.ldts.pacman.models.game.entities.ghost.regularghost.RegularGhost
import org.ldts.pacman.models.game.entities.ghost.states.ChasingState
import org.ldts.pacman.models.game.entities.ghost.states.GhostState
import spock.lang.Specification

class GhostDuringSequenceTest extends Specification {
    def state = ChasingState.class
    def sequence

    def setup() {
        sequence = new GhostDuringStateSequence(state, 1000)
    }

    def "We need to be able to get the time to be activated"() {
        expect:
            sequence.getTimeToBeActivatedInMilliseconds() == 1000
    }

    def "We should be able to get this sequence ghost state"() {
        expect:
            sequence.getGhostState() == state
    }

    def "When executing it shoudl transition the state of all ghosts that it has to"() {
        given:
            def ghost1 = Stub(RegularGhost.class)
            def state1 = Mock(GhostState.class)
            ghost1.getCurrentState() >> state1
            def ghost2= Stub(RegularGhost.class)
            def state2 = Mock(GhostState.class)
            ghost2.getCurrentState() >> state2
            def list = new ArrayList<>(Arrays.asList(ghost1, ghost2))
        when:
            sequence.execute(list)
        then:
            1 * state1.transitionToState(_)
            1 * state2.transitionToState(_)
    }

}
