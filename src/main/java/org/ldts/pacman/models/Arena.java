package org.ldts.pacman.models;

public class Arena {
    private final int height;
    private final int width;

    public Arena(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
