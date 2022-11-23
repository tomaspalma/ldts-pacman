import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.bundle.LanternaThemes
import org.ldts.pacman.gui.GUI
import org.ldts.pacman.gui.GUIForLanterna
import org.ldts.pacman.models.Position
import spock.lang.Specification

class GUIForLanternaTest extends Specification {
    private def lanternaGUI

    def setup() {
        lanternaGUI = new GUIForLanterna(200, 200)
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
            lanternaGUI.getTerminalSize() == new TerminalSize(200, 200)
    }

    def "We should execute the specific draw for our element"() {
        given:
            Position position = GroovyMock(Position)
            TextColor.ANSI textColor = GroovyMock(TextColor.ANSI)
            String drawSymbol = GroovyMock(String)
        when:
            lanternaGUI.drawEntity(position, textColor, drawSymbol)
        then:
            1 * lanternaGUI.drawElement(position, textColor, drawSymbol)
    }

    def "writeToScreenMethod should execute all its methods"() {
        given:
            def position = GroovyMock(Position)
            def text = GroovyMock(String)
            def color = GroovyMock(TextColor.ANSI)
            def screen = GroovyMock(Screen)
        when:
            lanternaGUI.writeToScreen(position, text, color);
        then:
            1 * screen.newTextGraphics()
    }

}