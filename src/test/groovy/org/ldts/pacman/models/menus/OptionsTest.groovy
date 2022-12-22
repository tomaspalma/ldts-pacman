package org.ldts.pacman.models.menus

import org.ldts.pacman.Game
import org.ldts.pacman.models.menus.options.GoToMainMenuOption
import org.ldts.pacman.models.menus.options.PlayOption
import org.ldts.pacman.models.menus.options.ResumeOption
import org.ldts.pacman.states.State
import org.ldts.pacman.models.menus.options.ExitOption
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

        when:
            def message = option.getMessage()
        then:
            message == "quit"
    }

    /*def "GoToMainMenuOption"() {
        given:
        def option = new GoToMainMenuOption("main menu")

        when:
            option.select(game, state)
        then:
        1 * game.setState(_)

        when:
        def message = option.getMessage()
        then:
        message == "main menu"
    }*/

    def "PlayOption"() {
        given:
            def option = new PlayOption("play")

        when:
            option.select(game, state)
        then:
            1 * game.setState(_)

        when:
            def message = option.getMessage()
        then:
            message == "play"
    }

    def "ResumeOption"() {
        given:
            def option = new ResumeOption("resume")

        when:
            option.select(game, state)
        then:
            1 * game.setState(state)

        when:
            def message = option.getMessage()
        then:
            message == "resume"
    }
}
