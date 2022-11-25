package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Clyde extends RegularGhost {

    protected Clyde(Position position) {
        super(position);
        this.color = TextColor.ANSI.YELLOW;
        this.chaseStrategy = new AgressiveChaseStrategy();
        this.scatterStrategy = new ScatterToBottomLeft();
    }
}
