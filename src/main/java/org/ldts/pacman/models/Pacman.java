package org.ldts.pacman.models;
import com.googlecode.lanterna.TextColor;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class Pacman extends MovableEntity {
    private HashMap<String, Integer> orientationIndexCorrespondence = new HashMap<>();
    private static final String[] drawSymbols = {"D", "C", "A", "B"};

    public Pacman(Position position) {
        super(position);
        // Aqui depois temos que ver qual é a orientação padrão que o pacman irá ter (se é que existirá uma)
        this.drawSymbol = drawSymbols[2];
        this.color = TextColor.ANSI.YELLOW_BRIGHT;
        orientationIndexCorrespondence.put("UP", 0);
        orientationIndexCorrespondence.put("DOWN", 1);
        orientationIndexCorrespondence.put("LEFT", 2);
        orientationIndexCorrespondence.put("RIGHT", 3);
    }

    // Este método vai ser chamado pelo respetivo controller
    public void changeOrientation(String newOrientation) {
        this.drawSymbol = drawSymbols[orientationIndexCorrespondence.get(newOrientation)];
    }
}
