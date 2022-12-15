package org.ldts.pacman.models.ghostdirections

import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.ghost.Ghost
import org.ldts.pacman.models.game.entities.ghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionDown
import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirectionUp
import spock.lang.Specification

class GhostDirectionDownTest extends Specification {
    private def dir
    private def ghost

    def setup() {
        ghost = Mock(Ghost)
        dir = new GhostDirectionDown(ghost)
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
            def realDir = new GhostDirectionDown(realGhost)
            realGhost.setCurrentDirectionTo(realDir)
        when:
            realDir.turnAround()
        then:
            realGhost.getCurrentDirection().getClass() == (new GhostDirectionUp(realGhost)).getClass()
    }

}
