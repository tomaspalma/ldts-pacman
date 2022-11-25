package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

// Ler o mapa e carregar as entidades respetivas para a lista da arena
public class FileArenaLoader extends ArenaLoader {
    private final String mapToLoad;
    private int width;
    private int height;
    private URL mapResource;
    private BufferedReader mapFileReader;

    public FileArenaLoader(Arena arena, String mapToLoad) throws FileNotFoundException {
        super(arena);
        this.mapToLoad = mapToLoad;
        this.width = 20;
        this.height = 20;
        this.mapResource = getClass().getClassLoader().getResource(mapToLoad);
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
        String currentRow;

        int x = 0;
        int y = 1;

        while((currentRow = this.mapFileReader.readLine()) != null) {
            //if(currentRow.length() >= width - 1) return;

            for(Character c: currentRow.toCharArray()) {
                addRespectiveElementOf(c, new Position(x, y));

                x += 1;
            }

            y += 1;
            x = 0;
        }
    }

    private void addRespectiveElementOf(Character character, Position currentPosition) {
        switch(character) {
            case 'W': loadWallAt(currentPosition); break;
            case 'P': loadPacmanAt(currentPosition); break;
            case 'o': loadPacdotAt(currentPosition); break;
            case 'O': loadPowerPelletAt(currentPosition); break;
            case 'p': loadGhost(new Pinky(currentPosition)); break;
            case 'c': loadGhost(new Clyde(currentPosition)); break;
            case 'i': loadGhost(new Inky(currentPosition)); break;
            case 'b': loadGhost(new Blinky(currentPosition)); break;
            case 'C': loadCherryAt(currentPosition); break;
            default: break;
        }
    }

    @Override
    protected void loadWallAt(Position position) {
        this.arena.addWall(new Wall(position));
    }

    @Override
    protected void loadPacmanAt(Position position) {
        if(this.arena.getPacman() == null) this.arena.setPacman(new Pacman(position));
    }

    @Override
    protected void loadGhost(Ghost ghost) {
        this.arena.addGhost(ghost);
    }

    @Override
    protected void loadCherryAt(Position position) {
        this.arena.addFixedEdible(new Cherry(position));
    }

    @Override
    protected void loadPacdotAt(Position position) {
        this.arena.addFixedEdible(new Pacdot(position));
    }

    @Override
    protected void loadPowerPelletAt(Position position) {
        this.arena.addFixedEdible(new PowerPellet(position));
    }

}
