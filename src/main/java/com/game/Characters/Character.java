package com.game.Characters;

import com.game.GamePanel.MainGamePanel;
import com.game.Key.Direction;
import com.game.Utilities.Position;
import com.game.Utilities.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Abstract base class for all game characters, providing common attributes and functionality.
 * <p>
 * This class encapsulates the main attributes shared by all characters present in the game world,
 * excluding the main menu. It includes movement mechanics, animation handling, and collision detection,
 * among others. Characters derived from this class can be either player-controlled or AI-driven entities
 * within the game environment.
 *
 */
public abstract class Character extends Position {

    public BufferedImage currentImage = null;
    public BufferedImage leftImage, rightImage;
    public UtilityTool utilityTool = new UtilityTool();
    protected MainGamePanel gamePanel;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public boolean onPath = true;

    /**
     * Sets the default position of the character within the game world.
     * Must be implemented by subclasses to define initial character placement.
     */
    protected abstract void setDefaultPosition();

    /**
     * Loads the character's image resources.
     * Must be implemented by subclasses to specify character appearance.
     */
    public abstract void getImage();


    /**
     * Performs the character's current action, which may include movement or other behaviors.
     * Can be overridden by subclasses to define specific character actions.
     */
    public void setAction(){};

    public MainGamePanel getGamePanel() {
        return gamePanel;
    }


    /**
     * Constructs a Character with specified movement speed and associates it with a game panel.
     *
     * @param speed the movement speed of the character
     * @param gamePanel the game panel the character belongs to
     */
    protected Character(int speed, MainGamePanel gamePanel){
            this.setDefaultPosition();
            this.movementSpeed = speed;
            this.gamePanel = gamePanel;
            this.solidAreaDefaultX = gamePanel.tileSize;
            this.solidAreaDefaultY = gamePanel.tileSize;
            this.solidArea = new Rectangle(0, 0, this.solidAreaDefaultX-3, this.solidAreaDefaultY-3);
            this.getImage();
    }

    /**
     * Updates the current image to be displayed based on the character's current and last direction.
     * If the current direction is either LEFT or RIGHT, the current image is updated accordingly.
     * Otherwise, the last direction is used to determine the current image.
     */
    private void updateCurrentImage() {
        if (currentDirection == Direction.LEFT || currentDirection == Direction.RIGHT) {
            currentImage = currentDirection == Direction.LEFT ? leftImage : rightImage;
        } else {
            currentImage = lastDirection == Direction.LEFT ? leftImage : rightImage;
        }
    }

    /**
     * Draws the current image at the character's position.
     * The method first updates the current image based on the character's direction,
     * then it draws that image on the given Graphics2D context at the current X and Y position.
     * The size of the image is determined by the tile size of the game panel.
     *
     * @param g2 The Graphics2D context on which the image will be drawn.
     */
    public void draw(Graphics2D g2) {
        updateCurrentImage();
        g2.drawImage(currentImage,this.getXPosition(), this.getYPosition(), gamePanel.tileSize,gamePanel.tileSize,null);
    }
    /**
     * Updates the character's movement speed.
     *
     * @param speed the new movement speed of the character
     */
    public void setMovementSpeed(int speed){
        this.movementSpeed = speed;
    }

    public void checkCollision(){};

    /**
     * Initiates pathfinding from the character's current position to a specified goal.
     *
     * @param goalCol the column index of the goal position
     * @param goalRow the row index of the goal position
     */
    public void searchPath(int goalCol, int goalRow){
        int startCol = (getXPosition() + solidArea.x)/gamePanel.tileSize;
        int startRow = (getYPosition() + solidArea.y)/gamePanel.tileSize;

        gamePanel.pathFinder.setNode(startCol,startRow,goalCol,goalRow,this);
        if(gamePanel.pathFinder.search()){
            //next worldX & worldY
            int nextX = gamePanel.pathFinder.pathList.get(0).col * gamePanel.tileSize;
            int nextY = gamePanel.pathFinder.pathList.get(0).row * gamePanel.tileSize;

            //charater's solidarea position
            int enLeftX = getXPosition() + solidArea.x;
            int enRightX = getXPosition() + solidArea.x + solidArea.width;
            int enTopY = getYPosition() + solidArea.y;
            int enBottomY = getYPosition() + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gamePanel.tileSize){
                currentDirection = Direction.UP;
            }else if( enLeftX >= nextX && enTopY < nextY && enRightX < nextX + gamePanel.tileSize){
                currentDirection = Direction.DOWN;
            }else if(enTopY >= nextY && enBottomY < nextY + gamePanel.tileSize){
                if(enLeftX > nextX)
                    currentDirection = Direction.LEFT;
                if(enLeftX < nextX)
                    currentDirection = Direction.RIGHT;
            }else if(enTopY > nextY && enLeftX > nextX){
                //up or right
                currentDirection = Direction.UP;
                checkCollision();
                if(collisionOn)
                    currentDirection = Direction.LEFT;

            }else if(enLeftX < nextX && enTopY > nextY){
                //up or right
                currentDirection = Direction.UP;
                checkCollision();
                if(collisionOn)
                    currentDirection = Direction.RIGHT;

           //}else if(enTopY < nextY && enLeftX > nextX){
            }else{
                //down and left
                currentDirection = Direction.DOWN;
                checkCollision();
                if(collisionOn)
                    currentDirection = Direction.LEFT;
            }
        }

    }

}
