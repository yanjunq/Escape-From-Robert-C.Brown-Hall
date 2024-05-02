import com.game.GamePanel.MainGamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class MainGamePanelTest {
    private MainGamePanel gamePanel;

    @BeforeEach
    void setUp(){
        gamePanel = new MainGamePanel();
    }

    /**
     * Test that the game is not running initially, starts correctly, updates the hero's position,
     * and the enemy's movement when the hero is at the left top side of the enemy.
     * The game should also terminate correctly.
     * @throws InterruptedException if the thread sleep is interrupted
     */
    @Test
    void runShouldUpdateAndRenderEnemyMovementWhenHeroOnTheLeftTopSideOfEnemy() throws InterruptedException {

        assertFalse(gamePanel.isRunning());
        gamePanel.getHero().setPosition(2*gamePanel.tileSize, 4*gamePanel.tileSize);
        gamePanel.startGame("Medium");
        gamePanel.thread.sleep(4000);

        assertTrue(gamePanel.getHero().update());
        assertTrue(gamePanel.getEnemy().update());
        gamePanel.gameTerminator.endGame();
        assertFalse(gamePanel.isRunning());

    }

    /**
     * Test that the game is not running initially, starts correctly, and updates both the hero's
     * and the enemy's positions when the hero is at the right bottom side of the enemy.
     * The game should also terminate correctly.
     * @throws InterruptedException if the thread sleep is interrupted
     */
    @Test
    void runShouldUpdateAndRenderEnemyMovementWhenHeroOnTheRightBottomSideOfEnemy() throws InterruptedException {

        assertFalse(gamePanel.isRunning());
        gamePanel.getEnemy().setPosition(2*gamePanel.tileSize, 2*gamePanel.tileSize);
        gamePanel.startGame("Medium");
        gamePanel.thread.sleep(1000);
        gamePanel.getHero().setPosition(14*gamePanel.tileSize, 4*gamePanel.tileSize);
        assertTrue(gamePanel.getHero().update());
        assertTrue(gamePanel.getEnemy().update());

        gamePanel.gameTerminator.endGame();
        assertFalse(gamePanel.isRunning());
    }

    /**
     * Test that the {@code paintComponent} method executes without throwing exceptions
     * by drawing onto a {@link BufferedImage}'s graphics object.
     */
    @Test
    void paintComponentShouldDrawWithoutErrors() {

        gamePanel.setPreferredSize(new Dimension(800, 600));
        gamePanel.setSize(gamePanel.getPreferredSize());

        BufferedImage bufferedImage = new BufferedImage(
                gamePanel.getWidth(),
                gamePanel.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics graphics = bufferedImage.getGraphics();
        assertDoesNotThrow(() -> gamePanel.paintComponent(graphics),
                "The paintComponent method should not throw an exception."
        );

        graphics.dispose();
    }

}

