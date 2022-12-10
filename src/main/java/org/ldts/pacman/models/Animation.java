package org.ldts.pacman.models;

public abstract class Animation {

   protected final Clock internalClock;
   protected final long durationInMilliseconds;

   public Animation(long durationInMilliseconds) {
      this.durationInMilliseconds = durationInMilliseconds;
      this.internalClock = new Clock();
   }

   public long getDurationInMilliseconds() {
      return this.durationInMilliseconds;
   }

   public Clock getInternalClock() {
      return this.internalClock;
   }
   public abstract void step();

   public boolean isFinished() {
      return this.internalClock.getElapsedMilliseconds() > this.durationInMilliseconds;
   }
}
