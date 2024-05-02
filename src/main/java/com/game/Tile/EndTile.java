package com.game.Tile;

/**
 * Represents a tile that marks the end of a level in a game.
 * This tile is not steppable and signals the end of a game level.
 */
public class EndTile extends Tile {

    /**
     * Constructs a new EndTile.
     * This tile is not steppable and indicates that it marks the end of a level.
     */
    public EndTile() {
        this.tileSteppability = false;
        this.isLevelEndBool = true;
    }
}