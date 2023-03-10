package org.ldts.pacman.models.game;

public class Clock {
    private long previousTime;
    private long elapsedMilliseconds;
    private boolean paused = false;

    public Clock(long previousTimeInMilliseconds) {
        this.previousTime = previousTimeInMilliseconds;
        this.elapsedMilliseconds = 0;
    }

    public long getElapsedMilliseconds() {
        return this.elapsedMilliseconds;
    }

    public long getCurrentSystemTime() {
        return System.currentTimeMillis();
    }

    public void pause() {
        this.paused = true;
    }

    public void unpause() {
        this.paused = false;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void step() {
        if(this.paused)
            return;

        long currentTime = this.getCurrentSystemTime();
        elapsedMilliseconds += (currentTime - this.previousTime);
        assert elapsedMilliseconds >= 0;
        this.previousTime = currentTime;
    }

    public void reset() {
        this.elapsedMilliseconds = 0;
    }
}
