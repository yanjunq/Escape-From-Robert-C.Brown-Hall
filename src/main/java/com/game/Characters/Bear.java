package com.game.Characters;

import com.game.GamePanel.MainGamePanel;

/**a
 * Represents a Bear enemy type in the game.
 * <p>
 * This class extends the {@link Enemy} class to provide specific implementations for the Bear enemy,
 * setting its movement speed, initial position, and visual representation. The Bear is a distinct
 * type of enemy with unique behaviors or characteristics defined by its speed and images.
 */
public class Bear extends Enemy {

    /**
     * Constructs a Bear enemy with specified speed and associates it with a game panel.
     * <p>
     * Initializes the Bear with the given movement speed and sets its initial position within
     * the game world. It also assigns images to represent the Bear visually in the game.
     *
     * @param speed the movement speed of the Bear.
     * @param gamePanel the game panel the Bear belongs to.
     */
    public Bear(int speed, MainGamePanel gamePanel){
        super(speed,gamePanel);
//        this.setPosition(7*gamePanel.tileSize,12*gamePanel.tileSize);//temp
       this.setPosition(13*gamePanel.tileSize,11*gamePanel.tileSize);

    }

    /**s
     * Loads the image resources specifically for the Bear.
     * <p>
     * Overrides the {@code getImage} method in the superclass to load unique images
     * representing the Bear from both left and right perspectives, giving it a distinct appearance.
     */
    @Override
    public void getImage() {//pic is temp
        rightImage = utilityTool.setImage("/Enemy/Bear_right",gamePanel);
        leftImage = utilityTool.setImage("/Enemy/Bear_left",gamePanel);
    }

}
