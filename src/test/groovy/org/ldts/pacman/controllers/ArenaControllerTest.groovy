package org.ldts.pacman.controllers

import org.ldts.pacman.Game
import org.ldts.pacman.gui.GUI
import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.GameActions
import org.ldts.pacman.models.game.entities.fixededibles.Cherry
import org.ldts.pacman.models.game.entities.ghost.RegularGhost
import org.ldts.pacman.models.game.entities.pacman.Pacman
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.obstacles.Wall
import org.ldts.pacman.controllers.ArenaController
import org.ldts.pacman.models.menus.GameOverMenu
import org.ldts.pacman.states.menus.RegularMenuState
import spock.lang.Specification

class ArenaControllerTest extends Specification {
    private def arena
    private def pacman
    private def arenaController
    private def pacController
    private def regularGhostController

    def setup() {
        arena = new Arena(20, 20, "maps/easy.txt")
        pacController= Mock(PacmanController.class)
        regularGhostController = Mock(RegularGhostController.class)
        arenaController = new ArenaController(arena)
        arenaController.setPacmanController(pacController)
        arenaController.setRegularGhostController(regularGhostController)
    }

    def "We should be able to detect if wall is at a certain position"() {
        given:
            pacman = new Pacman(new Position(5, 5), arena)
            arena.setPacman(pacman)
            def wall = new Wall(new Position(5, 5), arena)
        when:
            arena.addObstacle(wall)
        then:
            arena.isObstacleAt(new Position(5, 5))
    }

    def "Arena should be able to return valid index if fixed edible is at a certain position"() {
        given:
            def fixedEdible = new Cherry(new Position(5, 5), arena)
        when:
            arena.addToGeneralFixedEdibleList(fixedEdible)
        then:
            arena.getFixedEdibleAt(new Position(5, 5)) != -1
    }

    def "Arena controller should be initialized with pacman and regular ghsot controller"() {
        given:
            arenaController.setPacmanController(new PacmanController(arenaController, arena))
            arenaController.setRegularGhostController(new RegularGhostController(arenaController, arena))
        expect:
            arenaController.getPacmanController().getClass() == PacmanController.class
            arenaController.getRegularGhostController().getClass() == RegularGhostController.class
    }

    def "We should restore the fixed edibles list the next level if the current leven has ended and do nothting if it hasn't ended yet"() {
        given:
            arenaController.getModel().getGeneralFixedEdibleList().clear();
        when:
            arenaController.actIfLevelEnded()
        then:
            arenaController.getModel().getGeneralFixedEdibleList().size() != 0
        when:
            arenaController.actIfLevelEnded()
        then:
            arenaController.getModel().getGeneralFixedEdibleList().size() > 0
    }

    def "When stepping this controller we should call the steps of our children controllers"() {
        given:
            def game = Mock(Game.class)
            def action = GroovyMock(GameActions.ControlActions.class)
            def pacControllerMock = Mock(PacmanController.class)
            def regularGhostControllerMock = Mock(RegularGhostController.class)
            arenaController.setPacmanController(pacControllerMock)
            arenaController.setRegularGhostController(regularGhostControllerMock)
        when:
            arenaController.step(game,action ,1000)
        then:
            1 * pacControllerMock.step(game, action, 1000)
            1 * regularGhostControllerMock.step(game, action, 1000)
    }

    def "It should correctly put the level back to start position"() {
        when:
            arenaController.putCurrentLevelBackToStartPositions()
        then:
            1 * regularGhostController.putGhostsBackInInitialState()
            arenaController.getModel().getPacman().getPosition() ==
                arenaController.getModel().getPacman().getStartPosition()
            /*for(RegularGhost ghost: arenaController.getModel().getRegularGhostsList()) {
                if(ghost.getPosition() == arenaController.getModel().getGhostHouse().getExitPosition()
                || ghost.getPosition() )
            }*/
    }

    def "It should show a game over menu when the player loses"() {
        given:
            arenaController.getModel().getPacman().setLivesTo(0)
            def gui = Mock(GUI.class)
            def action = GroovyMock(GameActions.ControlActions.class)
            def game = new Game(21, 21, gui, null)
        when:
            arenaController.step(game, action, 1000)
        then:
            game.getState().getClass() == (new RegularMenuState(new GameOverMenu("loss"))).getClass()
    }


}
