package org.ldts.pacman.controllers

import org.ldts.pacman.Game
import org.ldts.pacman.models.menus.options.Option
import org.ldts.pacman.controllers.menus.RegularMenuController
import org.ldts.pacman.models.GameActions
import org.ldts.pacman.models.menus.Menu
import org.ldts.pacman.models.menus.options.PlayOption
import spock.lang.Specification

class MenuControllersTest extends Specification {
    private def menu
    private def game
    private long time

    def setup() {
        menu = Mock(Menu.class)
        game = Mock(Game.class)
        time = 0
    }

    def "regular step"() {
        given:
        def menuController = new RegularMenuController(menu)
        def moveDown = GameActions.ControlActions.MOVE_DOWN
        def moveUp = GameActions.ControlActions.MOVE_UP
        def exit = GameActions.ControlActions.EXIT

        when:
        menuController.step(game, moveDown, time)
        then:
        1 * menu.moveDown()

        when:
        menuController.step(game, moveUp, time)
        then:
        1 * menu.moveUp()

        when:
        menuController.step(game, exit, time)
        then:
        1 * game.setState(null)
    }
}
