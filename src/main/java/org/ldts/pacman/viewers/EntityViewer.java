package org.ldts.pacman.viewers;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.game.entities.Entity;
import org.ldts.pacman.models.game.Position;

public class EntityViewer extends Viewer<Entity> {

    protected EntityViewer(Entity model) {
        super(model);
    }

    @Override
    protected void drawEntities(GUI gui) {
        gui.drawEntity(this.getModel().getPosition(), this.getModel().getColor(), this.getModel().getDrawSymbol());
    }

    protected void drawEntities(GUI gui, Position position, TextColor.ANSI color, String drawSymbol) {
        gui.drawEntity(this.getModel().getPosition(), this.getModel().getColor(), this.getModel().getDrawSymbol());
    }
}