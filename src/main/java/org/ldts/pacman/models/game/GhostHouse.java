package org.ldts.pacman.models.game;

import org.ldts.pacman.models.game.entities.ghost.Ghost;

import java.util.ArrayList;
import java.util.List;

// The height and width are relative to the upper left corner of the ghost house
public class GhostHouse {
    private final List<Ghost> ghostHolder = new ArrayList<>();
    private final Position upperLeftPosition;
    private final Position gatePosition;
    private GhostHouseGate gate;
    private final int width;
    private final int height;

    public GhostHouse(Position upperLeftPosition, int width, int height) {
        this.upperLeftPosition = upperLeftPosition;
        this.width = width;
        this.height = height;
        
        this.gatePosition = new Vector(this.width / 2, 0).getPositionBasedOnSumWith(upperLeftPosition);
    }

    public Position getUpperLeftPosition() {
        return upperLeftPosition;
    }

    public List<Ghost> getGhostHolder() {
        return ghostHolder;
    }

    public Position getAvailablePosition() {
        int xOffset = (this.ghostHolder.size() % width) + 1;
        int yOffset = (this.ghostHolder.size() / height) + 1;

        return new Vector(xOffset, yOffset).getPositionBasedOnSumWith(upperLeftPosition);
    }

    public Position getExitPosition() {
        return new Vector(0, -1).getPositionBasedOnSumWith(this.gatePosition);
    }

    public GhostHouseGate getGhostHouseGate() {
        return gate;
    }

    public void setGhostHouseGate(GhostHouseGate newGhostHouseGate) {
        this.gate = newGhostHouseGate;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Position getGatePosition() {
        return gatePosition;
    }
}
