package org.ldts.pacman.models

import org.ldts.pacman.Game
import org.ldts.pacman.gui.GUI
import org.ldts.pacman.states.State
import spock.lang.Specification

class GameModelTest extends Specification {
    private def game
    private def gui

    def setup() {
        gui = Mock(GUI.class)
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
}
