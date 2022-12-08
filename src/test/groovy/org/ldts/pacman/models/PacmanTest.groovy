package org.ldts.pacman.models

import spock.lang.Specification

class PacmanTest extends Specification {
    private def arena
    private def pacman

    def setup() {
        arena = new Arena(20, 21, "maps/testmap.txt")
        pacman = arena.getPacman();
    }

}
