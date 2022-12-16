package org.ldts.pacman.controllers

import org.ldts.pacman.Game
import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.GameActions
import org.ldts.pacman.models.game.entities.pacman.Pacman
import org.ldts.pacman.models.game.entities.pacman.animations.PacmanAnimation
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionDown
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionLeft
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionRight
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionUp
import spock.lang.Specification

class PacmanControllerTest extends Specification {
    private def pacController
    private def arenaController
    private def arena
    private def pacman

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt")
        arenaController = new ArenaController(arena)
        pacController = new PacmanController(arenaController, arena)
        pacman = pacController.getModel().getPacman()
    }

    /*def "Step should execute the move pacman function as well as the animation executions"() {
        given:
            def game = Mock(Game.class)
            def action = GroovyMock(GameActions.ControlActions)
        when:
            pacController.step(game, action, 1000)
        then:
            1 * pacController.movePacmanAccordingToAction(action)
    }*/

    def "When moving pacman depending on the right action it should set the controller wanted direction to the correct one"() {
        when:
            pacController.movePacmanAccordingTo(GameActions.ControlActions.MOVE_LEFT)
        then:
            pacController.getWantedDirection().getClass() == (new PacmanDirectionLeft(pacman)).getClass()
        when:
            pacController.movePacmanAccordingTo(GameActions.ControlActions.MOVE_RIGHT)
        then:
            pacController.getWantedDirection().getClass() == (new PacmanDirectionRight(pacman)).getClass()
        when:
            pacController.movePacmanAccordingTo(GameActions.ControlActions.MOVE_UP)
        then:
            pacController.getWantedDirection().getClass() == (new PacmanDirectionUp(pacman)).getClass()
        when:
            pacController.movePacmanAccordingTo(GameActions.ControlActions.MOVE_DOWN)
        then:
            pacController.getWantedDirection().getClass() == (new PacmanDirectionDown(pacman)).getClass()

    }

    def "We should be able to change the number of lives our pacman has"() {
        expect:
            pacman.getRemainingLives() == 3
        when:
            pacController.changeLife(9)
        then:
            pacman.getRemainingLives() == 9
    }

    def "We should execute the step of our animations"() {
        given:
            def animationMock = Mock(PacmanAnimation.class)
            pacman.getAnimationsToExecute().clear()
            pacman.getAnimationsToExecute().add(animationMock)
        when:
            pacController.executeAnimations()
        then:
            1 * animationMock.step()
        when:
            animationMock.isFinished() >> true
            pacController.executeAnimations()
        then:
            pacman.getAnimationsToExecute().isEmpty()
    }

}
