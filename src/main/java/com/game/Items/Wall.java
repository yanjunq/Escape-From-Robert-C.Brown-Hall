package com.game.Items;

import com.game.Utilities.Position;
import java.awt.*;

/**
 * Represents a wall obstacle within the game environment.
 * <p>
 * This class extends {@link Position} to provide functionality for wall obstacles that can
 * block the player's or enemies' path. It includes properties for the wall's length and
 * visual representation.
 */
public class Wall extends Position {

    private Image wallSprite;

    /**
     * Constructs a Wall at a specified position.
     *
     * @param param_X The x-coordinate of the wall's position.
     * @param param_y The y-coordinate of the wall's position.
     */
    Wall(int param_X, int param_y){
        setPosition(param_X,param_y);
    }
}
