package org.ldts.pacman.viewers;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.game.entities.Obstacle;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.fixededibles.FixedEdible;
import org.ldts.pacman.models.game.entities.ghost.RegularGhost;
import org.ldts.pacman.models.Arena;

import java.io.IOException;

public class ArenaViewer extends Viewer<Arena> {
    public ArenaViewer(Arena arena) {
        super(arena);
    }

    @Override
    protected void drawEntities(GUI gui) {
        for(Obstacle obstacle: getModel().getObstaclesList()) {
            new EntityViewer(obstacle).drawEntities(gui);
        }

        for(FixedEdible fixedEdible: getModel().getGeneralFixedEdibleList()) {
            new EntityViewer(fixedEdible).drawEntities(gui);
        }

        for(RegularGhost ghost: getModel().getRegularGhostsList()) {
            new EntityViewer(ghost).drawEntities(gui);
        }

        new EntityViewer(getModel().getGhostHouse().getGhostHouseGate()).drawEntities(gui);

        new EntityViewer(getModel().getPacman()).drawEntities(gui);

        gui.writeToScreen(new Position(0, 0, getModel()), "Score " + getModel().getScore(), TextColor.ANSI.WHITE);
        gui.writeToScreen(new Position(getModel().getWidth() - 10, 0, getModel()), "Lives " + getModel().getPacman().getRemainingLives(), TextColor.ANSI.WHITE);
    }

    public void delegateToViewer(GUI gui, EntityViewer viewer) throws IOException {
        viewer.draw(gui);
    }
}
