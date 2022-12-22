import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.screen.Screen
import org.ldts.pacman.gui.GUIForLanterna
import spock.lang.Specification

class GUIForLanternaTest extends Specification {
    private def lanternaGUI

    def setup() {
        lanternaGUI = new GUIForLanterna(20, 20)
    }

    def "Should be able to hide cursor of gui"() {
        when:
            lanternaGUI.hideCursor()
        then:
            null == lanternaGUI.getScreen().getCursorPosition()
    }

    def "We should be able to close our screen"() {
        given:
            def screen = Mock(Screen)
        when:
            lanternaGUI.close(screen)
        then:
            1 * screen.close()
    }

    def "We should be able to get the correct lanterna terminal size"() {
        expect:
            lanternaGUI.getTerminalSize() == new TerminalSize(20, 20)
    }

    def "Should be able to refresh screen"() {
        given:
            def screen = Mock(Screen)
            def gui = new GUIForLanterna(screen)
        when:
            gui.refresh()
        then:
            1 * screen.refresh()
    }

    def "Should be able to close screen"() {
        given:
            def screen = Mock(Screen)
            def gui = new GUIForLanterna(screen)
        when:
            gui.close()
        then:
            1 * screen.close()
    }

    def "We should be able to clear our screen"() {
        given:
            def screen = Mock(Screen)
            def gui = new GUIForLanterna(screen)
        when:
            gui.clear()
        then:
            1 * screen.clear()
    }

    /*def "We should be able to invoke the correct methods in writing to screen"() {
        given:
            def screen = Mock(Screen)
            def gui = new GUIForLanterna(screen)
            def textGraphics = Mock(TextGraphics)
            screen.newTextGraphics() >> textGraphics
        when:
            gui.writeToScreen(new Position(5, 5),
                "test", TextColor.ANSI.WHITE)
        then:
            1 * textGraphics.setForegroundColor(TextColor.ANSI.WHITE)
            1 * textGraphics.putString(_, "test")
    }*/

    /*def "Draw element function should set the foreground color and put string of draw symbol"() {
        given:
            def screenMock = Stub(Screen.class)
            def tgraphicsMock = Mock(TextGraphics.class)
            screenMock.newTextGraphics() >> tgraphicsMock
            def gui = new GUIForLanterna(screenMock)
        when:
            gui.drawElement(new Position(0, 0), TextColor.ANSI.WHITE, "")
        then:
            1 * tgraphicsMock.setForegroundColor(TextColor.ANSI.WHITE)
            1 * tgraphicsMock.putString(0, 0, "")
    }*/

    /*def "Depending on the key the user inputs, it should return the correct game action"() {
        given:
            def terminalMock = Stub(Terminal.class)
            def keystrokeMock = Mock(com.googlecode.lanterna.input.KeyStroke.class)
            terminalMock.pollInput() >> keystrokeMock
            keystrokeMock.getKeyType() >> KeyType.ArrowUp
            lanternaGUI.setTerminalTo(terminalMock)
        when:
            def result = lanternaGUI.getNextUserInput()
        then:
            result == GameActions.ControlActions.MOVE_UP
    }*/

    /*def "When receiving the down arrow key it should return the game action to move down"() {
        given:
            def terminalMock = Stub(Terminal.class)
            def keystrokeMock = Mock(com.googlecode.lanterna.input.KeyStroke.class)
            terminalMock.pollInput() >> keystrokeMock
            keystrokeMock.getKeyType() >> KeyType.ArrowDown
            lanternaGUI.setTerminalTo(terminalMock)
        when:
            def result = lanternaGUI.getNextUserInput()
        then:
            result == GameActions.ControlActions.MOVE_DOWN
    }*/

    /*def "When receiving the left arrow key it should return the game action to move left"() {
        given:
            def terminalMock = Stub(Terminal.class)
            def keystrokeMock = Mock(com.googlecode.lanterna.input.KeyStroke.class)
            terminalMock.pollInput() >> keystrokeMock
            keystrokeMock.getKeyType() >> KeyType.ArrowLeft
            lanternaGUI.setTerminalTo(terminalMock)
        when:
            def result = lanternaGUI.getNextUserInput()
        then:
            result == GameActions.ControlActions.MOVE_LEFT
    }*/

    /*def "When receiving the right arrow key it should return the game action to move right"() {
        given:
            def terminalMock = Stub(Terminal.class)
            def keystrokeMock = Mock(com.googlecode.lanterna.input.KeyStroke.class)
            terminalMock.pollInput() >> keystrokeMock
            keystrokeMock.getKeyType() >> KeyType.ArrowRight
            lanternaGUI.setTerminalTo(terminalMock)
        when:
            def result = lanternaGUI.getNextUserInput()
        then:
            result == GameActions.ControlActions.MOVE_RIGHT
    }*/

    /*def "When receiving the ENTER key it should return the game action to select something"() {
        given:
            def terminalMock = Stub(Terminal.class)
            def keystrokeMock = Mock(com.googlecode.lanterna.input.KeyStroke.class)
            terminalMock.pollInput() >> keystrokeMock
            keystrokeMock.getKeyType() >> KeyType.Enter
            lanternaGUI.setTerminalTo(terminalMock)
        when:
            def result = lanternaGUI.getNextUserInput()
        then:
            result == GameActions.ControlActions.SELECT
    }*/

    /*def "When receiving the ESCAPE key it should return the game action to switch to pause main menu"() {
        given:
            def terminalMock = Stub(Terminal.class)
            def keystrokeMock = Mock(com.googlecode.lanterna.input.KeyStroke.class)
            terminalMock.pollInput() >> keystrokeMock
            keystrokeMock.getKeyType() >> KeyType.Escape
            lanternaGUI.setTerminalTo(terminalMock)
        when:
            def result = lanternaGUI.getNextUserInput()
        then:
            result == GameActions.ControlActions.SWITCH_TO_PAUSE_MENU
    }*/

     /*def "When receiving the 'q' char exit the game"() {
        given:
            def terminalMock = Stub(Terminal.class)
            def keystrokeMock = Mock(com.googlecode.lanterna.input.KeyStroke.class)
            terminalMock.pollInput() >> keystrokeMock
            keystrokeMock.getKeyType() >> KeyType.Character
            keystrokeMock.getCharacter() >> 'q'
            lanternaGUI.setTerminalTo(terminalMock)
        when:
            def result = lanternaGUI.getNextUserInput()
        then:
            result == GameActions.ControlActions.EXIT
    }*/

    /*def "When receiving the a key that is not mapped it should return the game action NONE"() {
        given:
            def terminalMock = Stub(Terminal.class)
            def keystrokeMock = Mock(com.googlecode.lanterna.input.KeyStroke.class)
            terminalMock.pollInput() >> keystrokeMock
            keystrokeMock.getKeyType() >> KeyType.Home
            lanternaGUI.setTerminalTo(terminalMock)
        when:
            def result = lanternaGUI.getNextUserInput()
        then:
            result == GameActions.ControlActions.NONE
    }*/

}