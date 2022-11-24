package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final int height;
    private final int width;

    private Pacman pacman;
    private List<Wall> walls = new ArrayList<>();
    private List<Ghost> ghosts = new ArrayList<>();
    private List<FixedEdible> fixedEdibles = new ArrayList<>();
    private ArenaLoader loader;
    private int score;

    public Arena(int width, int height, String mapToLoad){
        this.width = width;
        this.height = height;
        this.loader = new ArenaLoader(mapToLoad);
        pacman = new Pacman(new Position(5, 5));
        loader.loadTo(pacman, walls, ghosts, fixedEdibles);
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public List<FixedEdible> getFixedEdibles() {
        return fixedEdibles;
    }

    public ArenaLoader getLoader() {
        return loader;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
       this.score = score;
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
