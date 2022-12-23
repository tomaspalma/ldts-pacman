package org.ldts.pacman.models.ghostdirections

import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.ghost.Ghost
import org.ldts.pacman.models.game.entities.ghost.regularghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionDown
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionUp
import spock.lang.Specification

class GhostDirectionDownTest extends Specification {
    private def dir
    private def ghost

    def setup() {
        ghost = Stub(Ghost)
        dir = new GhostDirectionDown(ghost)
    }

    def "It should correctly return the list of possible positions to move"() {
        given:
            def ghostStub = Stub(Ghost.class)
            def dir1 = new GhostDirectionDown(ghostStub)
            ghostStub.willBeInInvalidPosition(_ as Position) >> false
        when:
            def l = dir1.getPossiblePositionsToMove()
        then:
            l == [dir1.getPossiblePositionToMoveLeft(), dir1.getPossiblePositionToMoveRight(),
                dir1.getPossiblePositionToMoveDown()]
    }

    def "it should turn around to direction up"() {
        given:
            def ghostMock = Mock(Ghost)
            def dir1 = new GhostDirectionDown(ghostMock)
            ghostMock.setCurrentDirectionTo(dir1)
        when:
            dir1.turnAround()
        then:
            1 * ghostMock.setCurrentDirectionTo(_)
    }

    def "It should step the direction to the new correct one"() {
        given:
            def arena = Mock(Arena.class)
            def realGhost = new Pinky(new Position(5, 5), arena)
            def realDir = new GhostDirectionDown(realGhost)
            realGhost.setCurrentDirectionTo(realDir)
        when:
            realDir.turnAround()
        then:
            realGhost.getCurrentDirection().getClass() == (new GhostDirectionUp(realGhost)).getClass()
    }

}
