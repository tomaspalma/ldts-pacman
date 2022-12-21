package org.ldts.pacman.controllers

import org.ldts.pacman.Game
import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.GameActions
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.fixededibles.FixedEdible
import org.ldts.pacman.models.game.entities.ghost.Pinky
import org.ldts.pacman.models.game.entities.pacman.Pacman
import org.ldts.pacman.models.game.entities.pacman.animations.PacmanAnimation
import org.ldts.pacman.models.game.entities.pacman.animations.PacmanEatingAnimation
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirection
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionDown
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionLeft
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionRight
import org.ldts.pacman.models.game.entities.pacman.directions.PacmanDirectionUp
import spock.lang.Specification

class PacmanControllerTest extends Specification {
    private def pacController
    private def arenaController
    private def arena
    private def pacman

    def setup() {
        arena = new Arena(20, 21, "maps/easy.txt")
        arenaController = new ArenaController(arena)
        pacController = new PacmanController(arenaController, arena)
        arenaController.setAreSoundsSilenced(true)
        pacman = pacController.getModel().getPacman()
    }

    def "Upon instantation it should add itself to the pacman observers list"() {
        expect:
            pacman.getObservers().contains(arenaController)
    }

    def "When moving pacman depending on the right action it should set the controller wanted direction to the correct one"() {
        when:
            pacController.movePacmanAccordingTo(GameActions.ControlActions.MOVE_LEFT)
        then:
            pacController.getWantedDirection().getClass() == (new PacmanDirectionLeft(pacman)).getClass()
        when:
            pacController.movePacmanAccordingTo(GameActions.ControlActions.MOVE_RIGHT)
        then:
            pacController.getWantedDirection().getClass() == (new PacmanDirectionRight(pacman)).getClass()
        when:
            pacController.movePacmanAccordingTo(GameActions.ControlActions.MOVE_UP)
        then:
            pacController.getWantedDirection().getClass() == (new PacmanDirectionUp(pacman)).getClass()
        when:
            pacController.movePacmanAccordingTo(GameActions.ControlActions.MOVE_DOWN)
        then:
            pacController.getWantedDirection().getClass() == (new PacmanDirectionDown(pacman)).getClass()

    }

    def "We should execute the step of our animations"() {
        given:
            def animationMock = Mock(PacmanAnimation.class)
            pacman.getAnimationsToExecute().clear()
            pacman.getAnimationsToExecute().add(animationMock)
        when:
            pacController.executeAnimations()
        then:
            1 * animationMock.step()
        when:
            animationMock.isFinished() >> true
            pacController.executeAnimations()
        then:
            pacman.getAnimationsToExecute().isEmpty()
    }

    def "It should notify pacman it collided with a ghost"() {
        given:
            def tile = arenaController.getModel().getGameGrid().get(0).get(0)
            tile.put(new Pinky(new Position(0, 1), arena))
            def pac = Mock(Pacman.class)
            pacController.setPacmanTo(pac)
        when:
            pacController.actIfCollisionWithSpecialEntitiesAt(new Position(0, 1))
        then:
            1 * pac.notifyObserversItCollidedWithGhostAt(new Position(0, 1))
    }

    def "Step function should execute the move pacman function and execute the step of its animations"() {
        given:
            arena.getPacman().getAnimationsToExecute().clear()
            def pacAnimationMock = Mock(PacmanEatingAnimation.class)
            arena.getPacman().getAnimationsToExecute().add(pacAnimationMock)
            pacController.setWantedDirectionTo(new PacmanDirectionRight(arena.getPacman()))
            def gameMock = Mock(Game.class)
            def action = GameActions.ControlActions.MOVE_LEFT
        when:
            pacController.step(gameMock, action, 1000)
        then:
            1 * pacAnimationMock.step()
            pacController.getWantedDirection() instanceof PacmanDirectionLeft
    }

    def "When moving pacman we should only change pacman's position if he's able to go to a certain position"() {
        given:
            pacman.setPosition(new Position(0, 1))
            def pacDirStub = Stub(PacmanDirection)
            pacman.setCurrentDirectionTo(pacDirStub)
            pacDirStub.getNextPosition() >> pacman.getStartPosition()
        when:
            pacController.movePacman()
        then:
            pacman.getPosition() == pacman.getStartPosition()
    }

    def "When trying to change direction of pacman it should correctly check if pacman will be able to move to that position"() {
        given:
            def pacDirStub = Stub(PacmanDirection.class)
            pacController.setWantedDirectionTo(pacDirStub)
            pacDirStub.getNextPosition() >> pacman.getStartPosition()
        when:
            pacController.tryToChangeToWantedDirection()
        then:
            pacman.getCurrentDirection() == pacDirStub
    }

    def "It should make pacman notify its observers"() {
        given:
            def pos = new Position(5, 5)
            def fixedEdible = Mock(FixedEdible.class)
            def tile = arena.getGameGrid().get(pos.getY() - 1).get(pos.getX())
            def pacman = Mock(Pacman.class)
            pacController.setPacman(pacman)
            tile.put(fixedEdible)
        when:
            pacController.actIfCollisionWithSpecialEntitiesAt(pos)
        then:
            1 * pacman.notifyObserversItAteFixedEdibleAt(pos)
    }
}
