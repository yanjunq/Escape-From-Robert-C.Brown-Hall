import com.game.GamePanel.MainGamePanel;
import com.game.Items.*;
import com.game.Tile.FloorTile;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import com.game.Characters.*;

/**
 * This class contains unit tests for the movement of the hero when interacting with a vortex.
 */
public class TestVortexMoveHero {

    private MainGamePanel gamePanel;
    private String difficulty = "Easy";
    private static Hero hero;
    private Enemy enemy;

    @BeforeEach
    public void setUp() {
        this.gamePanel = new MainGamePanel();
        gamePanel.setupGame(difficulty);
        hero = gamePanel.getHero();
        enemy = gamePanel.getEnemy();

    }

    /**
     * Test to verify that the hero cannot move to a new tile because the map tile is not a 'floor' tile.
     */
    @Test
    public void placementNotAllowedNotFloor(){

        boolean validPosition;
        hero.setPosition(0,0);
        int tileNum = gamePanel.tileM.getMapTileNum()[0][0];
        validPosition = gamePanel.tileM.getTile()[tileNum] instanceof FloorTile;
        assertFalse(validPosition);
    }

    /**
     * Test to verify that the hero cannot move to a new tile because an enemy is already present in the tile.
     */
    @Test
    public void placementNotAllowedEnemy(){

        boolean validPosition;
        int enemyXCoord = enemy.getXPosition();
        int enemyYCoord = enemy.getYPosition();
        Item dummyItem = new Item(gamePanel);
        dummyItem.setPosition(enemyXCoord/48, enemyYCoord/48);
        validPosition = !(gamePanel.collisionChecker.isCharacterIntersecting(dummyItem));
        assertFalse(validPosition);
    }

    /**
     * Test to verify that the hero's move is valid: the tile is a floor, no item present, no enemy present.
     */
    @Test
    public void placementAllowed(){

        boolean validPositionCharacter;
        boolean validPositionFloor;
        hero.setPosition(6,1);
        int tileNum = gamePanel.tileM.getMapTileNum()[6][1];
        Item dummyItem = new Item(gamePanel);
        dummyItem.setPosition(6, 1);
        validPositionCharacter = !(gamePanel.collisionChecker.isCharacterIntersecting(dummyItem));
        validPositionFloor = gamePanel.tileM.getTile()[tileNum] instanceof FloorTile;
        assertTrue(validPositionCharacter);
        assertTrue(validPositionFloor);
    }

    /**
     * Test to verify that the hero's position changes after interaction with a vortex.
     */
    @Test
    public void doesHeroLocationChange(){
        int orgPosX = hero.getXPosition();
        int orgPosY = hero.getYPosition();
        hero.pickUpItem(12);
        int respawnPosX = hero.getXPosition();
        int respawnPosY = hero.getYPosition();
        boolean xPosChanged = orgPosX != respawnPosX;
        boolean yPosChanged = orgPosY != respawnPosY;
        boolean moved = xPosChanged || yPosChanged;
        assertTrue(moved);
    }
}
