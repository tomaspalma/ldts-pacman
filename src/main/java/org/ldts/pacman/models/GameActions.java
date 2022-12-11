package org.ldts.pacman.models;

public class GameActions {

    public enum GhostCollisionWithPacman {
       KILL_PACMAN,
       KILL_GHOST,
       NONE,
    }

    public enum ControlActions {
        MOVE_LEFT,
        MOVE_RIGHT,
        MOVE_UP,
        MOVE_DOWN,
        EXIT,
        SWITCH_TO_PAUSE_MENU,
        SELECT,
        NONE,
    }
}
