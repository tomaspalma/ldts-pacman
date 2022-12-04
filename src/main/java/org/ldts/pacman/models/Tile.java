package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private List<Entity> childrenEntities;
    private Position position;

    public Tile(Position position) {
        this.childrenEntities = new ArrayList<>();
        this.position = position;
    }

    public List<Entity> getChildrenEntities() {
        return this.childrenEntities;
    }

    public void addChild(Entity child) {
        this.childrenEntities.add(child);
    }

    public void removeChild(Entity child) {
        childrenEntities.remove(child);
        
        if(childrenEntities.isEmpty()) childrenEntities.add(new EmptySpace(position));
    }

    public boolean containsFixedEdible() {
        for(Entity entity: childrenEntities) {
            if(entity instanceof FixedEdible) return true;
        }

        return false;
    }

    public boolean containsGhost() {
        for(Entity entity: childrenEntities) {
            if(entity instanceof Ghost) return true;
        }

        return false;
    }

    public boolean containsObstacle() {
        for(Entity entity: childrenEntities) {
            if(entity instanceof Obstacle) return true;
        }

        return false;
    }

    public boolean containsPacman() {
        for(Entity entity: childrenEntities) {
            if(entity instanceof Pacman) return true;
        }

        return false;
    }

    public boolean containsGate() {
        for(Entity entity: childrenEntities) {
            if(entity instanceof GhostHouseGate) return true;
        }

        return false;
    }

    public FixedEdible getFixedEdible() {
        for(Entity entity: childrenEntities) {
            if(entity instanceof FixedEdible) return (FixedEdible) entity;
        }

        return null;
    }

    public Ghost getGhost() {
        for(Entity entity: childrenEntities) {
            if(entity instanceof Ghost) return (Ghost) entity;
        }

        return null;
    }

    public Pacman getPacman() {
        for(Entity entity: childrenEntities) {
            if(entity instanceof Ghost) return (Pacman) entity;
        }

        return null;
    }
}
