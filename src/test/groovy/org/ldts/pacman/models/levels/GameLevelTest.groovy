package org.ldts.pacman.models.levels

import org.apache.tools.ant.taskdefs.Sleep
import org.ldts.pacman.models.GhostDuringStateSequence
import org.ldts.pacman.models.LevelStateSequence
import org.ldts.pacman.models.SpecificGhostStartSequence
import org.ldts.pacman.models.game.Clock
import org.ldts.pacman.models.game.arena.levels.GameLevel
import org.ldts.pacman.models.game.entities.ghost.RegularGhost
import spock.lang.Specification

class GameLevelTest extends Specification {
    private def level
    private def startStateMachine
    private def duringStateMachine
    private def regularGhostsList
    private def clock

    def setup() {
        def startStateMachine = Mock(List<SpecificGhostStartSequence>.class)
        def duringStateMachine = Mock(List<GhostDuringStateSequence>.class)
        def regularGhostsList = Mock(List<RegularGhost>.class)
        level = new GameLevel(startStateMachine, duringStateMachine, regularGhostsList, new Clock(System.currentTimeMillis()))
    }

    def "We need to check if the other during sequences remaining is intializing correctly"() {
        given:
            def duringSMStub = Stub(List<GhostDuringStateSequence>.class)
            level.setDuringStateMachine(duringSMStub)
            duringSMStub.isEmpty() >> false
        expect:
            level.isOtherDuringSequencesRemaining() == true
    }

    def "During them main step function it should call the clock step function"() {
        given:
            def clockMock = Mock(Clock.class)
            level.setClock(clockMock)
        when:
            level.step()
        then:
            1 * clockMock.step()
    }

    def "We should be able to restart a level"() {
        given:
            level.setOtherDuringSequencesRemaining(false)
            level.setOtherStartSequencesRemaining(false)
            level.setStartStateMachineCounter(999)
            level.setDuringStateMachineCounter(2348902)
        when:
            level.restart()
        then:
            level.isOtherDuringSequencesRemaining() == true
            level.isOtherStartSequencesRemaining () == true
            level.getStartStateMachineCounter() == 0
            level.getDuringStateMachineCounter() == 0
    }

    def "If is time to activate we should return true and false otherwise"() {
        given:
            def levelStateSequenceStub = Stub(LevelStateSequence.class)
            levelStateSequenceStub.getTimeToBeActivatedInMilliseconds() >> 0.01
            def realClock = new Clock(System.currentTimeMillis())
            level.setClock(realClock)
        when:
            Thread.sleep(1)
            realClock.step()
        then:
            level.isTimeToActivate(levelStateSequenceStub) == true
        when:
            realClock.reset()
        then:
            level.isTimeToActivate(levelStateSequenceStub) == false
    }

    def "We should be able to run the code accordingly if there are otherStartSequencesRemaining"() {
        given:
            def levelStartSequence1 = Stub(SpecificGhostStartSequence.class)
            def levelStartSequence2 = Stub(SpecificGhostStartSequence.class)
            def startStateMachine = new ArrayList<>(Arrays.asList(levelStartSequence1, levelStartSequence2))
            levelStartSequence1.getTimeToBeActivatedInMilliseconds() >> 0
            levelStartSequence2.getTimeToBeActivatedInMilliseconds() >> 4234
            level.setStartStateMachine(startStateMachine)
            level.setStartStateMachineCounter(0)
        when:
            level.step()
        then:
            level.getStartStateMachineCounter() == 1
            level.getDuringStateMachineCounter() == 0
    }


 def "We should be able to run the code accordingly if there are otherDuringStateSequencesRemaining"() {
        given:
            def levelStartSequence1 = Stub(SpecificGhostStartSequence.class)
            def levelStartSequence2 = Stub(SpecificGhostStartSequence.class)
            def levelDuringSequence1 = Stub(GhostDuringStateSequence.class)
            def levelDuringSequence2= Stub(GhostDuringStateSequence.class)
            def levelDuringSequence3 = Stub(GhostDuringStateSequence.class)
            def startStateMachine = new ArrayList<>(Arrays.asList(levelStartSequence1, levelStartSequence2))
            def duringStateMachine = new ArrayList<>(Arrays.asList(levelDuringSequence1, levelDuringSequence2, levelDuringSequence3))
            levelStartSequence1.getTimeToBeActivatedInMilliseconds() >> 0
            levelStartSequence2.getTimeToBeActivatedInMilliseconds() >> 0
            levelDuringSequence1.getTimeToBeActivatedInMilliseconds() >> 0
            levelDuringSequence2.getTimeToBeActivatedInMilliseconds() >> 242348
            levelDuringSequence3.getTimeToBeActivatedInMilliseconds() >> 242342349
            level.setStartStateMachine(startStateMachine)
            level.setDuringStateMachine(duringStateMachine)
            level.setStartStateMachineCounter(level.getStartStateMachine().size() - 1)
        when:
            level.step()
            level.step()
            level.step()
        then:
            level.getStartStateMachineCounter() == level.getStartStateMachine().size()
    }

    /*def "If there are more than one start sequence that has the same activation time they should be activated almost at the same time"() {
        given:
            def levelStartSequence1 = Stub(SpecificGhostStartSequence.class)
            def levelStartSequence2 = Stub(SpecificGhostStartSequence.class)
            def levelStartSequence3= Stub(SpecificGhostStartSequence.class)
            def startStateMachine = new ArrayList<>(Arrays.asList(levelStartSequence1, levelStartSequence2, levelStartSequence3))
            levelStartSequence1.getTimeToBeActivatedInMilliseconds() >> 50
            levelStartSequence2.getTimeToBeActivatedInMilliseconds() >> 50
            levelStartSequence3.getTimeToBeActivatedInMilliseconds() >> 500
            level.setStartStateMachine(startStateMachine)
            level.setStartStateMachineCounter(0)
        when:
            level.step()
        then:
<<<<<<< HEAD
            level.getStartStateMachineCounter() == 2
    }*/
}
