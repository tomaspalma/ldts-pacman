package org.ldts.pacman.models;

public class Clock {
    private long previousTime;
    private long elapsedMilliseconds;

    public Clock() {
        this.previousTime = System.currentTimeMillis();
        this.elapsedMilliseconds = 0;
    }

    public long getElapsedMilliseconds() {
        return this.elapsedMilliseconds;
    }

    public long getCurrentSystemTime() {
        return System.currentTimeMillis();
    }

    public void step() {
        long currentTime = this.getCurrentSystemTime();
        elapsedMilliseconds += (currentTime - this.previousTime);
        this.previousTime = currentTime;
    }
}
