package org.ldts.pacman.models

import spock.lang.Specification

class VectorTest extends Specification {
    private def position
    private def arena

    def setup() {
        arena = new Arena(20, 21, "maps/testmap.txt")
    }


}
