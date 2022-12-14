package org.ldts.pacman.state

import org.ldts.pacman.Game
import org.ldts.pacman.gui.GUI
import org.ldts.pacman.models.GameActions
import org.ldts.pacman.states.State
import spock.lang.Specification

class StateTest extends Specification {
    private state

    def setup() {
        state = Spy(State.class)
    }

    /*def "State step function should get next user input"() {
        given:
            def gui = Stub(GUI)
            def game = Mock(Game.class)
            gui.getNextUserInput() >> GameActions.ControlActions.MOVE_DOWN
        when:
            state.step(game, gui, 1)
        then:
            state.getUserControlAction() == GameActions.ControlActions.MOVE_DOWN
    } */
}
