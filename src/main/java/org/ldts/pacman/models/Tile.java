package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Tile {
    protected final Arena arena;
    protected final Position position;
    protected final List<Entity> childrenEntities;

    public Tile(Position position, Arena arena) {
        this.position = position;
        this.arena = arena;
        this.childrenEntities = new ArrayList<>();
    }

    public Position getPosition() {
        return this.position;
    }

    public Arena getArena() {
        return arena;
    }

    public void removeChild(Entity child) {
        childrenEntities.remove(child);
        if(childrenEntities.isEmpty()) childrenEntities.add(new EmptySpace(position, arena));
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

    public abstract void put(Entity child);
}
