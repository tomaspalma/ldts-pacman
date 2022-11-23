package org.ldts.pacman.models;

<<<<<<< HEAD
import java.util.HashMap;

public class Pacman extends MovableEntity {
    private HashMap<String, Integer> orientationIndexCorrespondence;
    private static final String[] drawSymbols = {"A", "B", "C", "D"};

    public Pacman(Position position) {
        super(position);
        // Aqui depois temos que ver qual é a orientação padrão que o pacman irá ter (se é que existirá uma)
        this.drawSymbol = drawSymbols[0];
=======
import com.googlecode.lanterna.TextColor;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class Pacman extends MovableEntity {
    private final HashMap<String, Integer> orientationIndexCorrespondence = new HashMap<>();
    private List<String> drawSymbols = Arrays.asList("A", "B", "C", "D");
    public Pacman(Position position) {
        super(position);
        // Aqui depois temos que ver qual é a orientação padrão que o pacman irá ter (se é que existirá uma)
        this.drawSymbol = drawSymbols.get(0);
        this.color = TextColor.ANSI.YELLOW;
>>>>>>> 456124498d166da454a8e1cf6c28def2ee831847
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
