import com.game.Characters.Hero;
import com.game.GamePanel.MainGamePanel;
import com.game.Key.KeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for validating the movement functionality of the {@link Hero} class.
 * Ensures that the hero character moves correctly in all directions.
 */
public class TestHeroMovement {

    private Hero hero;

    /**
     * Sets up the environment for each test method.
     * Initializes the game panel and hero character to be used in the tests.
     */
    @BeforeEach
    void setHero() {
        MainGamePanel gamePanel = new MainGamePanel();
        new KeyHandler(gamePanel); // KeyHandler initialized but not used directly in tests
        hero = gamePanel.getHero();
    }

    /**
     * Tests that the hero moves correctly to the right.
     * Checks if the hero's X position increases by the movement speed after moving right.
     */
    @Test
    void testHeroMoveRight() {
        int expectX = hero.getXPosition() + hero.getMovementSpeed();
        hero.moveRight(hero.getMovementSpeed());
        assertEquals(expectX, hero.getXPosition());
    }

    /**
     * Tests that the hero moves correctly to the left.
     * Checks if the hero's X position decreases by the movement speed after moving left.
     */
    @Test
    void testHeroMoveLeft() {
        int expectX = hero.getXPosition() - hero.getMovementSpeed();
        hero.moveLeft(hero.getMovementSpeed());
        assertEquals(expectX, hero.getXPosition());
    }

    /**
     * Tests that the hero moves correctly upwards.
     * Checks if the hero's Y position decreases by the movement speed after moving up.
     */
    @Test
    void testHeroMoveUp() {
        int expectY = hero.getYPosition() - hero.getMovementSpeed();
        hero.moveUp(hero.getMovementSpeed());
        assertEquals(expectY, hero.getYPosition());
    }

    /**
     * Tests that the hero moves correctly downwards.
     * Checks if the hero's Y position increases by the movement speed after moving down.
     */
    @Test
    void testHeroMoveDown() {
        int expectY = hero.getYPosition() + hero.getMovementSpeed();
        hero.moveDown(hero.getMovementSpeed());
        assertEquals(expectY, hero.getYPosition());
    }

}

