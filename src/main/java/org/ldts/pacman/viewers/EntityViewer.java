package org.ldts.pacman.viewers;

import org.ldts.pacman.gui.GUI;
import org.ldts.pacman.models.Entity;

public interface EntityViewer<T extends Entity> {
    void draw(T entity, GUI gui);
}
