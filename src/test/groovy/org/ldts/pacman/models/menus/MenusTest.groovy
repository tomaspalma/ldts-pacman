package org.ldts.pacman.models.menus

import org.codehaus.groovy.transform.tailrec.GotoRecurHereException
import org.ldts.pacman.Game
import org.ldts.pacman.models.menus.options.ExitOption
import org.ldts.pacman.models.menus.options.GoToMainMenuOption
import org.ldts.pacman.models.menus.options.PlayOption
import org.ldts.pacman.models.menus.options.ResumeOption
import org.ldts.pacman.states.ArenaState
import spock.lang.Specification

class MenusTest extends Specification {
    def "MainMenu construction"() {
        when:
        def mainMenu = new MainMenu()

        then:
        mainMenu.getNumberOptions() == 2
        mainMenu.getCurrentNumber() == 0
        mainMenu.getOption(0) instanceof PlayOption
        mainMenu.getOption(1) instanceof ExitOption
    }

    def "GameOverMenu loss construction"() {
        when:
        def gameOverMenu = new GameOverMenu("loss")

        then:
        gameOverMenu.getNumberOptions() == 3
        gameOverMenu.getCurrentNumber() == 0
        gameOverMenu.getOption(0) instanceof PlayOption
        gameOverMenu.getOption(1) instanceof GoToMainMenuOption
        gameOverMenu.getOption(2) instanceof ExitOption
    }

    def "PauseMenu construction"() {
        given:
        def arenaState = Mock(ArenaState.class)

        when:
        def pauseMenu = new PauseMenu(arenaState)

        then:
        pauseMenu.getNumberOptions() == 3
        pauseMenu.getCurrentNumber() == 0
        pauseMenu.getOption(0) instanceof ResumeOption
        pauseMenu.getOption(1) instanceof GoToMainMenuOption
        pauseMenu.getOption(2) instanceof ExitOption
    }

    def "Menu option selection"() {
        given:
        def menu = new GameOverMenu("loss")
        menu.currentOption = 0

        when:
        menu.moveUp()
        then:
        menu.getCurrentNumber() == menu.getNumberOptions() - 1

        when:
        menu.currentOption = 0
        menu.moveDown()
        then:
        menu.getCurrentNumber() == 1 % menu.getNumberOptions()
    }
}
