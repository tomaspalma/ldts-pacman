package org.ldts.pacman.models

import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirection
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionDown
import spock.lang.Specification

class PacmanTest extends Specification {
    private def arena
    private def pacman

    def setup() {
        arena = new Arena(20, 21, "maps/testmap.txt")
        pacman = arena.getPacman()
    }

    def "It should correctly close its mouth"() {
         when:
            pacman.closeMouth()
        then:
            pacman.getDrawSymbol() == "["
    }

    def "It should correctly open its mouth"() {
        given:
            def pacDir = Stub(PacmanDirectionDown)
            pacDir.getDrawSymbol() >> "A"
            pacman.setCurrentDirectionTo(pacDir)
        when:
            pacman.openMouth()
        then:
            pacman.getDrawSymbol() == "A"
    }

}
