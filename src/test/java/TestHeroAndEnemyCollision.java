import com.game.Characters.Enemy;
import com.game.Characters.Hero;
import com.game.Utilities.CollisionChecker;
import com.game.GamePanel.MainGamePanel;
import com.game.Key.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the collision detection between the hero and enemy characters in the game.
 * Ensures that the collision logic accurately detects overlaps under various scenarios.
 */
public class TestHeroAndEnemyCollision {

    private CollisionChecker collisionChecker;
    private MainGamePanel gamePanel;
    private String difficulty = "easy";
    private Hero hero;
    private Enemy enemy;

    /**
     * Sets up the test environment before each test case.
     * Initializes the game panel, characters, and collision checker for the tests.
     */
    @BeforeEach
    void setUp() {
        this.gamePanel = new MainGamePanel();
        this.collisionChecker = gamePanel.collisionChecker;
        gamePanel.setupGame(difficulty);
        hero = gamePanel.getHero();
        enemy = gamePanel.getEnemy();
    }

    /**
     * Tests collision detection when the hero is directly above the enemy.
     */
    @Test
    public void testCollisionWhenHeroOnTopOfEnemy() {
        hero.setPosition(gamePanel.tileSize, gamePanel.tileSize);
        enemy.setPosition(gamePanel.tileSize, enemy.getMovementSpeed() + gamePanel.tileSize);
        enemy.setCurrentDirection(Direction.UP);
        enemy.setAction();

        assertTrue(collisionChecker.checkCharacter(enemy, hero) != 999);
    }

    /**
     * Tests collision detection when the hero is directly below the enemy.
     */
    @Test
    public void testCollisionWhenHeroBelowOfEnemy() {
        hero.setPosition(gamePanel.tileSize, gamePanel.tileSize + enemy.getMovementSpeed());
        enemy.setPosition(gamePanel.tileSize, gamePanel.tileSize);
        enemy.setCurrentDirection(Direction.DOWN);
        enemy.setAction();

        assertTrue(collisionChecker.checkCharacter(enemy, hero) != 999);
    }

    /**
     * Tests collision detection when the hero is to the left of the enemy.
     */
    @Test
    public void testCollisionWhenHeroLeftOfEnemy() {
        hero.setPosition(gamePanel.tileSize, gamePanel.tileSize);
        enemy.setPosition(gamePanel.tileSize + enemy.getMovementSpeed(), gamePanel.tileSize);
        enemy.setCurrentDirection(Direction.LEFT);
        enemy.setAction();

        assertTrue(collisionChecker.checkCharacter(enemy, hero) != 999);
    }

    /**
     * Tests collision detection when the hero is to the right of the enemy.
     */
    @Test
    public void testCollisionWhenHeroRightOfEnemy() {
        enemy.setCurrentDirection(Direction.RIGHT);
        hero.setPosition(gamePanel.tileSize + enemy.getMovementSpeed(), gamePanel.tileSize);
        enemy.setPosition(gamePanel.tileSize, gamePanel.tileSize);
        enemy.setAction();

        assertTrue(collisionChecker.checkCharacter(enemy, hero) != 999);
    }
}
