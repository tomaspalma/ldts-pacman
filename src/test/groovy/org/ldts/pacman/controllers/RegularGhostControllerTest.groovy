package org.ldts.pacman.controllers

import org.ldts.pacman.Game
import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.GameActions
import org.ldts.pacman.models.PositionTest
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.ghost.Ghost
import org.ldts.pacman.models.game.entities.ghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.RegularGhost
import org.ldts.pacman.models.game.entities.ghost.states.ChasingState
import org.ldts.pacman.models.game.entities.ghost.states.DeadState
import org.ldts.pacman.models.game.entities.ghost.states.GhostHouseState
import org.ldts.pacman.models.game.entities.ghost.states.ScatteringState
import spock.lang.Specification

class RegularGhostControllerTest extends Specification{
    private def arena
    private def arenaController
    private def regularGhostController

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt")
        arenaController = new ArenaController(arena)
        regularGhostController = new RegularGhostController(arenaController, arena)
    }

    def "It should be able start the apply ghost state changes if ghost is changing state on step"() {
        given:
            def ghost = new Pinky(new Position(5, 5), arena)
            ghost.setPreviousStateTo(new ScatteringState(ghost))
            def stateMock = Mock(ChasingState.class)
            ghost.setCurrentStateTo(stateMock)
            arena.getRegularGhostsList().clear()
            arena.getRegularGhostsList().add(ghost)
            def game = Mock(Game.class)
            def action = GroovyMock(GameActions.ControlActions)
        when:
            regularGhostController.step(game, action, 1000)
        then:
            1 * stateMock.applyChangesToGhost()
    }

    def "If the number of steps is greater than 1 it should reset numberofsteps"() {
        given:
            regularGhostController.setNumberOfSteps(5)
            def game = Mock(Game.class)
            def action = GroovyMock(GameActions.ControlActions)
        when:
            regularGhostController.step(game, action, 1000)
        then:
            regularGhostController.getNumberOfSteps() == 0
    }

    def "It should be able to start the killing process of a ghost"() {
        given:
            def ghost = Mock(Ghost.class)
        when:
            regularGhostController.killGhost(ghost)
        then:
            1 * ghost.die()
    }

    def "It should be able to fully revive a ghost"() {
        given:
            def ghost = new Pinky(new Position(5, 5), arena)
            ghost.setCurrentStateTo(new DeadState(ghost))
        when:
            regularGhostController.reviveDeadGhost(ghost)
        then:
            ghost.getCurrentState() instanceof ChasingState
            ghost.getPreviousState() instanceof GhostHouseState
    }

    def "It should be able to detect if a ghost has changed state from the start state"() {
        given:
            def ghost = new Pinky(new Position(5, 5), arena)
            ghost.setCurrentStateTo(new DeadState(ghost))
        expect:
            regularGhostController.stateChangedIn(ghost)
    }

    def "We should be able to put the regular ghosts back in their initial state"() {
        given:
            arenaController.getModel().getRegularGhostsList().clear()
            def ghostMock = Mock(RegularGhost.class)
            def ghostReal = new Pinky(new Position(10, 10), arena)
            arenaController.getModel().getRegularGhostsList().add(ghostMock)
            arenaController.getModel().getRegularGhostsList().add(ghostReal)
        when:
            regularGhostController.putGhostsBackInInitialState()
        then:
            1 * ghostMock.switchTile(_)
            1 * ghostMock.setPosition(_)
            1 * ghostMock.setCurrentStateTo(_)
            1 * ghostMock.setPreviousStateTo(_)
            ghostReal.getPosition() == ghostReal.getStartPosition()
            ghostReal.getCurrentState() == ghostReal.getOriginalState()
            ghostReal.getPreviousState() == ghostReal.getOriginalState()
    }

    def "We should be able start a call to arena controller to process pacman life if collides with pacman"() {
        given:
            def acMock = Mock(ArenaController.class)
            def newRGController = new RegularGhostController(acMock, arena)
            def ghost = Mock(RegularGhost.class)
        when:
            newRGController.checkCollisionWithPacman(ghost, arena.getPacman().getPosition())
        then:
            1 * acMock.processPacmanLoseLife()
    }

}
