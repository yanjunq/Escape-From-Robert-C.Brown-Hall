package com.game.Items;

import com.game.GamePanel.MainGamePanel;

import java.util.concurrent.ThreadLocalRandom;
import com.game.Utilities.Position;

import com.game.Characters.Hero;


/**
 * Represents a vortex item that teleports the Hero to a random position and applies a score penalty.
 * <p>
 * Extends {@link Item} to implement the behavior of a vortex, including a negative score
 * modifier and the action of teleporting the Hero upon collision. It encapsulates the logic for
 * determining a valid new position for the Hero within the game environment.
 */
public class Vortex extends Item {

    /**
     * Constructs a Vortex item associated with a specific game panel.
     * <p>
     * Initializes the item with its image and sets the damage points. The vortex item's image is loaded
     * from resources, and it is marked with the "Vortex" punishment type.
     *
     * @param gamePanel The game panel to which this vortex item belongs.
     */
    public Vortex(MainGamePanel gamePanel) {
        super(gamePanel);
        this.name = "Vortex";
        image = utilityTool.setImage("/Items/Vortex",gamePanel);
        setScoreEffect();
    }

    /**
     * Applies the vortex effect to the Hero character upon collision.
     * <p>
     * This method is called when the Hero collides with the vortex, applying a score penalty and
     * teleporting the Hero to a random valid position within the game world.
     *
     * @param hero The Hero character affected by the vortex.
     */
    @Override
    public void collisionAction(Hero hero) {
        super.collisionAction(hero);
        Position validPosition = validSpawnPosition();
        hero.setPosition(validPosition.getXPosition() * gamePanel.tileSize, validPosition.getYPosition() * gamePanel.tileSize);
    }
}
