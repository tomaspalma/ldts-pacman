package org.ldts.pacman.models;

<<<<<<< HEAD
public abstract class Ghost extends MovableEntity {
    enum GHOST_STATE {}
=======
public abstract class Ghost extends Entity {
>>>>>>> feature/guiforlanterna
    protected GhostStrategy currentStrategy;

    protected Ghost(Position position) {
        super(position);
        currentStrategy = new AliveStrategy();
    }
}
