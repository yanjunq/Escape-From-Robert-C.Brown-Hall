package com.game.Tile;

/**
 * Represents a floor tile that can be stepped on by game entities.
 * This tile does not mark the end of a game level.
 */
public class FloorTile extends Tile {

    /**
     * Constructs a new FloorTile.
     * This tile is steppable and does not mark the end of a game level.
     */
    public FloorTile() {
        this.tileSteppability = true;
        this.isLevelEndBool = false;
    }
}
