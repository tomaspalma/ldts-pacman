package org.ldts.pacman.models;

import java.util.ArrayList;
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
