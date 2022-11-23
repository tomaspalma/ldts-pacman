package org.ldts.pacman.viewers;

import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.Entity;

public class EntityViewer extends Viewer<Entity> {

    protected EntityViewer(Entity model) {
        super(model);
    }

    @Override
    protected void drawEntities(GUI gui) {
        gui.drawEntity(this.getModel().getPosition(), this.getModel().getColor(), this.getModel().getDrawSymbol());
    }
}