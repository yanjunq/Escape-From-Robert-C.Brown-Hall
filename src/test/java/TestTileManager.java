import com.game.GamePanel.MainGamePanel;
import com.game.Tile.NotSteppableTile;
import com.game.Tile.Tile;
import com.game.Tile.TileManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link TileManager}.
 */
public class TestTileManager {

    private MainGamePanel gamePanel;
    private TileManager tileManager;
    private String difficulty = "Easy";

    @BeforeEach
    public void setUp() {
        this.gamePanel = new MainGamePanel();
        gamePanel.startGame(difficulty);
        this.tileManager = new TileManager(gamePanel, difficulty);
    }

    /**
     * Test to verify that the mapTileNum array is correctly populated.
     */
    @Test
    void mapTileNumCorrectlyPopulated() {
        assertNotNull(tileManager.getMapTileNum());
        assertEquals(gamePanel.maxScreenCol, tileManager.getMapTileNum().length);
        assertEquals(gamePanel.maxScreeRow, tileManager.getMapTileNum()[0].length);
    }

    /**
     * Test to ensure that setting a new sprite for a tile type results in the expected sprite change.
     *
     * @throws IOException if there is an error reading the image file
     */
    @Test
    public void testSetSpriteChange() throws IOException {
        Tile lockerTile = new NotSteppableTile();
        BufferedImage originalSprite = ImageIO.read(getClass().getResourceAsStream("/Tiles/locker.png"));
        lockerTile.setTileSprite(originalSprite);

        String newSpriteFilename = "locker";
        tileManager.setSpriteChange(0, newSpriteFilename);

        BufferedImage newSprite = tileManager.getTile()[0].getTileSprite();

        int[] originalPixels = getRGBPixels(originalSprite);
        int[] newPixels = getRGBPixels(newSprite);

        assertArrayEquals(originalPixels, newPixels);
    }

    /**
     * Helper method to get RGB pixel data from a BufferedImage.
     *
     * @param image the BufferedImage from which to extract RGB pixel data
     * @return an array of integers representing the RGB pixel values
     */
    private int[] getRGBPixels(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
        return pixels;
    }

    /**
     * Test to verify that the correct map difficulty is returned by the TileManager.
     */
    @Test
    public void testGetMapDifficulty() {
        String expectedDifficulty = "Easy";
        TileManager tileManager = new TileManager(gamePanel, expectedDifficulty);

        assertEquals(expectedDifficulty, tileManager.getMapDifficulty());
    }

    /**
     * Test to verify that an IllegalArgumentException is thrown when attempting to change sprite with an invalid filename.
     */
    @Test
    public void testSetSpriteChangeWithIOException() {
        assertThrows(IllegalArgumentException.class, () -> {
            tileManager.setSpriteChange(0, "invalid_filename");
        });
    }
}
