package org.ldts.pacman.controllers

import org.ldts.pacman.Game
import org.ldts.pacman.gui.GUI
import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.GameActions
import org.ldts.pacman.models.GhostDuringStateSequence
import org.ldts.pacman.models.SpecificGhostStartSequence
import org.ldts.pacman.models.game.Clock
import org.ldts.pacman.models.game.arena.levels.GameLevel
import org.ldts.pacman.models.game.entities.fixededibles.FixedEdible
import org.ldts.pacman.models.game.entities.fixededibles.Pacdot
import org.ldts.pacman.models.game.entities.fixededibles.PowerPellet
import org.ldts.pacman.models.game.entities.ghost.Ghost
import org.ldts.pacman.models.game.entities.ghost.Pinky
import org.ldts.pacman.models.game.entities.ghost.RegularGhost
import org.ldts.pacman.models.game.entities.ghost.states.FrightenedState
import org.ldts.pacman.models.game.entities.pacman.Pacman
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.menus.GameOverMenu
import org.ldts.pacman.models.menus.PauseMenu
import org.ldts.pacman.sounds.SFX
import org.ldts.pacman.states.ArenaState
import org.ldts.pacman.states.menus.PauseMenuState
import org.ldts.pacman.states.menus.RegularMenuState
import spock.lang.Specification

class ArenaControllerTest extends Specification {
    private def arena
    private def pacman
    private def arenaController
    private def pacController
    private def regularGhostController
    private def pacman_eating_edible = Mock(SFX.class)
    private def pacman_dying_sound = Mock(SFX.class)
    private def kill_ghost_sound = Mock(SFX.class)

    def setup() {
        arena = new Arena(20, 20, "maps/easy.txt")
        pacController= Mock(PacmanController.class)
        regularGhostController = Mock(RegularGhostController.class)
        arenaController = new ArenaController(arena)
        arenaController.setPacmanController(pacController)
        arenaController.setRegularGhostController(regularGhostController)
        arenaController.setSounds(new ArrayList<>(Arrays.asList(pacman_eating_edible, pacman_dying_sound, kill_ghost_sound)))
    }

    def "Arena controller should be initialized with pacman and regular ghsot controller"() {
        given:
            arenaController.setPacmanController(new PacmanController(arenaController, arena))
            arenaController.setRegularGhostController(new RegularGhostController(arenaController, arena))
        expect:
            arenaController.getPacmanController().getClass() == PacmanController.class
            arenaController.getRegularGhostController().getClass() == RegularGhostController.class
    }

    def "We should restore the fixed edibles list the next level if the current leven has ended and do nothting if it hasn't ended yet"() {
        given:
            arenaController.getModel().getGeneralFixedEdibleList().clear()
        when:
            arenaController.actIfLevelEnded()
        then:
            arenaController.getModel().getGeneralFixedEdibleList().size() != 0
        when:
            arenaController.actIfLevelEnded()
        then:
            arenaController.getModel().getGeneralFixedEdibleList().size() > 0
    }

    def "When stepping this controller we should call the steps of our children controllers"() {
        given:
            def game = Mock(Game.class)
            def action = GroovyMock(GameActions.ControlActions.class)
            def pacControllerMock = Mock(PacmanController.class)
            def regularGhostControllerMock = Mock(RegularGhostController.class)
            arenaController.setPacmanController(pacControllerMock)
            arenaController.setRegularGhostController(regularGhostControllerMock)
        when:
            arenaController.step(game,action ,1000)
        then:
            1 * pacControllerMock.step(game, action, 1000)
            1 * regularGhostControllerMock.step(game, action, 1000)
    }

    def "It should correctly put the level back to start position"() {
        when:
            arenaController.putCurrentLevelBackToStartPositions()
        then:
            1 * regularGhostController.putGhostsBackInInitialState()
            arenaController.getModel().getPacman().getPosition() ==
                arenaController.getModel().getPacman().getStartPosition()
            /*for(RegularGhost ghost: arenaController.getModel().getRegularGhostsList()) {
                if(ghost.getPosition() == arenaController.getModel().getGhostHouse().getExitPosition()
                || ghost.getPosition() )
            }*/
    }

