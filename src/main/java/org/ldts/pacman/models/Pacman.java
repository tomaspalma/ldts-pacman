package org.ldts.pacman.models;

import java.util.HashMap;

public class Pacman extends MovableEntity {
    private HashMap<String, Integer> orientationIndexCorrespondence;
    private static final String[] drawSymbols = {"A", "B", "C", "D"};

    public Pacman(Position position) {
        super(position);
        // Aqui depois temos que ver qual é a orientação padrão que o pacman irá ter (se é que existirá uma)
        this.drawSymbol = drawSymbols[0];
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
