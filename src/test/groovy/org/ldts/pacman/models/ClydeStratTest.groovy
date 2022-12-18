package org.ldts.pacman.models

import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.ghost.Clyde
import org.ldts.pacman.models.game.entities.ghost.strategies.chasing.HybridIgnorantChaseStrategy
import org.ldts.pacman.models.game.entities.ghost.strategies.chasing.AgressiveChaseStrategy
import org.ldts.pacman.models.game.entities.ghost.strategies.chasing.RunToBottomLeftChaseStrategy
import org.ldts.pacman.models.game.entities.pacman.Pacman
import spock.lang.Specification

class ClydeStratTest extends Specification {
    private def pacman
    private def arena

    def setup() {
        arena = new Arena(20, 21, "maps/testmap.txt")
       // arena = Mock(Arena)
        pacman = new Pacman(new Position(6, 6), arena)
        arena.setPacman(pacman)
    }

    def "When within a distance of 8 tiles clyde should use RunToBottomLeftStrategy"() {
        given:
            def clyde = new Clyde(new Position(4, 4), arena)
            pacman.setPosition(new Position(5, 5))
        when:
            clyde.getChaseStrategy().getNextPosition(clyde)
        then:
        HybridIgnorantChaseStrategy clydeStrat = clyde.getChaseStrategy() as HybridIgnorantChaseStrategy
            clydeStrat.getCurrentChosenStrategy() instanceof RunToBottomLeftChaseStrategy
    }

    def "When the distance is more than 8 tiles, clyde should follow an aggressive chase strategy"() {
        given:
            def clyde = new Clyde(new Position(4, 4), arena)
            pacman.setPosition(new Position(19, 19))
        when:
            clyde.getChaseStrategy().getNextPosition(clyde)
        then:
            HybridIgnorantChaseStrategy clydeStrat = clyde.getChaseStrategy() as HybridIgnorantChaseStrategy
            clydeStrat.getCurrentChosenStrategy() instanceof AgressiveChaseStrategy
    }

}
