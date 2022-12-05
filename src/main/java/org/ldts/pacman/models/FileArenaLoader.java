package org.ldts.pacman.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

// Ler o mapa e carregar as entidades respetivas para a lista da arena
public class FileArenaLoader extends ArenaLoader {
    private final String mapToLoad;
    private final int width;
    private final int height;
    private final URL mapResource;
    private final BufferedReader mapFileReader;

    public FileArenaLoader(Arena arena, String mapToLoad) throws FileNotFoundException {
        super(arena);
        this.mapToLoad = mapToLoad;
        this.width = 20;
        this.height = 20;
        this.mapResource = getClass().getClassLoader().getResource(mapToLoad);
        assert mapResource != null;
        assert mapResource != null;
        this.mapFileReader = new BufferedReader(new FileReader(mapResource.getFile()));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    // Ler o mapa de um fichiero e carregar as entidades corretas para a lista
    public void load() throws IOException {
        String currentCharRow;
        Position currentPosition;
        int x = 0;
        int y = 1;

        while((currentCharRow = this.mapFileReader.readLine()) != null) {
            //if(currentCharRow.length() >= width - 1) return;

            this.arena.getGameGrid().add(new ArrayList<>());

            for(Character c: currentCharRow.toCharArray()) {
                addRespectiveElementOf(c, new Position(x, y, this.arena));
                x += 1;
            }

            y += 1;
            x = 0;
        }

        addDependenciesToEntities();
    }

    private void addDependenciesToEntities() {
        addRegularGhostsObserversToPowerPellet();
    }

    private void addRegularGhostsObserversToPowerPellet() {
        for(FixedEdible fixedEdible: this.arena.getGeneralFixedEdibleList()) {                
            if(fixedEdible instanceof PowerPellet) {
                for(RegularGhost regularGhost: this.arena.getRegularGhostsList()) {
                    ((PowerPellet) fixedEdible).addObserver(regularGhost);
                }
            }
        }
    }

    private void addRespectiveElementOf(Character character, Position currentPosition) {
        switch(character) {
            case 'W': loadObstacle(new Wall(currentPosition)); break;
            case 'P': loadPacmanAt(currentPosition); break;
            case 'o': loadFixedEdible(new Pacdot(currentPosition)); break;
            case 'O': loadFixedEdible(new PowerPellet(currentPosition)); break;
            case 'C': loadFixedEdible(new Cherry(currentPosition)); break;
            case 'p': loadRegularGhost(new Pinky(currentPosition)); break;
            case 'c': loadRegularGhost(new Clyde(currentPosition)); break;
            case 'i': loadRegularGhost(new Inky(currentPosition)); break;
            case 'b': loadRegularGhost(new Blinky(currentPosition)); break;
            case ' ': loadEmptySpace(new EmptySpace(currentPosition)); break;
            default: break;
        }
    }

    @Override
    protected void loadObstacle(Obstacle obstacle) {
        this.arena.addObstacle(obstacle);
        this.addToGrid(obstacle);
    }

    @Override
    protected void loadPacmanAt(Position position) {
        Pacman pacman = new Pacman(position);
        this.arena.setPacman(pacman);

        this.addToGrid(pacman);

        this.setPacmanStartPosition(position);
    }

    @Override
    protected void setPacmanStartPosition(Position position) {
        this.arena.setStartPacmanPosition(position);
    }

    @Override
    protected void loadRegularGhost(RegularGhost ghost) {
        this.arena.addRegularGhost(ghost);
        this.addToGrid(ghost);
    }

    @Override
    protected void loadFixedEdible(FixedEdible fixedEdible) {
        this.arena.getGeneralFixedEdibleList().add(fixedEdible);
        this.addToGrid(fixedEdible);
    }

    private void addToGrid(Entity entity) {
        this.arena.gameGrid.get(entity.getPosition().getY() - 1).add(new Tile(entity.getPosition()));
        this.arena.gameGrid.get(entity.getPosition().getY() - 1).get(entity.getPosition().getX())
            .addChild(entity);
    }

    private void loadEmptySpace(EmptySpace emptySpace) {
        this.addToGrid(emptySpace);
        this.arena.incrementGhostHouseSize();
    }
}
