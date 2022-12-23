package org.ldts.pacman.models.levels


import org.ldts.pacman.models.game.arena.levels.sequences.GhostDuringStateSequence
import org.ldts.pacman.models.game.arena.levels.sequences.LevelStateSequence
import org.ldts.pacman.models.game.arena.levels.sequences.SpecificGhostStartSequence
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

    def "We need to be able to use a getter to the during state machine"() {
        given:
            def ghostDSS = Mock(GhostDuringStateSequence.class)
            level.setDuringStateMachine(new ArrayList<>(Arrays.asList(ghostDSS)))
        expect:
            level.getDuringStateMachine().size() == 1
            level.getDuringStateMachine().get(0) == ghostDSS
    }

    def "We need to be able to detect if there are other sequences remaining"() {
        expect:
            level.isOtherStartSequencesRemaining()
            level.isOtherDuringSequencesRemaining()
        when:
            level.setOtherStartSequencesRemaining(false)
        then:
            level.isOtherStartSequencesRemaining() == false
        when:
            level.setOtherDuringSequencesRemaining(false)
        then:
            level.isOtherDuringSequencesRemaining() == false
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
            def clockMock = Mock(Clock.class)
            level.setClock(clockMock)
        when:
            level.restart()
        then:
            level.isOtherDuringSequencesRemaining() == true
            level.isOtherStartSequencesRemaining () == true
            level.getStartStateMachineCounter() == 0
            level.getDuringStateMachineCounter() == 0
            1 * clockMock.reset()
    }

    /*def "In the default game level we should be able to apply transformations based on a multiplier"() {
        given:
            def ghostDSS = Mock(GhostDuringStateSequence.class)
            level.setDuringStateMachine(new ArrayList<>(Arrays.asList(ghostDSS)))
            def ghostSSS = Mock(SpecificGhostStartSequence.class)
            level.setStartStateMachine(new ArrayList<>(Arrays.asList(ghostSSS)))
            def clockMock = Mock(Clock.class)
        when:
            level.transformItselfIntoAnotherLevel()
        then:
            1 * ghostDSS.setTimeToBeActivatedInMilliseconds(_)
            1 * ghostSSS.setTimeToBeActivatedInMilliseconds(_)
    } */

    def "It should restart when transforming to another level"() {
        given:
            def ghostDSS = Mock(GhostDuringStateSequence.class)
            level.setDuringStateMachine(new ArrayList<>(Arrays.asList(ghostDSS)))
            def ghostSSS = Mock(SpecificGhostStartSequence.class)
            level.setStartStateMachine(new ArrayList<>(Arrays.asList(ghostSSS)))
            level.setOtherDuringSequencesRemaining(false)
            level.setOtherStartSequencesRemaining(false)
            level.setStartStateMachineCounter(999)
            level.setDuringStateMachineCounter(2348902)
            def clockMock = Mock(Clock.class)
            level.setClock(clockMock)
        when:
            level.transformItselfIntoAnotherLevel()
        then:
            level.isOtherDuringSequencesRemaining() == true
            level.isOtherStartSequencesRemaining () == true
            level.getStartStateMachineCounter() == 0
            level.getDuringStateMachineCounter() == 0
            1 * clockMock.reset()
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

   /* def "We should be able to run the code accordingly if there are otherStartSequencesRemaining"() {
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
    } */


    /*def "We should be able to run the code accordingly if there are otherDuringStateSequencesRemaining"() {
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
        then:
            level.getStartStateMachineCounter() == level.getStartStateMachine().size()
    }*/

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
            level.getStartStateMachineCounter() == 2
    }*/
}
