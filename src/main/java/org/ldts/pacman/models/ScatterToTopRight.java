package org.ldts.pacman.models;

public class ScatterToTopRight implements ScatterStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        // TODO Auto-generated method stub
        return new Position(4, 4, ghost.getArena());
    }
    
}
