package org.ldts.pacman.models.menus

import spock.lang.Specification

class OptionsTest extends Specification {
    private def game
    private def state

    def setup() {
        game = Mock(Game.class)
        state = Mock(State.class)
    }
}
