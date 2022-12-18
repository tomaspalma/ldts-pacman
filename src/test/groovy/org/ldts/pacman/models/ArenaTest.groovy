package org.ldts.pacman.models

import org.ldts.pacman.models.game.entities.Obstacle
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.fixededibles.Cherry
import org.ldts.pacman.models.game.entities.pacman.Pacman
import spock.lang.Specification

class ArenaTest extends Specification {
    private def arena

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt")
    }

    def "Arena should be able to detect if obstacle is at a certain position"() {
        given:
            def pacman = new Pacman(new Position(5, 5), arena)
            arena.setPacman(pacman)
            def obstacle = new Obstacle(new Position(5, 5), arena)
        when:
            arena.addObstacle(obstacle)
        then:
            arena.isObstacleAt(new Position(5, 5))
    }

    def "Arena should be able to return valid index if fixed edible is at a certain position"() {
        given:
            def fixedEdible = new Cherry(new Position(5, 5), arena)
        when:
            arena.addToGeneralFixedEdibleList(fixedEdible)
        then:
            arena.getFixedEdibleAt(new Position(5, 5)) != -1
    }

    def "If we don't find a certain fixed edible in the list of the arena, we need to return -1"() {
        given:
            arena.getGeneralFixedEdibleList().clear()
        expect:
            arena.getFixedEdibleAt(new Position(4, 2)) == -1
    }

    def "We should be able to get a ghost that is at a certain position"() {
        given:
            def desiredPosition = arena.getRegularGhostsList().get(0).getPosition()
            def desiredGhost = arena.getRegularGhostsList().get(0)
        expect:
            desiredGhost == arena.getGhostAt(desiredPosition)
    }

    def "If we don't find a ghost, we should return -1 in getGhostAt function"() {
        expect:
            arena.getGhostAt(new Position(0, 0)) == null
    }

    def "If there is no obstacle at a certain position we should return false"() {
        given:
            def pacPos = arena.getPacman().getPosition()
        expect:
            arena.isObstacleAt(pacPos) == false
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

    def cleanup() {
        this.arena.getGameGrid().clear()
    }

}