    def "It should show a game over menu when the player loses"() {
        given:
            arenaController.getModel().getPacman().setLivesTo(0)
            def gui = Mock(GUI.class)
            def action = GroovyMock(GameActions.ControlActions.class)
            def game = new Game(21, 21, gui, null)
        when:
            arenaController.step(game, action, 1000)
        then:
            game.getState().getClass() == (new RegularMenuState(new GameOverMenu("loss"))).getClass()
    }

    def "It should pause the level clock if the ghosts are frightened"() {
        given:
            arenaController.getModel().getRegularGhostsList().clear()
            def ghost = new Pinky(new Position(9, 2), arena)
            ghost.setCurrentStateTo(new FrightenedState(ghost))
            def gui = Mock(GUI.class)
            def action = GroovyMock(GameActions.ControlActions.class)
            def game = new Game(21, 21, gui, null)
            arenaController.getModel().getRegularGhostsList().add(ghost)
        when:
            arenaController.step(game, action, 1000)
            def result1 = arenaController.getModel().getLevels().get(0).getClock().getElapsedMilliseconds()
            arenaController.step(game, action, 1000)
            def result2 = arenaController.getModel().getLevels().get(0).getClock().getElapsedMilliseconds()
        then:
            result1 == result2
    }

    def "Upon action EXIT the controller should set the game state to null"() {
        given:
            def gui = Mock(GUI.class)
            def action = GameActions.ControlActions.EXIT
            def game = new Game(21, 21, gui, new ArenaState(arena))
        when:
            arenaController.step(game, action, 1000)
        then:
            game.getState() == null
    }

    def "Upon switch to pause menu action it should set the state of game to pause menu"() {
        given:
            def gui = Mock(GUI.class)
            def action = GameActions.ControlActions.SWITCH_TO_PAUSE_MENU
            def game = new Game(21, 21, gui, null)
            def result = new PauseMenuState(new PauseMenu((ArenaState) game.getState()))
        when:
            arenaController.step(game, action, 1000)
        then:
            game.getState().getClass() == result.getClass()
    }

    def "When the clock is paused and the ghosts aren't frightened it should unpause it"() {
        given:
            def gui = Mock(GUI.class)
            def action = GameActions.ControlActions.SWITCH_TO_PAUSE_MENU
            def game = new Game(21, 21, gui, null)
            arenaController.getModel().getLevels().get(0).getClock().pause()
        when:
            arenaController.step(game, action, 1000)
        then:
            !arenaController.getModel().getLevels().get(0).getClock().isPaused()
    }

    def "It should correctly process when pacman loses a life"() {
        given:
            def pac = Mock(Pacman.class)
            arenaController.getModel().setPacman(pac)
        when:
            arenaController.processPacmanLoseLife()
        then:
            1 * pacman_dying_sound.play()
            1 * pac.die()
    }

    def "It should kill pacman if the collision with a ghost results in KILL_PACMAN action"() {
        given:
            arenaController.setAteGhostPoints(500)
            def tile = arena.getGameGrid().get(0).get(0)
            def ghostMock = Stub(Ghost.class)
            ghostMock.getCollisionWithPacmanResult() >> GameActions.GhostCollisionWithPacman.KILL_PACMAN
            tile.put(ghostMock)
            def pacMock = Mock(Pacman.class)
            arena.setPacman(pacMock)
        when:
            arenaController.changeOnPacmanCollisionWithGhostAt(new Position(0, 1))
        then:
            arenaController.getAteGhostPoints() == 200
            1 * pacMock.die()
    }

