package com.game.Characters;

import com.game.GamePanel.MainGamePanel;
import com.game.Key.Direction;

/**
 * Represents an enemy character within the game.
 * <p>
 * This class extends the {@link Character} class, specializing it for enemy behavior.
 * Enemies can automatically navigate towards the player character, checking for collisions
 * and initiating actions based on their AI logic. The {@code Enemy} class provides mechanisms
 * for setting default positions, handling images, custom collision detection, and defining
 * enemy-specific actions such as movement patterns and interactions with the player.
 */
public class Enemy extends Character {

    /**
     * Sets the default position and orientation of the enemy character.
     * This method is intended to be overridden by subclasses for custom positioning.
     */
    @Override
    protected void setDefaultPosition(){
        currentDirection = Direction.RIGHT;
        lastDirection = Direction.RIGHT;
    }
    /**
     * Loads the image resources for the enemy character.
     * This method is intended to be overridden by subclasses to specify enemy appearance.
     */
    @Override
    public void getImage() {}

    /**
     * Constructs an {@code Enemy} with specified speed and associates it with a game panel.
     *
     * @param speed the movement speed of the enemy
     * @param gamePanel the game panel the enemy belongs to
     */
    public Enemy(int speed, MainGamePanel gamePanel) {
        super(speed, gamePanel);
        this.movementSpeed = speed;
    }

    /**
     * Checks for and handles collisions specific to the enemy character.
     * This includes interactions with tiles, the player, and other characters.
     */
    @Override
    public void checkCollision() {
        gamePanel.collisionChecker.checkTile(this);
        int index = gamePanel.collisionChecker.checkCharacter(this,gamePanel.getHero());
        if(index != 999){
            this.gamePanel.getHero().alive = false;
            gamePanel.gameTerminator.terminate();
        }
    }

    /**
     * Determines and initiates the enemy's actions based on its AI logic.
     * Actions may include pathfinding towards the player or random movement.
     */
    @Override
    public void setAction() {

            int goalCol = (gamePanel.getHero().getXPosition() + gamePanel.getHero().solidArea.x)/gamePanel.tileSize;
            int goalRow = (gamePanel.getHero().getYPosition() + gamePanel.getHero().solidArea.y)/gamePanel.tileSize;
            searchPath(goalCol,goalRow);

    }

    /**
     * Updates the enemy's state and handles movement based on the current direction.
     * <p>
     * This method manages collision detection, action decisions, and movement execution.
     * It ensures that the enemy navigates the game world according to its AI behavior.
     *
     */
    public boolean update() {

        reachedEndOn = false;
        collisionOn = false;
        checkCollision();
        setAction();

        if (!collisionOn) {
            switch (currentDirection) {
                case UP:
                    this.moveUp(movementSpeed);
                    return true;
                case DOWN:
                    this.moveDown(movementSpeed);
                    return true;
                case LEFT:
                    this.moveLeft(movementSpeed);
                    return true;
                case RIGHT:
                    this.moveRight(movementSpeed);
                    return true;
            }
        }
        return false;

    }

}
