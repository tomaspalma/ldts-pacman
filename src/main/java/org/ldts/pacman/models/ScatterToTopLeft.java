package org.ldts.pacman.models;

public class ScatterToTopLeft implements ScatterStrategy {
    @Override
    public Position getNextPosition(Ghost ghost) {
        // TODO Auto-generated method stub
        return new Position(4, 4, ghost.getArena());
    }
    
}
