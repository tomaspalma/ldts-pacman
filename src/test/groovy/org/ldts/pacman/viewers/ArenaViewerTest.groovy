package org.ldts.pacman.viewers

import com.googlecode.lanterna.TextColor
import org.ldts.pacman.gui.GUI
import org.ldts.pacman.models.game.arena.Arena
import org.ldts.pacman.models.game.Position
import org.ldts.pacman.models.game.entities.Obstacle
import org.ldts.pacman.models.game.entities.fixededibles.FixedEdible
import org.ldts.pacman.models.game.entities.ghost.regularghost.RegularGhost
import spock.lang.Specification

class ArenaViewerTest extends Specification {
    private def viewer
    private def arena
    private def gui

    def setup() {
        arena = new Arena(20, 21, "maps/testmap.txt")
        viewer = new ArenaViewer(arena)
        gui = Mock(GUI.class)
    }

    def "Upon execution of drawEntities(GUI gui) arena viewer should draw every single drawable thing in the arena, as well as display score and remaining lives"() {
        when:
        viewer.drawEntities(gui)
        then:
        1 * gui.drawEntity(arena.getPacman().getStartPosition(), arena.getPacman().getColor(), arena.getPacman().getDrawSymbol())
        1 * gui.drawEntity(arena.getGhostHouse().getGatePosition(), arena.getGhostHouse().getGhostHouseGate().getColor(), "-")
        for (FixedEdible e : arena.getGeneralFixedEdibleList()) {
            1 * gui.drawEntity(e.getPosition(), e.getColor(), e.getDrawSymbol())
        }
        for (Obstacle o : arena.getObstaclesList()) {
            1 * gui.drawEntity(o.getPosition(), o.getColor(), o.getDrawSymbol())
        }
        for (RegularGhost g : arena.getRegularGhostsList()) {
            1 * gui.drawEntity(g.getPosition(), g.getColor(), g.getDrawSymbol())
        }
        1 * gui.writeToScreen(new Position(0, 0), "Score " + arena.getScore(), TextColor.ANSI.WHITE)
        1 * gui.writeToScreen(new Position(arena.getWidth() - 10, 0), "Lives " + arena.getPacman().getRemainingLives(), TextColor.ANSI.WHITE)
    }
}