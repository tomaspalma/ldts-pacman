package org.ldts.pacman.models

import spock.lang.Specification

class GhostDirectionTest extends Specification {
    private def arena

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt")
    }

    def "We should be able to detect if a theoretically created position is invalid "() {

    }
}
