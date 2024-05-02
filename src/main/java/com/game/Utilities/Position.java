package com.game.Utilities;

import com.game.Key.Direction;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the spatial position and movement capabilities of game entities.
 * <p>
 * This class provides the basic functionality for managing the coordinates (X, Y) of game entities,
 * along with their movement behaviors and collision detection flags. It serves as a base class for
 * characters, items, and other objects within the game that require spatial positioning and movement.
 */
public class Position {
    private int X; // The X-coordinate of the entity
    private int Y; // The Y-coordinate of the entity
    public Rectangle solidArea; // The collision area of the entity
    public int solidAreaDefaultX = 48; // Default width of the solid area
    public int solidAreaDefaultY = 48; // Default height of the solid area
    protected Direction currentDirection; // The current movement direction of the entity
    protected Direction lastDirection; // The last movement direction of the entity

    protected int movementSpeed; // The movement speed of the entity
    protected boolean collisionOn = false; // Flag indicating if a collision has occurred
    protected boolean reachedEndOn = false; // Flag indicating if the end of a level or segment has been reached
    protected boolean mysteriousSmokeTileOn = false; // Flag indicating if an effect from a mysterious smoke tile is active

    /**
     * Default constructor initializing the position with default coordinates (0, 0).
     */
    public Position(){ // default constructor
        this.X = 0;
        this.Y = 0;
    }

    public boolean getCollisionOn(){return this.collisionOn;}

    public void setMovementSpeed(int speed){
        this.movementSpeed = speed;
    }

    /**
     * Parameterized constructor for setting the initial position.
     *
     * @param param_X The initial X-coordinate.
     * @param param_y The initial Y-coordinate.
     */
//    public Position(int param_X, int param_y){ // parameterized constructor
//        this.X = param_X;
//        this.Y = param_y;
//    }

    /**
     * Sets the position of the entity.
     *
     * @param param_X The X-coordinate to move the entity to.
     * @param param_y The Y-coordinate to move the entity to.
     */
    public void setPosition(int param_X, int param_y){ // setter
        this.X = param_X;
        this.Y = param_y;
    }

    public int getXPosition(){ // getter X
        return this.X;
    }
    public int getYPosition(){return this.Y;}
    public int getMovementSpeed(){return  this.movementSpeed;}
    public Direction getCurrentDirection(){return this.currentDirection;}
    public void setCurrentDirection(Direction d){this.currentDirection = d;}
    public Direction getLastDirection(){return this.lastDirection;}


    public void moveUp(int s){this.Y-=s;}
    public void moveDown(int s){this.Y+=s;}
    public void moveRight(int s){this.X+=s;}
    public void moveLeft(int s){this.X-=s;}


}
