package org.ldts.pacman.models.game.arena.loaders.map;

import org.ldts.pacman.models.game.*;
import org.ldts.pacman.models.game.arena.ghosthouse.GhostHouse;
import org.ldts.pacman.models.game.arena.ghosthouse.GhostHouseGate;
import org.ldts.pacman.models.game.arena.grid.RegularTile;
import org.ldts.pacman.models.game.arena.grid.TeletransporterTile;
import org.ldts.pacman.models.game.entities.EmptySpace;
import org.ldts.pacman.models.game.entities.Entity;
import org.ldts.pacman.models.game.entities.Obstacle;
import org.ldts.pacman.models.game.entities.ghost.regularghost.*;
import org.ldts.pacman.models.game.entities.obstacles.Wall;
import org.ldts.pacman.models.game.entities.fixededibles.Cherry;
import org.ldts.pacman.models.game.entities.fixededibles.FixedEdible;
import org.ldts.pacman.models.game.entities.fixededibles.Pacdot;
import org.ldts.pacman.models.game.entities.fixededibles.PowerPellet;
import org.ldts.pacman.models.game.entities.pacman.Pacman;
import org.ldts.pacman.models.game.arena.Arena;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

// Ler o mapa e carregar as entidades respetivas para a lista da arena
public class FileMapArenaLoader extends MapArenaLoader {
    private final int width;
    private final int height;
    private final URL mapResource;
    private final BufferedReader mapFileReader;
    private final HashMap<Character, TeletransporterTile> possibleLetterToCorrespondence = new HashMap<>();

    public FileMapArenaLoader(Arena arena, String mapToLoad) throws IOException {
        super(arena);
        this.width = 20;
        this.height = 20;

        this.mapResource = getClass().getClassLoader().getResource(mapToLoad);
        assert mapResource != null;

        this.mapFileReader = Files.newBufferedReader(Paths.get(mapResource.getFile()), UTF_8);

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
        int x = 0;
        int y = 1;

        createGhostHouse();

        while((currentCharRow = this.mapFileReader.readLine()) != null) {
            this.arena.getGameGrid().add(new ArrayList<>());

            for(int i = 0; i < currentCharRow.length(); i++) {
                char c = currentCharRow.charAt(i);
                addRespectiveElementOf(c, new Position(x, y));
                x += 1;
            }

            y += 1;
            x = 0;
        }

        addDependenciesToEntities();
    }

    private void createGhostHouse() throws IOException {
        String positionCoordinates = this.mapFileReader.readLine();

        String[] specialLimitParemeters = positionCoordinates.split("x");
        int ghostHouseX = Integer.parseInt(specialLimitParemeters[0]);
        int ghostHouseY = Integer.parseInt(specialLimitParemeters[1]);
        Position ghostHousePosition = new Position(ghostHouseX, ghostHouseY);

        String widthParameters = this.mapFileReader.readLine();
        specialLimitParemeters = widthParameters.split("x");
        int ghostHouseWidth = Integer.parseInt(specialLimitParemeters[0]);
        int ghostHouseHeight = Integer.parseInt(specialLimitParemeters[1]);

        this.arena.createGhostHouse(ghostHousePosition, ghostHouseWidth, ghostHouseHeight);
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
        boolean notYetFoundTeletransporterPair = !possibleLetterToCorrespondence.containsKey(character);

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
        List<FixedEdible> generalFixedEdibleList = this.arena.getGeneralFixedEdibleList();
        generalFixedEdibleList.add(fixedEdible);

        this.addEntityToGrid(fixedEdible);
    }

    private void addEntityToGrid(Entity entity) {
        this.arena.getGameGrid().get(entity.getPosition().getY() - 1).add(new RegularTile(entity.getPosition(), this.arena));
        this.arena.getGameGrid().get(entity.getPosition().getY() - 1).get(entity.getPosition().getX())
            .put(entity);
    }

    private void loadEmptySpace(EmptySpace emptySpace) {
        this.addEntityToGrid(emptySpace);
        this.arena.incrementGhostHouseSize();
    }

    private void loadGate(GhostHouseGate gate) {
        GhostHouse ghostHouse = this.arena.getGhostHouse();

        if(ghostHouse.getGhostHouseGate() != null)
            throw new IllegalArgumentException("There is a gate already defined!");

        this.arena.getGhostHouse().setGhostHouseGate(gate);
        this.addEntityToGrid(gate);
    }
}
