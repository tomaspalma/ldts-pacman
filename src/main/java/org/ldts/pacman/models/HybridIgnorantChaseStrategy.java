package org.ldts.pacman.models;

public class HybridIgnorantChaseStrategy implements ChaseStrategy {

    private ChaseStrategy currentChosenStrategy = null;
    public ChaseStrategy getCurrentChosenStrategy() {
        return this.currentChosenStrategy;
    }

    @Override
    public Position getNextPosition(Ghost ghost) {
        Position pacmanPosition = ghost.getArena().getPacman().getPosition();
        Position ghostPosition = ghost.getPosition();

        if(ghostPosition.getDistanceTo(pacmanPosition) > 8)
            tryToChangeToAgressive();
        else
            tryToChangeToRunToBottomLeft();

        return currentChosenStrategy.getNextPosition(ghost);
    }

    private void tryToChangeToAgressive() {
        boolean notYetOnAgressiveChaseStrat = !(currentChosenStrategy instanceof AgressiveChaseStrategy);

        if(notYetOnAgressiveChaseStrat)
            currentChosenStrategy = new AgressiveChaseStrategy();
    }

    private void tryToChangeToRunToBottomLeft() {
        boolean notYetOnRunToBottomLeftStrat = !(currentChosenStrategy instanceof RunToBottomLeftChaseStrategy);

        if(notYetOnRunToBottomLeftStrat)
            currentChosenStrategy = new RunToBottomLeftChaseStrategy();
    }
}
