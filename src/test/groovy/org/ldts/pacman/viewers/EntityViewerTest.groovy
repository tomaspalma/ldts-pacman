package org.ldts.pacman.viewers

import com.googlecode.lanterna.TextColor
import org.ldts.pacman.gui.GUI
import org.ldts.pacman.models.Arena
import org.ldts.pacman.models.game.entities.pacman.Pacman
import org.ldts.pacman.models.game.Position
import spock.lang.Specification

class EntityViewerTest extends Specification {
    private def entityViewer
    private def position
    private def pacman

    def setup() {
        position = new Position(5, 5)
        pacman = new Pacman(position, new Arena(20, 21, "maps/testmap.txt"))
        entityViewer = new EntityViewer(pacman)
    }

    def "DrawEntities should execute its methods"() {
        given:
            def gui = Mock(GUI)
            def color = TextColor.ANSI.YELLOW_BRIGHT
            def drawSymbol = pacman.getDrawSymbol()
        when:
            entityViewer.drawEntities(gui, position, color, drawSymbol)
        then:
            1 * gui.drawEntity(position, color, drawSymbol)
    }
}
