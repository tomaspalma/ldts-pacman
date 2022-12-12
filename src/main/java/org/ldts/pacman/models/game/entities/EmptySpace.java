package org.ldts.pacman.models.game.entities;

import org.ldts.pacman.models.game.Position;

public class EmptySpace extends FixedEntity {
    public EmptySpace(Position position) {
        super(position);
        this.drawSymbol = " ";
    }
}
