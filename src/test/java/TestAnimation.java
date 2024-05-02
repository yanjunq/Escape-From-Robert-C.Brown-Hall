import com.game.Characters.*;
import com.game.GamePanel.MainGamePanel;
import com.game.Key.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the animation functionality of game characters.
 * Ensures that characters display the correct images for different facing directions.
 */
public class TestAnimation {

    private Hero hero;
    private Enemy enemy;
    private MainGamePanel gamePanel;

    /**
     * Compares two images pixel by pixel to determine if they are identical.
     *
     * @param img1 The first image to compare.
     * @param img2 The second image to compare.
     * @return true if the images are equal, false otherwise.
     */
    public boolean areImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
            return false;
        }

        for (int x = 0; x < img1.getWidth(); x++) {
            for (int y = 0; y < img1.getHeight(); y++) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Sets up the test environment before each test.
     * Initializes the game panel, sets up the game, and creates hero and enemy characters.
     */
    @BeforeEach
    void setUp() {
        gamePanel = new MainGamePanel();
        gamePanel.setupGame("Easy");
        hero = gamePanel.getHero();
        enemy = gamePanel.getEnemy();
        gamePanel.g2 = (new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB)).createGraphics();
    }

    /**
     * Tests that the character image is correctly set when facing left.
     */
    @Test
    void testSetImageWhenCharacterFaceLeft() {
        hero.setCurrentDirection(Direction.LEFT);
        hero.draw(gamePanel.g2);
        assertTrue(areImagesEqual(hero.leftImage, hero.currentImage));

        enemy.setCurrentDirection(Direction.LEFT);
        enemy.draw(gamePanel.g2);
        assertTrue(areImagesEqual(enemy.leftImage, enemy.currentImage));
    }

    /**
     * Tests that the character image is correctly set when facing right.
     */
    @Test
    void testSetImageWhenCharacterFaceRight() {
        hero.setCurrentDirection(Direction.RIGHT);
        hero.draw(gamePanel.g2);
        assertTrue(areImagesEqual(hero.rightImage, hero.currentImage));

        enemy.setCurrentDirection(Direction.RIGHT);
        enemy.draw(gamePanel.g2);
        assertTrue(areImagesEqual(enemy.rightImage, enemy.currentImage));
    }

    /**
     * Tests that the character image is correctly set when facing up or down.
     * Assumes the last horizontal direction (left or right) determines the image used when facing vertically.
     */
    @Test
    void testSetImageWhenCharacterFaceUpOrDown() {
        hero.setCurrentDirection(Direction.UP);
        hero.draw(gamePanel.g2);
        assertTrue(areImagesEqual((hero.getLastDirection() == Direction.LEFT) ? hero.leftImage : hero.rightImage, hero.currentImage));

        enemy.setCurrentDirection(Direction.UP);
        enemy.draw(gamePanel.g2);
        assertTrue(areImagesEqual((enemy.getLastDirection() == Direction.LEFT) ? enemy.leftImage : enemy.rightImage, enemy.currentImage));
    }
}
