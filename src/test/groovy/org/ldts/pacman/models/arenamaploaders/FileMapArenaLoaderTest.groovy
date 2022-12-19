package org.ldts.pacman.models.arenamaploaders

import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.PowerPelletObservable
import org.ldts.pacman.models.game.GhostHouseGate
import org.ldts.pacman.models.game.arena.grid.TeletransporterTile
import org.ldts.pacman.models.game.arena.grid.Tile
import org.ldts.pacman.models.game.entities.fixededibles.FixedEdible
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

    def "If there are any power pellet observables, regular ghosts should be added to the observers list of them"() {
        expect:
            for(FixedEdible e: arena.getGeneralFixedEdibleList()) {
                if(e instanceof PowerPelletObservable) {
                    e.getObservers().size() == arena.getRegularGhostsList().size()
                }
            }
    }

    def "We need to be able to correctly set the pacman position to the start position when loading"() {
        expect:
            arena.getPacman().getPosition() == arena.getPacman().getStartPosition()
    }

    def "On a valid map like the test map, every teletransporter tile should have an exit tile and that tile should be a teletransporter as well"() {
        expect:
            for(List<Tile> row: arena.getGameGrid()) {
                for(Tile t: row) {
                    if(t instanceof TeletransporterTile) {
                        t.getExitTile() != null
                        t.getExitTile() instanceof TeletransporterTile
                    }
                }
            }
    }

}
