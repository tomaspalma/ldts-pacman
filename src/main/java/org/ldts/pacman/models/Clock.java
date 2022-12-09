package org.ldts.pacman.models;

public class Clock {
    private long previousTime;
    private long elapsedMilliseconds;

    public Clock() {
        previousTime = System.currentTimeMillis();
        elapsedMilliseconds = 0;
    }

    public long getElapsed() {
        return elapsedMilliseconds;
    }

    public void step() {
        long currentTime = System.currentTimeMillis();
        elapsedMilliseconds += (currentTime - previousTime);
        previousTime = currentTime;
    }
}
