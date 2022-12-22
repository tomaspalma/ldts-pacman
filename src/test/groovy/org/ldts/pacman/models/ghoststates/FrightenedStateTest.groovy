package org.ldts.pacman.models.ghoststates

import com.googlecode.lanterna.TextColor
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.entities.ghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.states.ChasingState
import org.ldts.pacman.models.game.entities.ghost.states.FrightenedState
import spock.lang.Specification

class FrightenedStateTest extends Specification {
    private def state
    private def ghost
    private def arena

    def setup() {
        arena = Mock(Arena.class)
        ghost = new Pinky(new Position(5, 5), arena)
        state = new FrightenedState(ghost)
    }

    def "It should correctly apply changes to the ghost"() {
        given:
            ghost.setColor(TextColor.ANSI.MAGENTA)
        when:
            state.applyChangesToGhost()
        then:
            ghost.getColor() == TextColor.ANSI.BLUE
    }

}
