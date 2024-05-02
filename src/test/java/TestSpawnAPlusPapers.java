import com.game.GamePanel.MainGamePanel;
import com.game.Items.*;
import com.game.Tile.FloorTile;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the spawning behavior of APlusPaper items.
 */
public class TestSpawnAPlusPapers {

    private MainGamePanel gamePanel;
    private String difficulty = "Hard";

    @BeforeEach
    public void setUp() {
        this.gamePanel = new MainGamePanel();
        gamePanel.setupGame(difficulty);

    }

    /**
     * Test to verify that an APlusPaper item cannot be placed because an item is already present in the position.
     */
    @Test
    public void placementNotAllowedItemAlreadyPresent(){

        boolean validPosition;
        APlusPaper testItem = new APlusPaper(gamePanel);
        testItem.setPosition(20,4);
        validPosition = !(gamePanel.collisionChecker.isTileOccupied(gamePanel.item, testItem));
        assertFalse(validPosition);
    }

    /**
     * Test to verify that an APlusPaper item cannot be placed because the position is not a floor tile.
     */
    @Test
    public void placementNotAllowedNotFloor(){

        boolean validPosition;
        APlusPaper testItem = new APlusPaper(gamePanel);
        testItem.setPosition(0,0);
        int tileNum = gamePanel.tileM.getMapTileNum()[0][0];
        validPosition = gamePanel.tileM.getTile()[tileNum] instanceof FloorTile;
        assertFalse(validPosition);
    }

    /**
     * Test to verify that an APlusPaper item cannot be placed because the hero is already present in the position.
     */
    @Test
    public void placementNotAllowedHeroAlreadyPresent(){

        boolean validPosition;
        APlusPaper testItem = new APlusPaper(gamePanel);
        testItem.setPosition(1,1);
        validPosition = !(gamePanel.collisionChecker.isCharacterIntersecting(testItem));
        assertFalse(validPosition);
    }

    /**
     * Test to verify that an APlusPaper item can be placed when conditions are met: floor tile, no item, no hero.
     */
    @Test
    public void placementAllowed(){

        boolean validPositionItem;
        boolean validPositionHero;
        boolean validPositionFloor;
        APlusPaper testItem = new APlusPaper(gamePanel);
        testItem.reSpawnPosition();
        int spawnXCoord = testItem.getXPosition()/48;
        int spawnYCoord = testItem.getYPosition()/48;
        int tileNum = gamePanel.tileM.getMapTileNum()[spawnXCoord][spawnYCoord];
        validPositionItem = !(gamePanel.collisionChecker.isTileOccupied(gamePanel.item, testItem));
        validPositionHero = !(gamePanel.collisionChecker.isCharacterIntersecting(testItem));
        validPositionFloor = gamePanel.tileM.getTile()[tileNum] instanceof FloorTile;
        assertTrue(validPositionItem);
        assertTrue(validPositionHero);
        assertTrue(validPositionFloor);
    }

    /**
     * Test to verify that the APlusPaper item changes its location after spawning.
     */
    @Test
    public void doesAPlusPaperReSpawn(){
        int orgPosX = gamePanel.item[3].getXPosition();
        int orgPosY = gamePanel.item[3].getYPosition();
        gamePanel.reSpawnAPlusPaperPosition();
        int respawnPosX = gamePanel.item[3].getXPosition();
        int respawnPosY = gamePanel.item[3].getYPosition();
        boolean xPosChanged = orgPosX != respawnPosX;
        boolean yPosChanged = orgPosY != respawnPosY;
        boolean moved = xPosChanged || yPosChanged;
        assertTrue(moved);
    }
}














