package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public abstract class Entity {
    protected String drawSymbol;
    protected TextColor.ANSI color;
    protected Position position;
    protected final Arena arena;

    protected Entity(Position position) {
        this.position = position;
        this.arena = this.position.getArena();
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
}
