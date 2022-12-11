package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    protected String drawSymbol;
    protected TextColor.ANSI color;
    protected Position position;
    protected final Arena arena;
    protected final List<Animation> possibleAnimations;

    protected Entity(Position position) {
        this.position = position;
        this.arena = this.position.getArena();
        this.possibleAnimations = new ArrayList<>();
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
