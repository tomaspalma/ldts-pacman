package org.ldts.pacman.models.game.entities;

import com.googlecode.lanterna.TextColor;
import org.ldts.pacman.models.animations.Animation;
import org.ldts.pacman.models.Arena;
import org.ldts.pacman.models.game.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    protected String drawSymbol;
    protected TextColor.ANSI color;
    protected Position position;
    protected final Arena arena;
    protected final List<Animation> possibleAnimations;

    public Entity(Position position, Arena arena) {
        this.position = position;
        this.possibleAnimations = new ArrayList<>();
        this.arena = arena;
    }

    public Position getPosition() {
        return this.position;
    }

    public String getDrawSymbol() {
        return this.drawSymbol;
    }

    public TextColor.ANSI getColor() {
        return this.color;
    }

    public Arena getArena() {
        return this.arena;
    }

    public void setColor(TextColor.ANSI newColor) {
        this.color = newColor;
    }

    public void setDrawSymbolTo(String newDrawSymbol) {
        this.drawSymbol = newDrawSymbol;
    }
}
