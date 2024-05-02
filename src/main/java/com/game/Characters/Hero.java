package com.game.Characters;

import com.game.GamePanel.MainGamePanel;
import com.game.Items.Item;
import com.game.Items.ItemType;
import com.game.Key.Direction;
import com.game.Key.KeyHandler;
import com.game.Utilities.Score;
import com.game.Tile.MysteriousSmokeTile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.Timer;

/**
 * Represents the hero character controlled by the player in the game.
 * <p>
 * This class extends the {@link Character} class, adding functionality for player input handling,
 * scoring, and unique interactions such as item pickups and enemy encounters. It manages the hero's
 * movement, animation, collision detection, and game state interactions.
 */
public class Hero extends Character implements Score{


    private int currentScore = 0;
    protected boolean alive  = true;
    protected boolean isInvincible = false;
    protected KeyHandler keyHandler;
    public int coffeeTimeEnd;
    public int currentTime;
    public String diff;
    private int itemsCollected = 0;

    /**
     * Initializes the hero character with specific speed, position, and input handling.
     * <p>
     * Positions the hero at a predefined location in the game world and sets up key handling
     * for player movement and actions. Loads the hero's image resources and initializes the hero's
     * collision area.
     *
     * @param speed the movement speed of the hero
     * @param keyHandler the key handler for processing player input
     * @param gamePanel the game panel the hero belongs to
     */
    public Hero(int speed, KeyHandler keyHandler, MainGamePanel gamePanel){

        super(speed,gamePanel);
        this.keyHandler = keyHandler;
        this.solidArea = new Rectangle(0, 0, this.solidAreaDefaultX-5, this.solidAreaDefaultY-5);
        this.setScore(20);
        this.alive = true;
    }

    protected void setDefaultPosition(){
        this.setPosition(50,50);
        currentDirection = Direction.RIGHT;
        lastDirection = Direction.RIGHT;
    }

    /**
     * Loads the image resources specifically for the hero character.
     * <p>
     * Overrides the {@code getImage} method in the superclass to load images representing
     * the hero character's appearance.
     */
    @Override
    public void getImage() {
        rightImage = utilityTool.setImage("/Hero/Student_right",gamePanel);
        leftImage = utilityTool.setImage("/Hero/Student_left",gamePanel);
    }

    public void checkTileCollisionAndMoveHero() {
        //check tile collision
        collisionOn = false;
        mysteriousSmokeTileOn = false;
        gamePanel.collisionChecker.checkTile(this);

        if(keyHandler.getPressed(Direction.UP)){
            this.currentDirection = Direction.UP;
            if(!collisionOn)  this.moveUp(movementSpeed);

        }else if (keyHandler.getPressed(Direction.DOWN)) {
            this.currentDirection = Direction.DOWN;
            if(!collisionOn)  this.moveDown(movementSpeed);

        }else if (keyHandler.getPressed(Direction.LEFT)) {
            this.lastDirection = this.currentDirection;
            this.currentDirection = Direction.LEFT;
            if(!collisionOn)  this.moveLeft(movementSpeed);

        }else if (keyHandler.getPressed(Direction.RIGHT)) {

            this.lastDirection = this.currentDirection;
            this.currentDirection = Direction.RIGHT;
            if(!collisionOn)  this.moveRight(movementSpeed);

        }
    }

    /**
     * Checks for collision between the hero and any enemy characters.
     * If a collision is detected, it triggers an interaction with the enemy,
     * usually resulting in a game event like the hero losing health or being defeated.
     */
    public void checkEnemyCollision(){
        int enemyIndex = gamePanel.collisionChecker.checkCharacter(this,gamePanel.getEnemy());
        interactEnemy(enemyIndex);

    }

    /**
     * Checks for collision between the hero and game items.
     * If a collision is detected, it triggers the item pickup logic,
     * which can result in changes to the hero's score, inventory, or state.
     */
    public void checkItemCollision(){
        int itemIndex = gamePanel.collisionChecker.checkItem(this, true);
        pickUpItem(itemIndex);

    }

