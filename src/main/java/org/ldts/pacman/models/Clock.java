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

    public long getCurrentSystemTime() {
        return System.currentTimeMillis();
    }

    public void step() {
        long currentTime = this.getCurrentSystemTime();
        elapsedMilliseconds += (currentTime - previousTime);
        previousTime = currentTime;
    }
}
