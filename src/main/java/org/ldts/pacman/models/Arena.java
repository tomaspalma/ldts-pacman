package org.ldts.pacman.models;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final int height;
    private final int width;
    private List<Entity> entitiesToDraw;
    private ArenaLoader loader;
    private int score;

    public Arena(int width, int height, String mapToLoad){
        this.width = width;
        this.height = height;
        this.entitiesToDraw = new ArrayList<>();
        entitiesToDraw.add(new Pacman(new Position(20, 20)));
        entitiesToDraw.add(new Wall(new Position(0, 0)));
        this.loader = new ArenaLoader(mapToLoad);
        loader.load(entitiesToDraw);
    }

    public List<Entity> getEntitiesToDraw() {
        return entitiesToDraw;
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
}