package com.game.Items;

import com.game.Characters.Hero;
import com.game.GamePanel.MainGamePanel;

/**
 * Represents a pile of books item that, when collected, reduces the player's score.
 * <p>
 * This class extends {@link Item} to implement the behavior and effects of
 * the pile of books item, specifically reducing the player's score upon collection.
 * It includes methods to handle the collision action with the Hero character.
 */
public class PileOfBooks extends Item {

    /**
     * Constructs a PileOfBooks item associated with a specific game panel.
     * <p>
     * Initializes the item with its image and sets the amount of score reduction.
     * The item's image is loaded from resources, and it is marked with the "PileOfBooks"
     * punishment type.
     *
     * @param gamePanel The game panel to which this item belongs.
     */
    public PileOfBooks(MainGamePanel gamePanel) {
        super(gamePanel);
        this.name = "PileOfBooks";
        image = utilityTool.setImage("/Items/PileOfBooks",gamePanel);
        setScoreEffect();
    }
}
