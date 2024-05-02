package com.game.Tile;

/**
 * Represents a tile that cannot be stepped on by game entities.
 * This tile does not mark the end of a game level.
 */
public class NotSteppableTile extends Tile {

    /**
     * Constructs a new NotSteppableTile.
     * This tile is not steppable and does not mark the end of a game level.
     * Currently used for inner walls, outer walls, and the entrance door tile
     */
    public NotSteppableTile() {
        this.tileSteppability = false;
        this.isLevelEndBool = false;
    }
}
