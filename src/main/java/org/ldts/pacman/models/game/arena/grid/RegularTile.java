package org.ldts.pacman.models.game.arena.grid;

import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.Position;
import org.ldts.pacman.models.game.entities.Entity;

import java.util.List;

public class RegularTile extends Tile {
    public RegularTile(Position position, Arena arena) {
        super(position, arena);
    }
    
    public List<Entity> getChildrenEntities() {
        return this.childrenEntities;
    }

    @Override
    public void put(Entity child) {
        this.childrenEntities.add(child);
    }
}
