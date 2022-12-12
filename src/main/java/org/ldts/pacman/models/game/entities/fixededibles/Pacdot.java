package org.ldts.pacman.models.game.entities.fixededibles;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.Position;

public class Pacdot extends FixedEdible {
    public Pacdot(Position position, Arena arena) {
        super(position, arena);
        this.drawSymbol = ".";
        this.color = TextColor.ANSI.YELLOW;
        this.points = 10;
    }
}
