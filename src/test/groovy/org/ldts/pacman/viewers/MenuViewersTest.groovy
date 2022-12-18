package org.ldts.pacman.viewers

import com.googlecode.lanterna.TextColor
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.viewers.menus.RegularMenuViewer
import spock.lang.Specification
import org.ldts.pacman.models.menus.Menu
import org.ldts.pacman.gui.GUI

class MenuViewersTest extends Specification {
    private def gui

    def setup() {
        gui = Mock(GUI.class)
    }

    def "regular viewer"() {
        given:
        def menu = Mock(Menu.class)
        def menuViewer = new RegularMenuViewer(menu)

        when:
        menuViewer.drawEntities(gui)

        then:
        1 * gui.writeToScreen(_ as Position, menu.getTitle(), menu.getColor())
        menu.getNumberOptions() * menu.getOption(_)
        menu.getNumberOptions() * gui.writeToScreen(_ as Position, _ as String, _ as TextColor.ANSI)
    }
}
