package org.ldts.pacman.models;

import com.googlecode.lanterna.TextColor;

public class Pinky extends RegularGhost {
    protected Pinky(Position position) {
        super(position);
        this.color = TextColor.ANSI.WHITE; 
        this.chaseStrategy = new AmbushChaseStrategy();
        this.scatterStrategy = new ScatterToTopLeft();
    }
}
