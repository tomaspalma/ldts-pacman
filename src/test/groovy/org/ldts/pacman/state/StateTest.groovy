package org.ldts.pacman.state

import org.ldts.pacman.Game
import org.ldts.pacman.gui.GUI
import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.GameActions
import org.ldts.pacman.states.ArenaState
import org.ldts.pacman.states.State
import spock.lang.Specification

class StateTest extends Specification {
    private def state
    private def arena

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt")
        state = new ArenaState(arena)
    }

    def "State step function should get next user input"() {
        given:
            def gui = Stub(GUI)
            def game = Mock(Game.class)
            gui.getNextUserInput() >> GameActions.ControlActions.MOVE_DOWN
        when:
            state.step(game, gui, 1)
        then:
            state.getUserControlAction() == GameActions.ControlActions.MOVE_DOWN

    }
}
