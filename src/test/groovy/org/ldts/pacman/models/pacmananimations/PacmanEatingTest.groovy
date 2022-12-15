package org.ldts.pacman.models.pacmananimations

import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.pacman.Pacman
import org.ldts.pacman.models.game.entities.pacman.animations.PacmanEatingAnimation
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirection
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionDown
import spock.lang.Specification

class PacmanEatingTest extends Specification {
    private def pacman
    private def arena
    private def pacAnimation

    def setup() {
        arena = Mock(Arena.class)
        pacman = new Pacman(new Position(5, 5), arena)
        pacAnimation = new PacmanEatingAnimation(1000, pacman)
    }

    def "Animation step should increase by 1000001"() {
        given:
            def previous = pacAnimation.getControl()
        when:
            pacAnimation.step()
        then:
            pacAnimation.getControl() == (previous + 1000001)
    }
}
