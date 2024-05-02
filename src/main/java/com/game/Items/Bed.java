package com.game.Items;

import com.game.Characters.Hero;
import com.game.GamePanel.MainGamePanel;

/**
 * Represents a bed item in the game, serving as a main reward for the player.
 * <p>
 * Collecting a bed rewards the player with points and contributes towards level completion.
 * Each bed is worth a set number of points, and players must collect all beds within a level
 * to unlock the finish. This class extends {@link Item} to implement the specific behaviors
 * and properties associated with bed items.
 *
 */
public class Bed extends Item {

    /**
     * Constructs a Bed item associated with a specific game panel.
     * <p>
     * Initializes the item with its image and sets the score bonus for collecting the bed.
     * The bed's image is loaded from resources, and it's marked with the "Bed" reward type.
     *
     */
    public Bed(MainGamePanel gamePanel) {
        super(gamePanel);
        name = "Bed";
        image = utilityTool.setImage("/Items/Bed",gamePanel);
        setScoreEffect();
    }
}
