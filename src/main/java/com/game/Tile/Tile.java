package com.game.Tile;

import com.game.Utilities.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Serves as the base class for all tiles within the game's world.
 * <p>
 * This abstract class extends {@link Position} to provide a foundational representation of a tile
 * in the game environment. It includes properties for visual representation, collision detection,
 * and special behaviors such as triggering the end of a level or activating specific effects like
 * mysterious smoke. It is designed to be extended by more specific tile types that implement
 * unique behaviors and visuals.
 */
public abstract class Tile extends Position {
    private BufferedImage tileSprite; // The visual sprite for the tile
    public boolean collision; // Indicates if the tile causes collision
    protected boolean tileSteppability; // Indicates if the tile can be stepped on
    protected boolean isLevelEndBool; // More specific flag for level ending condition
    protected boolean isMysteriousSmokeTileBool; // Flags the tile as having a mysterious smoke effect

    /**
     * Gets the sprite image of the tile.
     * <p>
     * This method provides access to the tile's visual representation.
     *
     * @return The sprite image of the tile.
     */
    public BufferedImage getTileSprite() {
        return this.tileSprite;
    }

    /**
     * Sets the sprite image for the tile.
     * <p>
     * Allows assigning a specific image to visually represent the tile in the game world.
     *
     * @param param_sprite The sprite image to set for the tile.
     */
    public void setTileSprite(BufferedImage param_sprite){
        this.tileSprite = param_sprite;
    }

    /**
     * Checks if the tile can be stepped on.
     * <p>
     * Determines whether the tile is navigable or blocks movement.
     *
     * @return {@code true} if the tile is steppable, {@code false} otherwise.
     */
    public boolean tileSteppable() {
        return this.tileSteppability;
    }

    /**
     * Checks if the tile triggers the end of a level.
     * <p>
     * Indicates whether interacting with the tile should signal the completion of the current level.
     *
     * @return {@code true} if the tile ends the level, {@code false} otherwise.
     */
    public boolean isLevelEnd() {
        return this.isLevelEndBool;
    }

    /**
     * Determines if the tile is a mysterious smoke tile.
     * <p>
     * Checks if the tile has the mysterious smoke effect, potentially altering player movement or visibility.
     *
     * @return {@code true} if the tile has a mysterious smoke effect, {@code false} otherwise.
     */
    public boolean isMysteriousSmokeTile(){
        return this.isMysteriousSmokeTileBool;
    }
}
