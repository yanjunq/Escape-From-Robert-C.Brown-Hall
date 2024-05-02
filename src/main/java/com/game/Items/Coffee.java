package com.game.Items;

import com.game.Characters.Hero;
import com.game.GamePanel.MainGamePanel;

/**
 * Represents a coffee item in the game that temporarily increases the Hero's movement speed.
 * <p>
 * Upon collection, this item awards the Hero a score bonus and temporarily boosts their movement
 * speed, simulating the energizing effect of coffee. This class extends {@link Item} to
 * implement specific behaviors and properties associated with coffee items, including the duration
 * of the speed boost and the score bonus provided.
 */
public class Coffee extends Item {
    private int speedModifierSeconds = 5;
    private int increaseSpeed = 6;

    /**
     * Constructs a Coffee item associated with a specific game panel.
     * <p>
     * Initializes the item with its image, reward type, and sets the score bonus and speed boost
     * effect. The coffee item's image is loaded from resources, and it is set to the "Coffee"
     * reward type.
     *
     * @param gamePanel The game panel to which this coffee item belongs.
     */
    public Coffee(MainGamePanel gamePanel) {
        super(gamePanel);
        name = "Coffee";
        image = utilityTool.setImage("/Items/Coffee",gamePanel);
        setScoreEffect();
    }

    /**
     * Defines the action to take upon collision with the Hero character.
     * <p>
     * When the Hero character collides with this coffee item, this method is called to apply
     * both the score bonus and the temporary speed boost to the Hero, effectively "collecting"
     * the coffee and enhancing the Hero's capabilities for a short duration.
     *
     * @param hero The Hero character with which the coffee has collided.
     */
    public void collisionAction(Hero hero) {
        super.collisionAction(hero);
        hero.setMovementSpeed(increaseSpeed);
        hero.coffeeTimeEnd = (hero.currentTime + speedModifierSeconds);
    }
}
