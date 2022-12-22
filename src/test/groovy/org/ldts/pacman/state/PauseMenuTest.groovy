package org.ldts.pacman.state

import org.ldts.pacman.controllers.menus.PauseMenuController
import org.ldts.pacman.controllers.menus.RegularMenuController
import org.ldts.pacman.models.menus.MainMenu
import org.ldts.pacman.models.menus.PauseMenu
import org.ldts.pacman.states.ArenaState
import org.ldts.pacman.states.menus.PauseMenuState
import org.ldts.pacman.viewers.menus.PauseMenuViewer
import org.ldts.pacman.viewers.menus.RegularMenuViewer
import spock.lang.Specification

class PauseMenuTest extends Specification {
    def state
    def arenaState

    def setup() {
        arenaState = Mock(ArenaState.class)
        state = new PauseMenuState(new PauseMenu(arenaState))
    }

    def "We should be able to correctly get the viewer"() {
        expect:
        state.getViewer() instanceof PauseMenuViewer
        state.getViewer().getModel() instanceof PauseMenu
    }

    def "We should be able to correctly get the controller"() {
        expect:
        state.getController() instanceof PauseMenuController
        state.getController().getModel() instanceof PauseMenu
    }

}
