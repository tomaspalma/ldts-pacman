package org.ldts.pacman.models;

public class Arena {
    private final Float height;
    private final Float width;

    public Arena(Float width, Float height){
        this.width = width;
        this.height = height;
    }

    public Float getHeight() {
        return height;
    }

    public Float getWidth() {
        return width;
    }
}
