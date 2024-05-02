import com.game.Characters.Enemy;
import com.game.Characters.Hero;
import com.game.GamePanel.MainGamePanel;
import com.game.GameTerminator.DefaultGameTerminator;
import com.game.GameTerminator.GameTerminator;
import com.game.Key.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests the exit and termination logic of the game,
 * ensuring that the game ends appropriately under certain conditions,
 * such as a collision between the hero and the enemy.
 */
public class TestExitGame {

    private MainGamePanel gamePanel;
    private Enemy enemy;
    private Hero hero;

    /**
     * Sets up the game environment before each test, including mocking the game terminator
     * to check interactions without affecting the actual game state.
     */
    @BeforeEach
    void setUp() {
        this.gamePanel = new MainGamePanel();
        gamePanel.setupGame("Easy");
        hero = gamePanel.getHero();
        enemy = gamePanel.getEnemy();
        gamePanel.gameTerminator = mock(GameTerminator.class); // Mock the GameTerminator
    }

    /**
     * Tests that the game exits when the hero and enemy collide.
     * Verifies that the game termination process is invoked in such scenarios.
     */
    @Test
    public void testExitGameAfterCollisionBetweenHeroAndEnemy() {
        hero.setPosition(gamePanel.tileSize, gamePanel.tileSize);
        enemy.setPosition(gamePanel.tileSize, enemy.getMovementSpeed() + gamePanel.tileSize);
        enemy.setCurrentDirection(Direction.UP);

        enemy.checkCollision();
        verify(gamePanel.gameTerminator).terminate(); // Verify terminate was called
    }

    /**
     * Tests that the game correctly sets its state to not running and nullifies the game thread
     * when the game ends.
     */
    @Test
    public void testEndGameSetsGameStateCorrectly() {
        DefaultGameTerminator terminator = new DefaultGameTerminator(gamePanel);
        terminator.endGame();
        assertFalse(gamePanel.running); // Game should not be running after endGame is called
        assertNull(gamePanel.thread); // Thread should be null after endGame is called
    }

    /**
     * Tests that calling terminate on a game terminator results in the game being ended correctly,
     * ensuring that the game's running state is set to false and the game thread is nullified.
     */
    @Test
    public void testTerminateCallsEndGame() {
        GameTerminator terminator = new DefaultGameTerminator(gamePanel) {
            @Override
            public void terminate() {
                endGame();
            }
        };

        terminator.terminate();
        assertFalse(gamePanel.running);
        assertNull(gamePanel.thread);
    }
}


