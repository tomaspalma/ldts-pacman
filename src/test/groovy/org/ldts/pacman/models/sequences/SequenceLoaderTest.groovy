package org.ldts.pacman.models.sequences

import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.arena.levels.sequences.DefaultDuringSequenceLoader
import org.ldts.pacman.models.game.arena.levels.sequences.DuringSequenceLoader
import spock.lang.Specification

class SequenceLoaderTest extends Specification {
    private def arena
    private def loader

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt")
        loader = new DefaultDuringSequenceLoader(arena.getRegularGhostsList())
    }

    def "We should be able to get the ghosts that this sequence will affect"() {
        expect:
            loader.getGhostsList() == arena.getRegularGhostsList()
    }

    def "We should be able to get the current specific ghsot start sequence list"() {
        expect:
            loader.populate().size() == 2
    }
}
