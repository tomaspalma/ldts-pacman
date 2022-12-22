package org.ldts.pacman.state

import org.ldts.pacman.controllers.menus.RegularMenuController
import org.ldts.pacman.models.menus.MainMenu
import org.ldts.pacman.states.menus.RegularMenuState
import org.ldts.pacman.viewers.menus.RegularMenuViewer
import spock.lang.Specification

class RegularMenuStateTest extends Specification {
    private def state

    def setup() {
        state = new RegularMenuState(new MainMenu())
    }

    def "We should be able to correctly get the viewer"() {
        expect:
            state.getViewer() instanceof RegularMenuViewer
            state.getViewer().getModel() instanceof MainMenu
    }

    def "We should be able to correctly get the controller"() {
        expect:
        state.getController() instanceof RegularMenuController
        state.getController().getModel() instanceof MainMenu
    }

}
