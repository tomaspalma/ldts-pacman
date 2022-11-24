package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;

import java.util.List;

// Ler o mapa e carregar as entidades respetivas para a lista da arena
public class ArenaLoader {
    private final String mapToLoad;
    private int width;
    private int height;

    public ArenaLoader(String mapToLoad) {
       this.mapToLoad = mapToLoad;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Ler o mapa de um fichiero e carregar as entidades corretas para a lista
    public void loadTo(Pacman pacman, List<Wall> walls, List<Ghost> ghosts, List<FixedEdible> fixedEdibles) {

    }

}
