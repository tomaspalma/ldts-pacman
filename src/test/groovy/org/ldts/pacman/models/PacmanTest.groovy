package org.ldts.pacman.models

import com.googlecode.lanterna.TextColor
import org.ldts.pacman.models.game.Clock
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.pacman.Pacman
import org.ldts.pacman.models.game.entities.pacman.animations.PacmanAnimation
import org.ldts.pacman.models.game.entities.pacman.animations.PacmanEatingAnimation
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirection
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionDown
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionRight
import spock.lang.Specification

class PacmanTest extends Specification {
    private def arena
    private def pacman

    def setup() {
        arena = new Arena(20, 21, "maps/testmap.txt")
        pacman = arena.getPacman()
    }

    def "It should correctly close its mouth"() {
         when:
            pacman.closeMouth()
        then:
            pacman.getDrawSymbol() == "["
    }

    def "It should correctly open its mouth"() {
        given:
            pacman.setDrawSymbolTo("B")
            def pacDir = Stub(PacmanDirectionDown)
            pacDir.getDrawSymbol() >> "A"
            pacman.setCurrentDirectionTo(pacDir)
        when:
            pacman.openMouth()
        then:
            pacman.getDrawSymbol() == "A"
    }

    def "Pacman should have color yellow after being initialized"() {
        expect:
            pacman.getColor() == TextColor.ANSI.YELLOW_BRIGHT
    }

    def "Pacman should be able to notify its observers that it ate a fixed edible"() {
        given:
            def posMock = Mock(Position.class)
            pacman.getObservers().clear()
            def pacObserverMock = Mock(PacmanObserver.class)
            pacman.addObserver(pacObserverMock)
        when:
            pacman.notifyObserversItAteFixedEdibleAt(posMock)
        then:
            1 * pacObserverMock.changeOnPacmanEatFixedEdibleAt(posMock)
    }

    def "Pacman should have the draw symbol of the right direction on being initialized"() {
        expect:
            pacman.getCurrentDirection().getClass() == PacmanDirectionRight
    }

    def "Pacman should be initialized with the default pacman easting animation"() {
        expect:
            pacman.getAnimationsToExecute().size() == 1
            pacman.getAnimationsToExecute().get(0).getClass() == PacmanEatingAnimation
    }

    def "Pacman should be able to die"() {
        given:
            def previousLives = pacman.getLives()
        when:
            pacman.die()
        then:
            pacman.getPosition() == pacman.getStartPosition()
            pacman.getLives() == (previousLives - 1)
    }

    def "We should be able to get the animations of a certain pacman"() {
        given:
            def clockMock = Mock(Clock.class)
            pacman.getAnimations().add(new PacmanEatingAnimation(1000, clockMock, pacman))
        expect:
            pacman.getAnimations().size() == 1
    }

    def "We need to be able to add an animation to pacman"() {
        expect:
            pacman.getAnimations().size() == 0
        when:
            def clockMock = Mock(Clock.class)
            def pacAnimationToAdd = new PacmanEatingAnimation(1000, clockMock, pacman)
            pacman.addAnimation(pacAnimationToAdd)
        then:
            pacman.getAnimations().size() == 1
            pacman.getAnimations().get(0) == pacAnimationToAdd
    }

    def "We should be able to change pacman direction"() {
        given:
            def pacmanDirection = new PacmanDirectionRight(pacman)
        when:
            pacman.setCurrentDirectionTo(pacmanDirection)
        then:
            pacman.getCurrentDirection() == pacmanDirection

    }

    def "Pacman lives should be decreased when pacman dies"() {
        given:
            def previousLives = pacman.getLives()
        when:
            pacman.die()
        then:
            pacman.getLives() == previousLives - 1
    }

    def "Pacman should restart to start position when it dies"() {
        when:
            pacman.die()
        then:
            pacman.getPosition() == pacman.getStartPosition()
    }

    def "We should be able to change the lives attribute of pacman"() {
        when:
            pacman.setLivesTo(1)
        then:
            pacman.getLives() == 1
    }

    def "When pacman eats a ghost it should notify its observers of said event"() {
        given:
            def posMock = Mock(Position.class)
            pacman.getObservers().clear()
            def pacObserverMock = Mock(PacmanObserver.class)
            pacman.addObserver(pacObserverMock)
        when:
            pacman.notifyObserversItCollidedWithGhostAt(posMock)
        then:
            1 * pacObserverMock.changeOnPacmanCollisionWithGhostAt(posMock)
    }

    def "We should be able to remove an observer from the list that pacman has"() {
        given:
            pacman.getObservers().clear()
            def pacObserverMock = Mock(PacmanObserver.class)
            pacman.addObserver(pacObserverMock)
        when:
            pacman.removeObserver(pacObserverMock)
        then:
            pacman.getObservers().size() == 0
    }

    def "Pacman should know if its mouth is open or not"() {
        given:
            pacman.openMouth()
        expect:
            pacman.isMouthOpen()
    }

    def "Pacman should know if its mouth is closed or not"() {
        given:
            pacman.closeMouth()
        expect:
            pacman.isMouthOpen() == false
    }

}
