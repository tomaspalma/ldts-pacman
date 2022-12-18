package org.ldts.pacman.controllers

import org.ldts.pacman.Game
import org.ldts.pacman.controllers.menus.PauseMenuController
import org.ldts.pacman.models.menus.PauseMenu
import org.ldts.pacman.controllers.menus.RegularMenuController
import org.ldts.pacman.models.GameActions
import org.ldts.pacman.models.menus.Menu
import spock.lang.Specification

class MenuControllersTest extends Specification {
    private def game
    private long time
    private def moveDown
    private def moveUp
    private def exit
    private def pause

    def setup() {
        game = Mock(Game.class)
        time = 0
        moveDown = GameActions.ControlActions.MOVE_DOWN
        moveUp = GameActions.ControlActions.MOVE_UP
        exit = GameActions.ControlActions.EXIT
        pause = GameActions.ControlActions.SWITCH_TO_PAUSE_MENU
    }

    def "regular step"() {
        given:
        def menu = Mock(Menu.class)
        def menuController = new RegularMenuController(menu)

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

    def "pause step"() {
        given:
        def pauseMenu = Mock(PauseMenu.class)
        def menuController = new PauseMenuController(pauseMenu)

        when:
        menuController.step(game, pause, time)
        then:
        1 * game.setState(pauseMenu.getArenaState())
    }
}
