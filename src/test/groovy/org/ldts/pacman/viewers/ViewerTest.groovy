package org.ldts.pacman.viewers

import org.ldts.pacman.gui.GUI
import org.ldts.pacman.models.game.entities.Entity
import spock.lang.Specification

class ViewerTest extends Specification {
    private def gui
    private def viewer
    private def entity

    def setup() {
        gui = Mock(GUI.class)
        entity = Mock(Entity.class)
        viewer = new EntityViewer(entity)
    }

    def "The draw function should execute all its methods"() {
        when:
           viewer.draw(gui)
        then:
            1 * gui.clear()
            1 * gui.refresh()
    }

    def "When calling the draw function of the GUI it should call the drawEntities specific function of concrete viewer"() {
        when:
            viewer.draw(gui)
        then:
            1 * gui.drawEntity(_, _, _)
    }
}
