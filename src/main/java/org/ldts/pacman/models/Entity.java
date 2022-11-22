package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public abstract class Entity {
    protected String drawSymbol;
    protected TextColor.ANSI color;
    protected Position position;

    protected Entity(Position position) {
        this.position = position;
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
}
