import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import org.apache.groovy.groovysh.Groovysh
import org.ldts.pacman.gui.GUIForLanterna
import org.ldts.pacman.models.game.Position
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

    /*def "We should execute the specific draw for our element"() {
        given:
            Position position = GroovyMock(Position)
            TextColor.ANSI textColor = GroovyMock(TextColor.ANSI)
            String drawSymbol = GroovyMock(String)
        when:
            lanternaGUI.drawEntity(position, textColor, drawSymbol)
        then:
            1 * lanternaGUI.drawElement(position, textColor, drawSymbol)
    }*/

    /*def "writeToScreenMethod should execute all its methods"() {
        given:
            def position = GroovyMock(Position)
            def text = GroovyMock(String)
            def color = GroovyMock(TextColor.ANSI)
            def screen = GroovyMock(Screen)
        when:
            lanternaGUI.writeToScreen(position, text, color);
        then:
            1 * screen.newTextGraphics()
    }*/

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
}