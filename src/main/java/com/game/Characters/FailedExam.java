package com.game.Characters;

import com.game.GamePanel.MainGamePanel;
import com.game.Key.Direction;

/**
 * Represents the "FailedExam" enemy within the game.
 * <p>
 * This class is a specific implementation of the {@link Enemy} class, representing
 * an enemy with a theme of a failed exam. It initializes the enemy with a specific
 * speed and position in the game world and loads unique images representing the
 * "FailedExam" enemy. The imagery and behavior of this enemy type are tailored to
 * fit the theme, providing a distinct challenge to the player.
 */
public class FailedExam extends Enemy {

    /**
     * Constructs a {@code FailedExam} enemy with specified speed and associates it with a game panel.
     * <p>
     * Positions the enemy at a predefined location in the game world. This constructor calls the
     * superclass constructor to set common attributes and then specifies the initial position
     * unique to the {@code FailedExam} enemy.
     *
     * @param speed the movement speed of the {@code FailedExam} enemy
     * @param gamePanel the game panel the enemy belongs to
     */
    public FailedExam(int speed, MainGamePanel gamePanel){
        super(speed,gamePanel);
        this.setPosition(5*gamePanel.tileSize,11*gamePanel.tileSize);
        this.currentDirection=Direction.LEFT;
    }

    /**
     * Loads the image ressurces specifically for the {@code FailedExam} enemy.
     * <p>
     * This method overrides the {@code getImage} method in the superclass to load
     * unique images for the {@code FailedExam}, giving it a distinct appearance.
     */
    @Override
    public void getImage() {//pic is temp
        rightImage = utilityTool.setImage("/Enemy/FailedExam",gamePanel);
        leftImage = utilityTool.setImage("/Enemy/FailedExam",gamePanel);
    }

}
