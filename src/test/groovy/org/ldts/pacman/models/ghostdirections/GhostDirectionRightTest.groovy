package org.ldts.pacman.models.ghostdirections

import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.ghost.Ghost
import org.ldts.pacman.models.game.entities.ghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionLeft
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionRight
import spock.lang.Specification

class GhostDirectionRightTest extends Specification {
    private def dir
    private def ghost

    def setup() {
        ghost = Mock(Ghost)
        dir = new GhostDirectionRight(ghost)
    }

    def "It should correctly return the list of possible positions to move"() {
        given:
            def ghostStub = Stub(Ghost.class)
            def dir1 = new GhostDirectionRight(ghostStub)
            ghostStub.willBeInInvalidPosition(_ as Position) >> false
        when:
            def l = dir1.getPossiblePositionsToMove()
        then:
            l == [dir1.getPossiblePositionToMoveRight(), dir1.getPossiblePositionToMoveUp(),
                dir1.getPossiblePositionToMoveDown()]
    }

    def "it should turn around to direction up"() {
        given:
            ghost.setCurrentDirectionTo(dir)
        when:
            dir.turnAround()
        then:
            1 * ghost.setCurrentDirectionTo(_)
    }

    def "It should step the direction to the new correct one"() {
        given:
            def arena = Mock(Arena.class)
            def realGhost = new Pinky(new Position(5, 5), arena)
            def realDir = new GhostDirectionRight(realGhost)
            realGhost.setCurrentDirectionTo(realDir)
        when:
            realDir.turnAround()
        then:
            realGhost.getCurrentDirection().getClass() == (new GhostDirectionLeft(realGhost)).getClass()
    }
}
