package org.ldts.pacman.models.arenamaploaders

import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.game.GhostHouseGate
import spock.lang.Specification

class FileMapArenaLoaderTest extends Specification {
    private def arena
    private def mapLoader

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt")
        mapLoader = arena.getMapLoader()
    }

    def "We need to be able to get the width of the map"() {
        expect:
            mapLoader.getWidth() == 20
    }

    def "We need to be able to get the height of the map"() {
        expect:
            mapLoader.getHeight() == 20
    }

    def "We should throw an illegal argument exception if we try to load gate when there is already one"() {
        given:
            def ghostHouseGate = Mock(GhostHouseGate.class)
        when:
            mapLoader.loadGate(ghostHouseGate)
        then:
            thrown(IllegalArgumentException)
    }

}
