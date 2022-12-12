package org.ldts.pacman.models.game.entities.fixededibles;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.game.Position;

public class Cherry extends FixedEdible {
    public Cherry(Position position) {
        super(position);
        this.color = TextColor.ANSI.RED;
        this.drawSymbol = ",";
    }
}
