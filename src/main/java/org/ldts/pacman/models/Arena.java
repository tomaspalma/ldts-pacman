package org.ldts.pacman.models;

import org.ldts.pacman.GameLevel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final int height;
    private final int width;
    private int ghostHouseSize = 0;
    private GhostHouse ghostHouse;
    private Pacman pacman;
    private List<Obstacle> obstaclesList = new ArrayList<>();
    private final List<List<Tile>> gameGrid = new ArrayList<>();
    private final List<GameLevel> levels = new ArrayList<>();
    private Position startPacmanPosition;
    private int ateGhostsPoints = 200;
    
    private final List<RegularGhost> regularGhostsList = new ArrayList<>();

    private final List<FixedEdible> generalFixedEdibleList = new ArrayList<>();

    private final MapArenaLoader loader;

    private int score = 0;

    public Arena(int width, int height, String mapToLoad) throws IOException {
        this.width = width;
        this.height = height;
        this.loader = new FileMapArenaLoader(this, mapToLoad);

        loader.load();
    }

    public void createGhostHouse(Position position, int width, int height) {
       this.ghostHouse = new GhostHouse(position, width, height);
    }

    public GhostHouse getGhostHouse() {
        return this.ghostHouse;
    }

    public List<GameLevel> getLevels() {
        return levels;
    }

    public void addToGeneralFixedEdibleList(FixedEdible fixedEdible) {
        this.generalFixedEdibleList.add(fixedEdible);
    }

    public void incrementGhostHouseSize() {
        this.ghostHouseSize += 1;
    }

    public int getGhostHouseSize() {
        return this.ghostHouseSize;
    }

    public List<List<Tile>> getGameGrid() {
        return gameGrid;
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

    public MapArenaLoader getLoader() {
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

    public void removeFromGameGridAt(Position position, Entity entity) {
        RegularTile regularTile = (RegularTile) gameGrid.get(position.getY() - 1).get(position.getX());

        regularTile.removeChild(entity);
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
