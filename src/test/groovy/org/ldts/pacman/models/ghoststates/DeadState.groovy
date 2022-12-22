package org.ldts.pacman.models.ghoststates

import com.googlecode.lanterna.TextColor
import org.ldts.pacman.models.game.GameActions
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.entities.ghost.Clyde
import org.ldts.pacman.models.game.entities.ghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.states.DeadState
import spock.lang.Specification

class DeadState extends Specification {
    private def state
    private def ghost
    private def arena

    def setup() {
        arena = Mock(Arena.class)
        ghost = new Clyde(new Position(5, 5), arena)
        state = new org.ldts.pacman.models.game.entities.ghost.states.DeadState(ghost)
    }

    def "It should return game action none when hitting a pacman"() {
        given:
            def expected = GameActions.GhostCollisionWithPacman.NONE
        expect:
            expected == state.collideWithPacmanResult()
    }

    def "It should turn the ghosts white when applying the changes"() {
        when:
            state.applyChangesToGhost()
        then:
            ghost.getColor() == TextColor.ANSI.WHITE
    }

}
