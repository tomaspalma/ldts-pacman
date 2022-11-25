package org.ldts.pacman.models;

import java.io.IOException;
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

    public Arena(int width, int height, String mapToLoad) throws IOException {
        this.width = width;
        this.height = height;
        this.loader = new FileArenaLoader(this, mapToLoad);
        loader.load();
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

    public void addWall(Wall wall) {
        this.walls.add(wall);
    }
    public void addGhost(Ghost ghost) {
        this.ghosts.add(ghost);
    }
    public void addFixedEdible(FixedEdible edible) {
        this.fixedEdibles.add(edible);
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

    public boolean isWallAt(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position))
                return true;
        }

        return false;
    }

    public int getFixedEdibleAt(Position position) {
        for(int i = 0; i < fixedEdibles.size(); i++) {
            if(fixedEdibles.get(i).getPosition().equals(position)) return i;
        }

        return -1;
    }

    // TODO
    public boolean isGhostAt(Position position) {
        return false;
    }
}
