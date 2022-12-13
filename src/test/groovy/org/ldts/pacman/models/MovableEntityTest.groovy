package org.ldts.pacman.models

import org.ldts.pacman.models.game.entities.ghost.directions.GhostDirection
import spock.lang.Specification

class MovableEntityTest extends Specification {
    private def movableEntity

    def setup() {
       movableEntity = Spy(MovableEntity)
    }

    def "We should be able to change direction of a movable entity"() {
        given:
            def direction= Mock(MovableEntityDirection)
        when:
            movableEntity.setCurrentDirectionTo(directionMock)
        then:
            movableEntity.getCurrentDirection() == directionMock
    }

}
