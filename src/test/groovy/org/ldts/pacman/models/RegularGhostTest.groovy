package org.ldts.pacman.models

import com.googlecode.lanterna.TextColor
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.ghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.states.FrightenedState;
import spock.lang.Specification;

public class RegularGhostTest extends Specification {
    private def regularGhost;

    def setup() {
        regularGhost = new Pinky(new Position(5, 5), new Arena(20, 21, "maps/testmap.txt"));
    }

    def "Regular ghost will switch to frightened stage after pacman eats powerpellet"() {
        when:
            regularGhost.handlePowerPelletBeingEaten();
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

    def "Frightened state should last the specified amount of time"() {

    }
}

