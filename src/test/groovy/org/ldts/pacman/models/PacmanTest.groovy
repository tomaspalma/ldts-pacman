package org.ldts.pacman.models

import com.googlecode.lanterna.TextColor
import org.ldts.pacman.models.game.entities.pacman.Pacman
import org.ldts.pacman.models.game.entities.pacman.animations.PacmanEatingAnimation
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionRight
import spock.lang.Specification

class PacmanTest extends Specification {
    private def arena
    private def pacman

    def setup() {
        arena = new Arena(20, 21, "maps/testmap.txt")
        pacman = arena.getPacman();
    }

    def "Pacman should have color yellow after being initialized"() {
        expect:
            pacman.getColor() == TextColor.ANSI.YELLOW_BRIGHT
    }

    def "Pacman should have the draw symbol of the right direction on being initialized"() {
        expect:
            pacman.getCurrentDirection().getClass() == PacmanDirectionRight
    }

    def "Pacman should be initialized with the default pacman easting animation"() {
        expect:
            pacman.getAnimationsToExecute().size() == 1
            pacman.getAnimationsToExecute().get(0).getClass() == PacmanEatingAnimation
    }

    def "Pacman should be able to die"() {
        when:
            pacman.die()
        then:
            1 * pacman.switchTile(pacman.getStartPosition())
    }

    def "We should be able to change pacman direction"() {
        given:
            def pacmanDirection = new PacmanDirectionRight(pacman)
        when:
            pacman.setCurrentDirectionTo(pacmanDirection)
        then:
            pacman.getCurrentDirection() == pacmanDirection

    }

    def "Pacman lives should be decreased when pacman dies"() {
        given:
            def previousLives = pacman.getLives()
        when:
            pacman.die()
        then:
            pacman.getLives() == previousLives - 1
    }

    def "Pacman should restart to start position when it dies"() {
        when:
            pacman.die()
        then:
            pacman.getPosition() == pacman.getStartPosition()
    }

    def "We should be able to change the lives attribute of pacman"() {
        when:
            pacman.setLivesTo(1);
        then:
            pacman.getLives() == 1;
    }

}
