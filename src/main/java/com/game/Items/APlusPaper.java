package com.game.Items;
import com.game.GamePanel.MainGamePanel;
import com.game.Characters.Hero;
import com.game.Utilities.Position;

/**
 * Represents an A+ paper item in the game that grants the player a score bonus upon collection.
 * <p>
 * This class extends {@link Item} to provide specific functionality for the A+ paper item,
 * including a predefined score bonus. When collected by the Hero, it increases the player's score,
 * symbolizing the acquisition of a valuable academic reward. Additionally, it includes a method to
 * update its position randomly within valid game bounds to maintain gameplay dynamics.
 */
public class APlusPaper extends Item {

    /**
     * Constructs an APlusPaper item associated with a specific game panel.
     * <p>
     * Initializes the item with its image, reward type, and sets the score bonus. The item's image
     * is loaded from the resources, and the item is set to the "APlusPaper" reward type.
     *
     * @param gamePanel The game panel to which this item belongs.
     */
    public APlusPaper(MainGamePanel gamePanel) {
        super(gamePanel);
        name = "APlusPaper";
        image = utilityTool.setImage("/Items/APlusPaper",gamePanel);
        setScoreEffect();
    }

    /**
     * Updates the item's position randomly within the game environment.
     * <p>
     * This method is intended to relocate the A+ paper to a new, valid position within the game world
     * to simulate dynamic placement and encourage exploration. The new position is chosen randomly but
     * is validated to ensure it is within appropriate bounds and not colliding with the Hero.
     */
    public void reSpawnPosition() {
        Position validPosition = validSpawnPosition();
        this.setPosition(validPosition.getXPosition(), validPosition.getYPosition());
    }
}




