package org.ldts.pacman.models.game.entities.fixededibles;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.Position;

public class Cherry extends FixedEdible {
    public Cherry(Position position, Arena arena) {
        super(position, arena);
        this.color = TextColor.ANSI.RED;
        this.drawSymbol = ",";
        this.points = 100;
    }
}
