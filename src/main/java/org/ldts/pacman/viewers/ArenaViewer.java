package org.ldts.pacman.viewers;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.*;

import java.io.IOException;
import java.util.List;

public class ArenaViewer extends Viewer<Arena> {
    public ArenaViewer(Arena arena) {
        super(arena);
    }

    @Override
    protected void drawEntities(GUI gui) {
        for(Wall wall: getModel().getWalls()) {
            new EntityViewer(wall).drawEntities(gui);
        }

        for(RegularGhost ghost: getModel().getRegularGhosts()) {
            new EntityViewer(ghost).drawEntities(gui);
        }

        for(FixedEdible fixedEdible: getModel().getFixedEdibles()) {
            new EntityViewer(fixedEdible).drawEntities(gui);
        }

        new EntityViewer(getModel().getPacman()).drawEntities(gui);

        gui.writeToScreen(new Position(0, 0), "Score: " + getModel().getScore(), TextColor.ANSI.WHITE);
        gui.writeToScreen(new Position(getModel().getWidth() - 10, 0), "Lives: ", TextColor.ANSI.WHITE);
    }

    private void delegateToViewer(GUI gui, EntityViewer viewer) throws IOException {
        viewer.draw(gui);
    }
}
