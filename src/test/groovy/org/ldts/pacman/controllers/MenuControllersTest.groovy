package org.ldts.pacman.controllers

import org.ldts.pacman.Game
import org.ldts.pacman.controllers.menus.PauseMenuController
import org.ldts.pacman.models.menus.GameOverMenu
import org.ldts.pacman.models.menus.PauseMenu
import org.ldts.pacman.controllers.menus.RegularMenuController
import org.ldts.pacman.models.game.GameActions
import org.ldts.pacman.models.menus.Menu
import org.ldts.pacman.models.menus.options.MenuOption
import org.ldts.pacman.sounds.IntroSound
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
        menuController.setAreSoundsSilenced(true)

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
        menuController.setAreSoundsSilenced(true)

        when:
        menuController.step(game, pause, time)
        then:
        2 * pauseMenu.getArenaState()
        1 * game.setState(pauseMenu.getArenaState())
    }

    def "It should be able to mode down a menu"() {
        given:
            def pauseMenu = Mock(PauseMenu.class)
            def pauseController = new PauseMenuController(pauseMenu)
            def action = GameActions.ControlActions.MOVE_DOWN
        when:
            pauseController.step(game, action, 1000)
        then:
            1 * pauseMenu.moveDown()
    }

    def "A controller should be able to mode up a menu"() {
        given:
        def pauseMenu = Mock(PauseMenu.class)
        def pauseController = new PauseMenuController(pauseMenu)
        def action = GameActions.ControlActions.MOVE_UP
        when:
        pauseController.step(game, action, 1000)
        then:
        1 * pauseMenu.moveUp()
    }

    def "It should be able to leave the game"() {
        given:
        def pauseMenu = Mock(PauseMenu.class)
        def pauseController = new PauseMenuController(pauseMenu)
        def game = Mock(Game.class)
        def action = GameActions.ControlActions.EXIT
        when:
        pauseController.step(game, action, 1000)
        then:
        1 * game.setState(null)
    }

    def "The regular menu controller should be able to stop music when exit"() {
        given:
            def pauseMenu = Mock(PauseMenu.class)
            def regularController = new RegularMenuController(pauseMenu)
            def game = Mock(Game.class)
            def action = GameActions.ControlActions.EXIT
            def music = Mock(IntroSound.class)
            regularController.setMusic(music)
        when:
            regularController.step(game, action, 1000)
        then:
            1 * music.stop()
    }

    def "The regular menu controller should be able to stop music when selecting option"() {
        given:
        def regularMenu = new GameOverMenu("loss")
        def regularController = new RegularMenuController(regularMenu)
        def game = Mock(Game.class)
        def action = GameActions.ControlActions.SELECT
        def music = Mock(IntroSound.class)
        regularController.setMusic(music)
        when:
        regularController.step(game, action, 1000)
        then:
        1 * music.stop()
    }
}
