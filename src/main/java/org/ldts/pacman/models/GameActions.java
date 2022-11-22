package org.ldts.pacman.models;

public class GameActions {
    public enum GhostCollisionWithPacman {
       KILL_PACMAN,
       KILL_GHOST,
    }

    public enum ControlActions {
        MOVE_LEFT,
        MOVE_RIGHT,
        MOVE_UP,
        MOVE_DOWN,
        EXIT,
        NONE,
    }
}
