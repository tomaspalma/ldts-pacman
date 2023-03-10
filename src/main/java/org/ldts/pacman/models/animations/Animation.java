package org.ldts.pacman.models.animations;

import org.ldts.pacman.models.game.Clock;

public abstract class Animation {

   protected final Clock internalClock;
   protected final long durationInMilliseconds;

   public Animation(long durationInMilliseconds, Clock internalClock) {
      this.durationInMilliseconds = durationInMilliseconds;
      this.internalClock = internalClock;
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
