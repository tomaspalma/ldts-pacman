package org.ldts.pacman.models.menus

import org.ldts.pacman.Game
import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.menus.options.PlayOption
import org.ldts.pacman.models.menus.options.ResumeOption
import org.ldts.pacman.states.ArenaState
import org.ldts.pacman.states.State
import org.ldts.pacman.models.menus.options.ExitOption
import org.ldts.pacman.states.menus.RegularMenuState
import spock.lang.Specification

class OptionsTest extends Specification {
    private def game
    private def state

    def setup() {
        game = Mock(Game.class)
        state = Mock(State.class)
    }

    def "ExitOption"() {
        given:
        def option = new ExitOption("quit")

        when:
        option.select(game, state)

        then:
        1 * game.setState(null)
    }

    def "GoToMainMenuOption"() {
        given:
        def option = new ExitOption("main menu")

        when:
        option.select(game, state)

        then:
        1 * game.setState(_)
    }

    def "PlayOption"() {
        given:
        def option = new PlayOption("play")

        when:
        option.select(game, state)

        then:
        1 * game.setState(_)
    }

    def "ResumeOption"() {
        given:
        def option = new ResumeOption("resume")

        when:
        option.select(game, state)

        then:
        1 * game.setState(state)
    }
}
