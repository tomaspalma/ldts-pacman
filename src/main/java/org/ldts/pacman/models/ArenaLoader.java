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
public class ArenaLoader {
    private Arena arena;
    private final String mapToLoad;
    private int width;
    private int height;
    private URL mapResource;
    private BufferedReader mapFileReader;

    public ArenaLoader(Arena arena, String mapToLoad) throws FileNotFoundException {
        this.mapToLoad = mapToLoad;
        this.width = 20;
        this.height = 20;
        this.arena = arena;
        this.mapResource = getClass().getClassLoader().getResource(mapToLoad);
        this.mapFileReader = new BufferedReader(new FileReader(mapResource.getFile()));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Ler o mapa de um fichiero e carregar as entidades corretas para a lista
    public void load() throws IOException {
        String currentRow;
        Position currentPosition;

        int x = 0;
        int y = 0;

        while((currentRow = this.mapFileReader.readLine()) != null) {
            //if(currentRow.length() >= width - 1) return;


            for(Character c: currentRow.toCharArray()) {
                currentPosition = new Position(x, y);

                addRespectiveElementOf(c, new Position(x, y));

                x += 1;
            }

            y += 1;
            x = 0;

            System.out.println(x + ", " + y);
        }
    }

    private void addRespectiveElementOf(Character character, Position currentPosition) {

        switch(character) {
            case 'W': loadWallAt(currentPosition); break;
            case 'P': loadPacmanAt(currentPosition); break;
            case 'o': loadPacdotAt(currentPosition); break;
            case 'O': loadPowerPelletAt(currentPosition); break;
            case 'R': loadRegularGhostAt(currentPosition); break;
            case 'C': loadCherryAt(currentPosition); break;
            default: break;
        }
    }

    private void loadWallAt(Position position) {
        this.arena.addWall(new Wall(position));
    }

    private void loadPacmanAt(Position position) {
        if(this.arena.getPacman() == null) this.arena.setPacman(new Pacman(position));
    }

    private void loadRegularGhostAt(Position position) {
        this.arena.addGhost(new RegularGhost(position));
    }

    private void loadCherryAt(Position position) {
        this.arena.addFixedEdible(new Cherry(position));
    }

    private void loadPacdotAt(Position position) {
        this.arena.addFixedEdible(new Pacdot(position));
    }

    private void loadPowerPelletAt(Position position) {
        this.arena.addFixedEdible(new PowerPellet(position));
    }

}
