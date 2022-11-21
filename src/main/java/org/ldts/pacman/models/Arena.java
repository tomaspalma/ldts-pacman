package org.ldts.pacman.models;

import java.util.List;

public class Arena {
    private final int height;
    private final int width;
    private Pacman pacman;

    private List<Wall> walls;
    private List<Ghost> ghosts;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void setGhosts(List<Ghost> ghosts) {
        this.ghosts = ghosts;
    }

    // TODO
    public boolean isWall(Position position) {
        return false;
    }

    // TODO
    public boolean isGhost(Position position) {
        return false;
    }
}
