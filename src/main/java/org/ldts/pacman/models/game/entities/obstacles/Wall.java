package org.ldts.pacman.models.game.entities.obstacles;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.entities.Obstacle;
import org.ldts.pacman.models.game.Position;

public class Wall extends Obstacle {
    public Wall(Position position, Arena arena) {
        super(position, arena);
        this.drawSymbol = ";";
        this.color = TextColor.ANSI.BLUE;
    }
}
