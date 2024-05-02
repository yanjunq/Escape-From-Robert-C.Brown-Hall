import com.game.GamePanel.MainGamePanel;
import com.game.Key.Direction;
import com.game.Key.KeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.event.KeyEvent;

/**
 * Tests the functionality of the {@link KeyHandler} class.
 * Ensures that key presses and releases are correctly interpreted
 * as movement directions and updates the state accordingly.
 */
public class TestKeyHandler {
    private MainGamePanel gamePanel;
    private KeyHandler keyHandler;

    /**
     * Sets up the test environment before each test.
     * This involves creating a mock {@link MainGamePanel} and a new {@link KeyHandler}.
     */
    @BeforeEach
    void setUp() {
        gamePanel = mock(MainGamePanel.class);
        keyHandler = new KeyHandler(gamePanel);
    }

    /**
     * Tests that the UP direction is correctly registered when the W key is pressed.
     */
    @Test
    void testKeyPressUP() {
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.getPressed(Direction.UP));
    }

    /**
     * Tests that the DOWN direction is correctly registered when the S key is pressed.
     */
    @Test
    void testKeyPressDown() {
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.getPressed(Direction.DOWN));
    }

    /**
     * Tests that the LEFT direction is correctly registered when the A key is pressed.
     */
    @Test
    void testKeyPressLeft() {
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.getPressed(Direction.LEFT));
    }

    /**
     * Tests that the RIGHT direction is correctly registered when the D key is pressed.
     */
    @Test
    void testKeyPressRight() {
        KeyEvent keyEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyHandler.keyPressed(keyEvent);
        assertTrue(keyHandler.getPressed(Direction.RIGHT));
    }

    /**
     * Tests that the key release functionality works correctly,
     * specifically ensuring that the UP direction is no longer registered after the W key is released.
     */
    @Test
    void testKeyRelease() {
        KeyEvent pressEvent = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyHandler.keyPressed(pressEvent);
        KeyEvent releaseEvent = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyHandler.keyReleased(releaseEvent);
        assertFalse(keyHandler.getPressed(Direction.UP));
    }

    /**
     * Tests the state of a specific key (D in this case) to ensure
     * it registers both the press and release actions correctly.
     */
    @Test
    void testSpecificKeyState() {
        KeyEvent pressEventD = new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyHandler.keyPressed(pressEventD);
        assertTrue(keyHandler.getPressed(Direction.RIGHT));

        KeyEvent releaseEventD = new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        keyHandler.keyReleased(releaseEventD);
        assertFalse(keyHandler.getPressed(Direction.RIGHT));
    }
}

