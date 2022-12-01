package org.ldts.pacman.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final int height;
    private final int width;

    private Pacman pacman;
    private List<Obstacle> obstaclesList = new ArrayList<>();


    private Position startPacmanPosition;
    
    private final List<RegularGhost> regularGhostsList = new ArrayList<>();

    private final List<FixedEdible> generalFixedEdibleList = new ArrayList<>();

    private ArenaLoader loader;

    private int score = 0;

    public Arena(int width, int height, String mapToLoad) throws IOException {
        this.width = width;
        this.height = height;
        this.loader = new FileArenaLoader(this, mapToLoad);

        loader.load();
    }

    public Position getStartPacmanPosition() {
        return startPacmanPosition;
    }
    public List<Obstacle> getObstaclesList() {
        return this.obstaclesList;
    }

    public List<FixedEdible> getGeneralFixedEdibleList() {
        return generalFixedEdibleList;
    }

    public List<RegularGhost> getRegularGhostsList() {
        return this.regularGhostsList;
    }

    public void addObstacle(Obstacle obstacle) {
        this.obstaclesList.add(obstacle);
    }

    public void addRegularGhost(RegularGhost ghost) {
        this.regularGhostsList.add(ghost);
    }

    private void addToGeneralFixedEdibleList(FixedEdible fixedEdible) {
        this.generalFixedEdibleList.add(fixedEdible);
    }

    public ArenaLoader getLoader() {
        return loader;
    }

    public int getScore() {
        return score;
    }

    public void sumScoreWith(int increment) {
        this.score += increment;
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

    public void setObstacles(List<Obstacle> newObstaclesList) {
        this.obstaclesList = newObstaclesList;
    }

    public void setStartPacmanPosition(Position startPacmanPosition) {
        this.startPacmanPosition = startPacmanPosition;
    }

    public boolean isObstacleAt(Position position) {
        for (Obstacle obstacle: obstaclesList) {
            if (obstacle.getPosition().equals(position))
                return true;
        }

        return false;
    }

    public int getFixedEdibleAt(Position position) {
        for(int i = 0; i < generalFixedEdibleList.size(); i++) {
            if(generalFixedEdibleList.get(i).getPosition().equals(position)) return i;
        }

        return -1;
    }

    public Ghost getGhostAt(Position position) {
       for(Ghost ghost: this.getRegularGhostsList()) {
           if(ghost.getPosition().equals(position)) return ghost;
       }

       return null;
    }
}