    def "It should kill the ghosts if the collision between pacman and ghots results in KILL_GHOST action"() {
        given:
            arena.sumScoreWith(459)
            def tile = arena.getGameGrid().get(0).get(0)
            def ghostMock = Stub(Ghost.class)
            ghostMock.getCollisionWithPacmanResult() >> GameActions.GhostCollisionWithPacman.KILL_GHOST
            tile.put(ghostMock)
        when:
            arenaController.changeOnPacmanCollisionWithGhostAt(new Position(0, 1))
        then:
            arena.getScore() == 659
            1 * regularGhostController.killGhost(ghostMock)
            1 * kill_ghost_sound.play()
    }

    def "It should call the step function of the current level"() {
       given:
            arenaController.getModel().getLevels().clear()
            def ssM = Mock(List<SpecificGhostStartSequence>)
            def dSM = Mock(List<GhostDuringStateSequence>)
            def gL = Mock(List<RegularGhost>)
            def level = new GameLevel(ssM, dSM, gL, new Clock(System.currentTimeMillis()))
            def levelMock = Spy(level)
            arenaController.getModel().getLevels().add(levelMock)
            def gameMock = Mock(Game.class)
            def actionsMock = GroovyMock(GameActions.ControlActions)
        when:
            arenaController.step(gameMock, actionsMock, 1000)
        then:
            1 * levelMock.step()
    }

    def "We should correctly act if pacman eats a fixed edible"() {
        given:
            def edible = Mock(FixedEdible.class)
            def pos = new Position(5, 5)
            def tile = arena.getGameGrid().get(pos.getY() - 1).get(pos.getX())
            tile.put(edible)
        when:
            arenaController.changeOnPacmanEatFixedEdibleAt(pos)
        then:
            1 * pacman_eating_edible.play()
            tile.containsFixedEdible() == false
    }

    def "When pacman eats a fixed edible, if it is a power pellet observable, the power pellet should notify its observers"() {
        given:
            def pos = new Position(5, 5)
            def powerPelletObservable = Mock(PowerPellet.class)
            def tile = arena.getGameGrid().get(pos.getY() - 1).get(pos.getX())
            tile.put(powerPelletObservable)
        when:
            arenaController.changeOnPacmanEatFixedEdibleAt(pos)
        then:
            1 *powerPelletObservable.notifyObservers()
    }

    def "When changing when pacman eats a fixed edible it should force arena to sum score"() {
        given:
            def pos = new Position(5, 5)
            def fixedEdible = new Pacdot(pos, arena)
            def tile = arena.getGameGrid().get(pos.getY() - 1).get(pos.getX())
            def previousScore = arena.getScore()
            tile.put(fixedEdible)
        when:
            arenaController.changeOnPacmanEatFixedEdibleAt(new Position(5, 5))
        then:
            arena.getScore() == (previousScore + fixedEdible.getPoints())
    }

    def "When switching to next level it should apply the modulo operator with the size of arena.getLevels()"() {
        given:
            def noOfLevels = arena.getLevels().size()
            arenaController.setCurrentLevel(noOfLevels)
        when:
            arenaController.switchToNextLevel()
        then:
            arenaController.getCurrentLevel() == 0
    }

    def "It should pause the level clock if a regular ghost is on frightened mode"() {
        given:
            arena.getLevels().clear()
            def levelMock = Mock(GameLevel.class)
            arena.getLevels().add(levelMock)
            def clock = Mock(Clock.class)
            levelMock.getClock() >> clock
            arena.getRegularGhostsList().clear()
            def ghost = new Pinky(new Position(4, 1), arena)
            ghost.setCurrentStateTo(new FrightenedState(ghost))
            arena.getRegularGhostsList().add(ghost)
        when:
            arenaController.checkConditionsToPauseLevelClock()
        then:
            1 * clock.pause()
    }

    def "Arena controller should be able to restore fixed edibles"() {
        given:
            arena.getGeneralFixedEdibleList().clear()
        when:
            arenaController.restoreFixedEdibles()
        then:
            arena.getGeneralFixedEdibleList().size() > 0
            arena.getGeneralFixedEdibleList().size() == arena.getEatenFixedEdiblePool().size()
    }

}
