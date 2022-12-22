package org.ldts.pacman.state

import org.ldts.pacman.Game
import org.ldts.pacman.controllers.ArenaController
import org.ldts.pacman.controllers.Controller
import org.ldts.pacman.gui.GUI
import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.GameActions
import org.ldts.pacman.states.ArenaState
import org.ldts.pacman.viewers.Viewer
import org.ldts.pacman.viewers.ViewerTest
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

    def "State step function should step its controller and draw viewer"() {
        expect:
            state.getRealController() instanceof ArenaController
    }

    def "The step function of step should step its controller and draw the viewer"() {
        given:
            def viewer = Mock(Viewer.class)
            def controller = Mock(Controller.class)
            def state = new ArenaState(arena, viewer, controller)
            def game = Mock(Game.class)
            def gui = Mock(GUI.class)
        when:
            state.step(game, gui, 1000)
        then:
            1 * controller.step(game, _, 1000)
            1 * viewer.draw(gui)
    }
}
