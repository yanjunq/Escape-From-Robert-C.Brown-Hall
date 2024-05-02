package com.game.Tile;

import com.game.Characters.Hero;
import com.game.GamePanel.MainGamePanel;

/**
 * Represents a tile that can affect the player's speed and has visibility toggles.
 * <p>
 * Extends the {@link Tile} class to introduce a mysterious smoke tile that, when activated or stepped on,
 * can slow down the player for a certain period. The visibility of the tile can also be toggled to add
 * an element of surprise and strategy. This tile enhances gameplay by introducing environmental hazards
 * and challenges.
 */
public class MysteriousSmokeTile extends Tile {
    private static int slowSpeed = 2;
    private static int handicapTime = 5;
    private boolean isVisible;

/*    public MysteriousSmokeTile(int slowSpeed, int handicapTime, Image smokeSprite, boolean isVisible, Image paramTileSprite, int param_X, int param_y){
        this.slowSpeed = slowSpeed;
        this.handicapTime = handicapTime;
        this.smokeSprite = smokeSprite;
        this.isVisible = isVisible;
        //setTileSprite(paramTileSprite);
        setPosition(param_X,param_y);
    }*/

    /**
     * Constructs a MysteriousSmokeTile with default properties.
     * <p>
     * Initializes the tile to be steppable, not an end-level tile, and marks it as a
     * mysterious smoke tile. Default properties can be modified as needed for specific
     * gameplay scenarios.
     */
    public MysteriousSmokeTile(){
        this.tileSteppability = true;
        this.isLevelEndBool = false;
        this.isMysteriousSmokeTileBool = true;
    }

    /**
     * Retrieves the speed modifier associated with stepping on this tile.
     * <p>
     * This method allows access to the amount by which the player's speed will be reduced
     * upon interacting with the mysterious smoke, affecting their ability to navigate the game.
     *
     * @return The amount of speed reduction.
     */
    public int getSpeedModifier(){
        return slowSpeed;
    }

    /**
     * Engages the smoke effect, making the tile visible.
     * <p>
     * This method is intended to toggle the visibility of the mysterious smoke effect on,
     * revealing the tile to the player. It can be called based on specific game events or
     * conditions.
     */
    public static void engageSmoke(Hero hero, MainGamePanel gp){
        gp.tileM.setSpriteChange(5,"smoke");
        hero.setMovementSpeed(slowSpeed);
        hero.coffeeTimeEnd = (hero.currentTime + handicapTime);
    }

    /**
     * Hides the smoke effect, making the tile invisible.
     * <p>
     * This method toggles the visibility of the mysterious smoke effect off, hiding the tile
     * from the player. The tile can remain hidden until triggered by player interaction or
     * another game event.
     */
    public void hideSmoke(){
        this.isVisible = false; // remains hidden until player steps on it, reveals itself momentarily, then goes back invisible
    }
}
