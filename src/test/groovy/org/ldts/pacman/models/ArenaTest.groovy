package org.ldts.pacman.models

import org.ldts.pacman.models.game.arena.Arena
import spock.lang.Specification

class ArenaTest extends Specification {
    private def arena

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt")
    }

    def "Amount of grid elements must be equal to the total amount of elements in the other separate lists"() {
        given:
            def noOfGridElements = 0
            def totalEntities = 1 + arena.getObstaclesList().size() + arena.getGeneralFixedEdibleList().size() + arena.getRegularGhostsList().size() + arena.getGhostHouseSize()
            def noOfNonGridElementsInMap = 3 //ghost house and the 2 teletransporter tiles
        when:
            for(list in arena.getGameGrid()) {
                for(element in list) {
                   noOfGridElements += 1
                }
            }
        then:
            (noOfGridElements - noOfNonGridElementsInMap) == totalEntities
    }

    def "Size of each row must be equal to each other"() {
        given:
            def expectedSizeOfEachRow = arena.getGameGrid().get(0).size()
            def expectedResult = true
        when:
            def sum = 0
            for(list in arena.getGameGrid()) {
                for(element in list) {
                    sum += 1
                }
                if(sum != expectedSizeOfEachRow) {
                    expectedResult = false
                    break
                }
                sum = 0
            }
        then:
            expectedResult == true
    }

    def "Assert limits of grid size"() {
        expect:
            arena.getGameGrid().size() == arena.getHeight() - 1
            arena.getGameGrid().get(0).size() == arena.getWidth()
    }

    def "We should be able to get the correct index of a fixed edible when we try to get a fixed edible at a certain position"() {

    }

    def cleanup() {
        this.arena.getGameGrid().clear()
    }

}

