package com.game.Items;
import com.game.GamePanel.MainGamePanel;
import com.game.Tile.FloorTile;
import com.game.Utilities.Position;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;
import com.game.Characters.Hero;
import com.game.Utilities.UtilityTool;

/**
 * Serves as the base class for all items in the game, defining common properties and methods.
 * <p>
 * This abstract class extends {@link Position} to include positional data and implements common
 * item behaviors such as drawing the item on the screen, handling item state updates, and defining
 * collision actions. Specific item classes extend this base class to implement item-specific
 * behaviors and effects.
 */
public class Item extends Position {

    public BufferedImage image;
    public String name;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 48;
    public int solidAreaDefaultY = 48;
    public boolean collision = true;
    protected MainGamePanel gamePanel;
    protected int xCoordinate;
    protected int yCoordinate;
    public ItemType itemType;
    public UtilityTool utilityTool = new UtilityTool();
    protected int scoreEffect;

    public Item(MainGamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    protected void setScoreEffect() {
        if(name.equals("APlusPaper")) {
            scoreEffect = 10;
            itemType = ItemType.Reward;
        }
        else if(name.equals("Coffee") || name.equals("Bed")) {
            scoreEffect = 5;
            itemType = ItemType.Reward;
        }
        else if(name.equals("Vortex") || name.equals("PileOfBooks")) {
            scoreEffect = -5;
            itemType = ItemType.Punishment;
        }
    }

    /**
     * Draws the item on the game panel.
     *
     * @param g2 The Graphics2D object used for drawing.
     * @param gp The game panel where the item is to be drawn.
     */
    public void draw(Graphics2D g2, MainGamePanel gp) {
        int screenX = getXPosition();
        int screenY = getYPosition();
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    /**
     * Defines the action to take when the item collides with the Hero. Override in subclasses.
     *
     * @param hero The Hero character with which the item has collided.
     */
    public void collisionAction(Hero hero) {
        hero.addScore(scoreEffect);
    }

    public Position validSpawnPosition() {

        int newRowPos;
        int newColPos;
        boolean validPositionCharacter = false;
        boolean validPositionItem = false;
        boolean validTileFloor = false;
        Position validPosition = new Position();

        while(!validPositionCharacter || !validPositionItem || !validTileFloor) {

            // range of rows: 0-17
            // range of columns: 0-27
            newRowPos = ThreadLocalRandom.current().nextInt(0, 18);
            newColPos = ThreadLocalRandom.current().nextInt(0, 28);
            Item checkPositionValid = new Item(gamePanel);
            checkPositionValid.setPosition(newColPos, newRowPos);
            int tileNum = gamePanel.tileM.getMapTileNum()[newColPos][newRowPos];

            validTileFloor = gamePanel.tileM.getTile()[tileNum] instanceof FloorTile;
            validPositionItem = !(gamePanel.collisionChecker.isTileOccupied(gamePanel.item, checkPositionValid));
            validPositionCharacter = !(gamePanel.collisionChecker.isCharacterIntersecting(checkPositionValid));
            validPosition.setPosition(newColPos, newRowPos);
        }
        return validPosition;
    }

    public void setPosition(int param_X, int param_y) { // setter
        xCoordinate = param_X;
        yCoordinate = param_y;
        super.setPosition(param_X * 48, param_y * 48);
    }
}
