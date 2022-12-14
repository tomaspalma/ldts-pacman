package org.ldts.pacman.models

import org.ldts.pacman.models.game.entities.fixededibles.FixedEdible
import spock.lang.Specification

class FixedEntityTest extends Specification {
    def "A fixed entity should not have a set posiiton method"() {
        given:
            def fixedEdible = Mock(FixedEdible)
        when:
            fixedEdible.setPosition()
        then:
            thrown(MissingMethodException)
        when:
            fixedEdible.setPositionTo()
        then:
            thrown(MissingMethodException)
    }
}
