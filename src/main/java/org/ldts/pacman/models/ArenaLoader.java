package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// Ler o mapa e carregar as entidades respetivas para a lista da arena
public class ArenaLoader {
    private final String mapToLoad;
    private int width;
    private int height;

    public ArenaLoader(String mapToLoad) {
       this.mapToLoad = mapToLoad;
       this.width = 20;
       this.height = 20;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Ler o mapa de um fichiero e carregar as entidades corretas para a lista
    public void loadTo(Pacman pacman, List<Wall> walls, List<Ghost> ghosts, List<FixedEdible> fixedEdibles) throws IOException {
        // Alterar isto pela leitura de ficheiros
        for(int i = 0; i < width; i++) {
            walls.add(new Wall(new Position(i, 0)));
            walls.add(new Wall(new Position(i, height - 1)));
            walls.add(new Wall(new Position(0, i)));
            walls.add(new Wall(new Position(width - 1, i)));
        }
    }

}