    /**
     * Handles the effect of mysterious smoke tiles on the hero when encountered.
     * If the hero is on a tile with mysterious smoke, this method triggers
     * the logic associated with the mysterious smoke, such as altering the hero's state
     * or affecting the hero's health or abilities.
     */
    public void handleMysteriousSmoke() {
        if (mysteriousSmokeTileOn) {
            MysteriousSmokeTile.engageSmoke(this, gamePanel);
        }
    }

    public void CollisionCheck(){

        checkTileCollisionAndMoveHero();
        checkEnemyCollision();
        checkItemCollision();

    }

    /**
     * Updates the hero's state based on player input and game environment interactions.
     * <p>
     * Manages movement, collision detection, enemy interactions, and item pickups. Also handles
     * game state transitions such as reaching the end of a level or encountering an enemy.
     *
     */

    public boolean update() {

        if(this.getScore() < 0){    // Game ends if hero's score is negative;
            gamePanel.gameTerminator.terminate();
            return true;
        }

        //check tile collision, enemy collision, item collision
        CollisionCheck();

        if (reachedEndOn){

            for(Item item: gamePanel.getItem()){
                if (item != null && (item.itemType == ItemType.Reward)){
                    gamePanel.ui.showMessage("You haven't collected all reward items!");
                    reachedEndOn = false;
                    return true;
                }
            }
            gamePanel.ui.gameDone = true;
            Timer timer;
            timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gamePanel.gameTerminator.terminate();
                }
            });
            timer.setRepeats(false);
            timer.start();
            return true;
            }

        handleMysteriousSmoke();
        return true;
    }



    /**
     * Handles interaction with an enemy character.
     *
     * @param enemyIndex the index of the encountered enemy
     */
    public void interactEnemy(int enemyIndex){

        if(enemyIndex != 999) {

            this.alive = false;
            gamePanel.gameTerminator.terminate();
        }
    }

    /**
     * Manages the action of picking up an item and its effects on the hero.
     *
     * @param itemIndex the index of the item to be picked up
     */
    public void pickUpItem(int itemIndex) {

        currentTime = gamePanel.getTimeElapsedSec();
        Item[] item = gamePanel.getItem();
        if(itemIndex != 999) { // if there is no hero-item collision index = 999
            this.itemsCollected++;
            item[itemIndex].collisionAction(this);
            item[itemIndex] = null;

        }
    }


    /**
     * Checks if the current score is greater than zero.
     * <p>
     * This method is used to determine if the hero has accumulated any score points.
     * It can be used in game logic to trigger certain events or validate game state conditions.
     *
     * @return {@code true} if the current score is greater than 0, otherwise {@code false}.
     */
    public boolean checkScore(){
        return currentScore > 0;
    }

    /**
     * Retrieves the current score of the hero.
     * <p>
     * This method provides access to the hero's current score, allowing other parts of the
     * game to display or otherwise use the score value.
     *
     * @return the current score of the hero.
     */
    public int getScore(){ return this.currentScore;} // GETTER

    /**
     * Sets the hero's score to a specified value.
     * <p>
     * Allows for the direct setting of the hero's score, unless the hero is in an invincible state.
     * In an invincible state, the score cannot be directly modified to prevent unintended score reduction.
     *
     * @param score the new score value to set for the hero.
     */
    public void setScore(int score){ // SETTER

        if(!isInvincible) {
            this.currentScore = score;
        }
    }

    /**
     * Increments the hero's score by a specified amount.
     * <p>
     * This method adds the specified amount to the current score, allowing for score increases
     * resulting from game events such as collecting items or defeating enemies.
     *
     * @param score the amount to add to the current score.
     */
    public void addScore(int score){
        this.currentScore += score;
    }


}

