package org.ldts.pacman.models

import com.googlecode.lanterna.TextColor
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.Obstacle
import org.ldts.pacman.models.game.entities.ghost.Inky
import org.ldts.pacman.models.game.entities.ghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.RegularGhost
import org.ldts.pacman.models.game.entities.ghost.states.ChasingState
import org.ldts.pacman.models.game.entities.ghost.states.DeadState
import org.ldts.pacman.models.game.entities.ghost.states.FrightenedState
import org.ldts.pacman.models.game.entities.ghost.states.GhostHouseState
import org.ldts.pacman.models.game.entities.ghost.states.ScatteringState
import org.ldts.pacman.models.game.entities.ghost.strategies.dying.GhostHouseDyingStrategy
import org.ldts.pacman.models.game.entities.ghost.strategies.frightened.FrightenedRunAwayStrategy
import spock.lang.Specification

class RegularGhostTest extends Specification {
    private def regularGhost
    private def arena

    def setup() {
        arena = new Arena(20, 21, "maps/testmap.txt")
        regularGhost = new Inky(new Position(5, 5), arena)
    }

    def "Regular ghost will switch to frightened stage after pacman eats powerpellet"() {
        when:
            regularGhost.handlePowerPelletBeingEaten()
        then:
            regularGhost.getCurrentState() instanceof FrightenedState
    }

    def "Regular ghosts should be able to change color"() {
       expect:
            regularGhost.getColor() != TextColor.ANSI.RED
       when:
            regularGhost.changeColor(TextColor.ANSI.RED)
       then:
            regularGhost.getColor() == TextColor.ANSI.RED
    }

    def "A regular ghost should be able to die"() {
        given:
            regularGhost.setCurrentStateTo(new ChasingState(regularGhost))
        when:
            regularGhost.die()
        then:
            regularGhost.getCurrentState() instanceof DeadState
    }

    def "Depending on the set of states a ghost can be in, the result of a collision with pacman will be different"() {
        when:
            regularGhost.setCurrentStateTo(new ChasingState(regularGhost))
        then:
            regularGhost.getCollisionWithPacmanResult() == GameActions.GhostCollisionWithPacman.KILL_PACMAN
        when:
            regularGhost.setCurrentStateTo(new FrightenedState(regularGhost))
        then:
            regularGhost.getCollisionWithPacmanResult() == GameActions.GhostCollisionWithPacman.KILL_GHOST
    }

    def "We need to be able to correctly get the state a ghost is at at a certain moment"() {
        given:
            regularGhost.setCurrentStateTo(new ScatteringState(regularGhost))
        expect:
            regularGhost.getCurrentState() instanceof ScatteringState
    }

    def "All regular ghosts of an arena should have the run away strategy as the primary frightened strategy"() {
        expect:
            for(RegularGhost rg: arena.getRegularGhostsList())
                rg.getFrightenedStrategy() instanceof FrightenedRunAwayStrategy
    }

    def "The dying strategies of all regular ghosts of an arena should be to go to ghost house upon being murdered"() {
        expect:
            for(RegularGhost rg: arena.getRegularGhostsList())
                rg.getDyingStrategy() instanceof GhostHouseDyingStrategy
    }

    def "We should be able to get information off the RegularGhostModel as to wether or not it is on ghost house state"() {
        given:
            regularGhost.setCurrentStateTo(new GhostHouseState(regularGhost))
        expect:
            regularGhost.isOnGhostHouseState()
    }

    def "We should be able to get the original color of a regular ghost"() {
        given:
            def inky = new Inky(new Position(1, 9), arena)
        expect:
            inky.getOriginalColor() == TextColor.ANSI.BLUE_BRIGHT
    }

    def "We need to be able to extract information on the regular ghost model about whether or not it will be at an invalid position"() {
        expect:
            regularGhost.willBeInInvalidPosition(new Position(99, 99))
    }

    def "Verify if regular ghost model says it will be at invalid position if there's an obstacle there"() {
        given:
            def newPos = new Position(7, 7)
            def tile = arena.getGameGrid().get(newPos.getY() - 1).get(newPos.getX())
            tile.put(new Obstacle(new Position(7, 7), arena))
        expect:
            regularGhost.willBeInInvalidPosition(newPos)
    }

    def "When a ghost is not on ghost house state it should say so"() {
        given:
            regularGhost.setCurrentStateTo(new ChasingState(regularGhost))
        expect:
            regularGhost.isOnGhostHouseState() == false
    }

    def "Verify if regular ghost model says it will be at invalid position if there's an obstacle there"() {
        given:
            def newPos = arena.getGhostHouse().getGatePosition()
        expect:
            regularGhost.willBeInInvalidPosition(newPos)
    }

}

