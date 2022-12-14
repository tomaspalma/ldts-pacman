package org.ldts.pacman.models

import org.ldts.pacman.Game
import org.ldts.pacman.gui.GUI
import org.ldts.pacman.states.State
import spock.lang.Specification

class GameModelTest extends Specification {
    private def game
    private def gui

    def setup() {
    }

    def "It should call the gui close function if the game state is null"() {
        given:
            gui = Mock(GUI.class)
            def game = new Game(21, 21, gui, null)
        when:
            game.run()
        then:
            1 * gui.close()
    }

    def "We should call the step method of the current state if the state is not null"() {
        given:
            gui = Mock(GUI.class)
            def state = Mock(State.class)
            def game = new Game(21, 21, gui, state)
        when:
            game.run()
        then:
            1 * state.step(_,_,_)
    }

}
