package com.game.Characters;
import com.game.GamePanel.MainGamePanel;

/**
 * Represents a "ZombieProfessor" enemy type within the game.
 * <p>
 * This class extends the {@link Enemy} class to introduce a unique enemy with specific
 * characteristics and behavior, represented by a zombie version of a professor. It customizes
 * the enemy appearance by loading specific images for the zombie professor and can include
 * additional behaviors or interactions unique to this enemy type.
 */
public class ZombieProfessor extends Enemy {

    /**
     * Constructs a {@code ZombieProfessor} with specified speed and associates it with a game panel.
     * <p>
     * Initializes the enemy with the given movement speed and sets up its position within the game world
     * using the {@code GamePanel} parameter. This constructor calls the superclass constructor to establish
     * common enemy attributes and then proceeds to load the specific images for the {@code ZombieProfessor}.
     *
     * @param speed the movement speed of the {@code ZombieProfessor}
     * @param gamePanel the game panel the enemy belongs to
     */
    public ZombieProfessor (int speed, MainGamePanel gamePanel){
        super(speed, gamePanel);
        this.setPosition(5 * gamePanel.tileSize, 14 * gamePanel.tileSize);
    }


    /**
     * Loads the image resources specifically for the {@code ZombieProfessor}.
     * <p>
     * Overrides the {@code getImage} method in the superclass to load unique images
     * that depict the zombie professor from the left and right perspectives, giving it
     * a distinct appearance.
     */
    @Override
    public void getImage() {
        rightImage = utilityTool.setImage("/Enemy/Zombie_right",gamePanel);
        leftImage = utilityTool.setImage("/Enemy/Zombie_left",gamePanel);
    }
}
