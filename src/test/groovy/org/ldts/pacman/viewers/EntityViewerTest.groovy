package org.ldts.pacman.viewers

import com.googlecode.lanterna.TextColor
import org.ldts.pacman.gui.GUI
import org.ldts.pacman.models.Pacman
import org.ldts.pacman.models.Position
import spock.lang.Specification

class EntityViewerTest extends Specification {
    private def entityViewer
    private def position

    def setup() {
        position = new Position(5, 5)
        entityViewer = new EntityViewer(new Pacman(position))
    }

    def "DrawEntities should execute its methods"() {
        given:
            def gui = Mock(GUI)
            def color = TextColor.ANSI.YELLOW_BRIGHT
            def drawSymbol = "A"
        when:
            entityViewer.drawEntities(gui, position, color, drawSymbol)
        then:
            1 * gui.drawEntity(position, color, drawSymbol);
    }
}
