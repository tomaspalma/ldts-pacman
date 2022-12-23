package org.ldts.pacman.controllers

import org.ldts.pacman.Game
import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.GameActions
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.ghost.regularghost.Clyde
import org.ldts.pacman.models.game.entities.ghost.Ghost
import org.ldts.pacman.models.game.entities.ghost.regularghost.Inky
import org.ldts.pacman.models.game.entities.ghost.regularghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.regularghost.RegularGhost
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionLeft
import org.ldts.pacman.models.game.entities.ghost.states.ChasingState
import org.ldts.pacman.models.game.entities.ghost.states.DeadState
import org.ldts.pacman.models.game.entities.ghost.states.FrightenedState
import org.ldts.pacman.models.game.entities.ghost.states.GhostHouseState
import org.ldts.pacman.models.game.entities.ghost.states.ScatteringState
import spock.lang.Specification

class RegularGhostControllerTest extends Specification{
    private def arena
    private def arenaController
    private def regularGhostController

    def setup() {
        arena = new Arena(20, 21, "maps/testmap.txt")
        arenaController = new ArenaController(arena)
        arenaController.setAreSoundsSilenced(true)
        regularGhostController = new RegularGhostController(arenaController, arena)
    }

    def "It should be able start the apply ghost state changes if ghost is changing state on step"() {
        given:
            def ghost = new Pinky(new Position(5, 5), arena)
            ghost.setPreviousStateTo(new ScatteringState(ghost))
            def currentState = new ChasingState(ghost)
            def stateSpy= Spy(currentState)
            ghost.setCurrentStateTo(stateSpy)
            arena.getRegularGhostsList().clear()
            arena.getRegularGhostsList().add(ghost)
            def game = Mock(Game.class)
            def action = GroovyMock(GameActions.ControlActions)
        when:
            regularGhostController.step(game, action, 1000)
        then:
            1 * stateSpy.applyChangesToGhost()
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

    def "If the ghost can move outside ghost house we should be able to execute all the necessary steps to put the ghost out of the ghost house"() {
        given:
            def ghostMock = Mock(Ghost.class)
            def pos = Mock(Position.class)
            ghostMock.getPreviousState() >> new GhostHouseState(ghostMock)
            ghostMock.getCurrentState() >> new ChasingState(ghostMock)
        when:
            regularGhostController.moveGhost(ghostMock, pos)
        then:
            1 * ghostMock.setPosition(_)
            1 * ghostMock.setPreviousStateTo(_)
    }

    def "If the ghost can move outside of the ghost house we should be able to change its position"() {
        given:
            def ghost = new Clyde(new Position(5, 5), arena)
            def newPos = Mock(Position.class)
            ghost.setPreviousStateTo(new GhostHouseState(ghost))
            ghost.setCurrentStateTo(new ChasingState(ghost))
        when:
            regularGhostController.moveGhost(ghost, newPos)
        then:
            ghost.getPosition() == arena.getGhostHouse().getExitPosition()
            ghost.getPreviousState() instanceof ChasingState
    }

    def "We should get the number of steps"() {
        given:
            regularGhostController.setNumberOfSteps(10921)
        expect:
            regularGhostController.getNumberOfSteps() == 10921
    }

    def "We need to be able to see if the state changed in a ghost"() {
        given:
            def ghost = new Pinky(new Position(5,5), arena)
            ghost.setCurrentStateTo(new ChasingState(ghost))
            ghost.setCurrentStateTo(new FrightenedState(ghost))
        expect:
            regularGhostController.stateChangedIn(ghost) == true
    }

    def "We need to correctly detect if a ghost didn't change state"() {
        given:
            def ghost = new Pinky(new Position(5,5), arena)
            ghost.setCurrentStateTo(new GhostHouseState(ghost))
            ghost.setCurrentStateTo(new GhostHouseState(ghost))
        expect:
            regularGhostController.stateChangedIn(ghost) == false
    }

    def "Move ghost needs to be able to effectively make changes in both the direction and position of the ghost"() {
        given:
            def ghost = new Inky(new Position(1, 12), arena)
            ghost.setCurrentDirectionTo(new GhostDirectionLeft(ghost))
            def newPos = new Position(1, 11)
        when:
            regularGhostController.moveGhost(ghost, newPos)
        then:
            ghost.getCurrentDirection().getClass() == ghost.getCurrentDirection().generateNextDirectionAfterChangeTo(newPos).getClass()
    }

    def "If we have a dead ghost, when we call the move function it should revive them"() {
        given:
            def ghost = new Inky(new Position(1, 12), arena)
            ghost.setCurrentDirectionTo(new GhostDirectionLeft(ghost))
            ghost.setCurrentStateTo(new DeadState(ghost))
            def newPos = new Position(1, 11)
        when:
            regularGhostController.moveGhost(ghost, newPos)
        then:
            ghost.getCurrentState() instanceof ChasingState
            ghost.getPreviousState() instanceof GhostHouseState
    }

    def "When putting ghosts back in initial state it should call return to original state on regular ghost"() {
        given:
        arena.getRegularGhostsList().clear()
        def ghost = Mock(RegularGhost.class)
        arena.getRegularGhostsList().add(ghost)
        when:
        regularGhostController.putGhostsBackInInitialState()
        then:
        1 * ghost.returnToOriginalState()
    }
}
