package org.ldts.pacman.models;

import javax.swing.plaf.metal.MetalIconFactory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

// Ler o mapa e carregar as entidades respetivas para a lista da arena
public class FileArenaLoader extends ArenaLoader {
    private final String mapToLoad;
    private final int width;
    private final int height;
    private final URL mapResource;
    private final BufferedReader mapFileReader;
    private final HashMap<Character, TeletransporterTile> possibleLetterToCorrespondence = new HashMap<>();

    public FileArenaLoader(Arena arena, String mapToLoad) throws FileNotFoundException {
        super(arena);
        this.mapToLoad = mapToLoad;
        this.width = 20;
        this.height = 20;
        this.mapResource = getClass().getClassLoader().getResource(mapToLoad);
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
            case 'W': loadObstacle(new Wall(currentPosition, this.arena)); break;
            case 'P': loadPacmanAt(currentPosition); break;
            case 'o': loadFixedEdible(new Pacdot(currentPosition, this.arena)); break;
            case 'O': loadFixedEdible(new PowerPellet(currentPosition, this.arena)); break;
            case 'C': loadFixedEdible(new Cherry(currentPosition, this.arena)); break;
            case 'p': loadRegularGhost(new Pinky(currentPosition, this.arena)); break;
            case 'c': loadRegularGhost(new Clyde(currentPosition, this.arena)); break;
            case 'i': loadRegularGhost(new Inky(currentPosition, this.arena)); break;
            case 'b': loadRegularGhost(new Blinky(currentPosition, this.arena)); break;
            case 'G': loadGate(new GhostHouseGate(currentPosition, this.arena)); break;
            case ' ': loadEmptySpace(new EmptySpace(currentPosition, this.arena)); break;
            default: addTeletransporterToMap(character, currentPosition); break;
        }
    }

    private void addTeletransporterToMap(Character character, Position positionToAddTo) {
        boolean notYetFoundTeletransporterPair = !(possibleLetterToCorrespondence.containsKey(character));

        if(notYetFoundTeletransporterPair) {
            TeletransporterTile firstElementOfPair = new TeletransporterTile(positionToAddTo, this.arena);
            possibleLetterToCorrespondence.put(character, firstElementOfPair);
            this.arena.getGameGrid().get(positionToAddTo.getY() - 1).add(firstElementOfPair);
        } else {
            TeletransporterTile firstElementOfPair = possibleLetterToCorrespondence.get(character);
            TeletransporterTile secondElementOfPair = new TeletransporterTile(positionToAddTo, this.arena);

            firstElementOfPair.setExitTile(secondElementOfPair);
            secondElementOfPair.setExitTile(firstElementOfPair);

            this.arena.getGameGrid().get(positionToAddTo.getY() - 1).add(secondElementOfPair);

            possibleLetterToCorrespondence.remove(character);
        }
    }

    @Override
    protected void loadObstacle(Obstacle obstacle) {
        this.arena.addObstacle(obstacle);
        this.addEntityToGrid(obstacle);
    }

    @Override
    protected void loadPacmanAt(Position position) {
        Pacman pacman = new Pacman(position, this.arena);
        this.arena.setPacman(pacman);

        this.addEntityToGrid(pacman);

        this.setPacmanStartPosition(position);
    }

    @Override
    protected void setPacmanStartPosition(Position position) {
        this.arena.setStartPacmanPosition(position);
    }

    @Override
    protected void loadRegularGhost(RegularGhost ghost) {
        this.arena.addRegularGhost(ghost);
        this.addEntityToGrid(ghost);
    }

    @Override
    protected void loadFixedEdible(FixedEdible fixedEdible) {
        this.arena.getGeneralFixedEdibleList().add(fixedEdible);
        this.addEntityToGrid(fixedEdible);
    }

    private void addEntityToGrid(Entity entity) {
        this.arena.gameGrid.get(entity.getPosition().getY() - 1).add(new RegularTile(entity.getPosition(), this.arena));
        this.arena.gameGrid.get(entity.getPosition().getY() - 1).get(entity.getPosition().getX())
            .put(entity);
    }

    private void loadEmptySpace(EmptySpace emptySpace) {
        this.addEntityToGrid(emptySpace);
        this.arena.incrementGhostHouseSize();
    }

    private void loadGate(GhostHouseGate gate) {
        this.addEntityToGrid(gate);
    }
}
