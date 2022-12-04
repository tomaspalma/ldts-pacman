package org.ldts.pacman.models;

public class AgressiveChaseStrategy implements ChaseStrategy {

    @Override
    public void execute(Ghost ghost) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Position getNextPosition(Ghost ghost) {
        // TODO Auto-generated method stub
        return new Position(0, 0, ghost.getPosition().getArena());
    }
    
}
