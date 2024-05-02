import com.game.Characters.Enemy;
import com.game.GamePanel.MainGamePanel;
import com.game.Key.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the behavior of the Enemy's pathfinding system in different scenarios
 * to ensure it accurately adjusts its direction based on the hero's location and obstacles in the game.
 */
public class TestSearchPath {

    private MainGamePanel gamePanel;
    private Enemy enemy;

    /**
     * Sets up the game and enemy objects before each test to ensure a consistent testing environment.
     */
    @BeforeEach
    void setUp() {
        gamePanel = new MainGamePanel();
        gamePanel.setupGame("Easy");
        enemy = gamePanel.getEnemy();
    }

    /**
     * Test to ensure the enemy correctly adjusts its direction to up or right when the hero is positioned
     * diagonally at the upper right from the enemy's location.
     */
    @Test
    void testAdjustedEnemyDirectionWhenHeroStandOnRightUpPositionFromEnemy() {
        enemy.setPosition(2 * gamePanel.tileSize, 14 * gamePanel.tileSize);
        int goalCol = 5;
        int goalRow = 5;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.UP || enemy.getCurrentDirection() == Direction.RIGHT);
    }

    /**
     * Test to ensure the enemy correctly adjusts its direction to up or left when the hero is positioned
     * diagonally at the upper left from the enemy's location.
     */
    @Test
    void testAdjustedEnemyDirectionWhenHeroStandOnLeftUpPositionFromEnemy() {
        enemy.setPosition(10 * gamePanel.tileSize, 14 * gamePanel.tileSize);
        int goalCol = 5;
        int goalRow = 5;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.UP || enemy.getCurrentDirection() == Direction.LEFT);
    }

    /**
     * Test to ensure the enemy correctly adjusts its direction to down or right when the hero is positioned
     * diagonally at the lower right from the enemy's location.
     */
    @Test
    void testAdjustedEnemyDirectionWhenHeroStandOnRightDownPositionFromEnemy() {
        enemy.setPosition(2 * gamePanel.tileSize, 5 * gamePanel.tileSize);
        int goalCol = 5;
        int goalRow = 14;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.DOWN || enemy.getCurrentDirection() == Direction.RIGHT);
    }

    /**
     * Test to ensure the enemy correctly adjusts its direction to down or left when the hero is positioned
     * diagonally at the lower left from the enemy's location.
     */
    @Test
    void testAdjustedEnemyDirectionWhenHeroStandOnLeftDownPositionFromEnemy() {
        enemy.setPosition(6 * gamePanel.tileSize, 5 * gamePanel.tileSize);
        int goalCol = 7;
        int goalRow = 14;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.DOWN || enemy.getCurrentDirection() == Direction.RIGHT);
    }

    /**
     * Test to ensure the enemy adjusts its direction to left when the hero is positioned directly to the left
     * of the enemy's location.
     */
    @Test
    void testAdjustedEnemyDirectionWhenHeroStandOnLeftPositionFromEnemy() {
        enemy.setPosition(6 * gamePanel.tileSize, 5 * gamePanel.tileSize);
        int goalCol = 2;
        int goalRow = 5;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.LEFT);
    }

    /**
     * Test to ensure the enemy adjusts its direction to right when the hero is positioned directly to the right
     * of the enemy's location.
     */
    @Test
    void testAdjustedEnemyDirectionWhenHeroStandOnRightPositionFromEnemy() {
        enemy.setPosition(2 * gamePanel.tileSize, 5 * gamePanel.tileSize);
        int goalCol = 6;
        int goalRow = 5;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.RIGHT);
    }

    /**
     * Test to ensure the enemy adjusts its direction to up when the hero is positioned directly above
     * the enemy's location.
     */
    @Test
    void testAdjustedEnemyDirectionWhenHeroStandOnUpPositionFromEnemy() {
        enemy.setPosition(2 * gamePanel.tileSize, 5 * gamePanel.tileSize);
        int goalCol = 2;
        int goalRow = 2;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.UP);
    }

    /**
     * Test to ensure the enemy adjusts its direction to down when the hero is positioned directly below
     * the enemy's location.
     */
    @Test
    void testAdjustedEnemyDirectionWhenHeroStandOnDownPositionFromEnemy() {
        enemy.setPosition(2 * gamePanel.tileSize, 2 * gamePanel.tileSize);
        int goalCol = 2;
        int goalRow = 5;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.DOWN);
    }

    /**
     * Test to ensure the enemy adjusts its direction to up or left when there's an obstacle on the upper side,
     * and the hero is positioned diagonally on the upper left side.
     */
    @Test
    void testAdjustedEnemyDirectionWithObstacleOnUpSideAndHeroOnLeftUpSide() {
        enemy.setPosition(5 * gamePanel.tileSize - 2, 6 * gamePanel.tileSize - 1);
        int goalCol = 4;
        int goalRow = 4;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.UP || enemy.getCurrentDirection() == Direction.LEFT);
    }

    /**
     * Test to ensure the enemy adjusts its direction to up or left when there's an obstacle on the left side,
     * and the hero is positioned diagonally on the upper left side.
     */
    @Test
    void testAdjustedEnemyDirectionWithObstacleOnLeftSideAndHeroOnLeftUpSide() {
        enemy.setPosition(5 * gamePanel.tileSize, 10 * gamePanel.tileSize - 1);
        int goalCol = 4;
        int goalRow = 9;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.UP || enemy.getCurrentDirection() == Direction.LEFT);
    }

    /**
     * Test to ensure the enemy adjusts its direction to up or right when there's an obstacle on the upper side,
     * and the hero is positioned diagonally on the upper right side.
     */
    @Test
    void testAdjustedEnemyDirectionWithObstacleOnUpSideAndHeroOnRightUpSide() {
        enemy.setPosition(7 * gamePanel.tileSize + 2, 6 * gamePanel.tileSize - 1);
        int goalCol = 8;
        int goalRow = 5;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.UP || enemy.getCurrentDirection() == Direction.RIGHT);
    }

    /**
     * Test to ensure the enemy adjusts its direction to up or right when there's an obstacle on the right side,
     * and the hero is positioned diagonally on the upper right side.
     */
    @Test
    void testAdjustedEnemyDirectionWithObstacleOnRightSideAndHeroOnRightUpSide() {
        enemy.setPosition(6 * gamePanel.tileSize + 2, 7 * gamePanel.tileSize - 1);
        int goalCol = 7;
        int goalRow = 6;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.UP || enemy.getCurrentDirection() == Direction.RIGHT);
    }

    /**
     * Test to ensure the enemy adjusts its direction to down or left when there's an obstacle on the left side,
     * and the hero is positioned diagonally on the lower left side.
     */
    @Test
    void testAdjustedEnemyDirectionWithObstacleOnLeftSideAndHeroOnLeftDownSide() {
        enemy.setPosition(8 * gamePanel.tileSize - 1, 5 * gamePanel.tileSize + 2);
        int goalCol = 7;
        int goalRow = 6;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.DOWN || enemy.getCurrentDirection() == Direction.LEFT);
    }

    /**
     * Test to ensure the enemy adjusts its direction to down or left when there's an obstacle on the downside,
     * and the hero is positioned diagonally on the lower left side.
     */
    @Test
    void testAdjustedEnemyDirectionWithObstacleOnDownSideAndHeroOnLeftDownSide() {
        enemy.setPosition(11 * gamePanel.tileSize - 1, 4 * gamePanel.tileSize + 2);
        int goalCol = 10;
        int goalRow = 5;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.DOWN || enemy.getCurrentDirection() == Direction.LEFT);
    }

    /**
     * Test to ensure the enemy adjusts its direction to down or left when there's an obstacle on the downside,
     * and the hero is positioned diagonally on the lower right side.
     */
    @Test
    void testAdjustedEnemyDirectionWithObstacleOnDownSideAndHeroOnRightDownSide() {
        enemy.setPosition(7 * gamePanel.tileSize + 10, 4 * gamePanel.tileSize);
        int goalCol = 8;
        int goalRow = 5;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.DOWN || enemy.getCurrentDirection() == Direction.LEFT);
    }

    /**
     * Test to ensure the enemy adjusts its direction to down or right when there's an obstacle on the downside,
     * and the hero is positioned diagonally on the lower right side.
     */
    @Test
    void testAdjustedEnemyDirectionWithObstacleOnDownSideAndHeroOnRightDownSide2() {
        enemy.setPosition(8 * gamePanel.tileSize + 2, 7 * gamePanel.tileSize);
        int goalCol = 9;
        int goalRow = 8;

        enemy.searchPath(goalCol, goalRow);
        assertTrue(enemy.getCurrentDirection() == Direction.DOWN || enemy.getCurrentDirection() == Direction.RIGHT);
    }

    /**
     * Test to ensure the enemy adjusts its direction to down or right when there's an obstacle on the right side,
     * and the hero is positioned diagonally on the lower right side.
     */
    @Test
    void testAdjustedEnemyDirectionWithObstacleOnRightSideAndHeroOnRightDownSide() {
        enemy.setPosition(9 * gamePanel.tileSize + 2, 4 * gamePanel.tileSize);
        int goalCol = 10;
        int goalRow = 5;

        enemy.searchPath(goalCol, goalRow);

        boolean isDirectionCorrect = enemy.getCurrentDirection() == Direction.DOWN || enemy.getCurrentDirection() == Direction.RIGHT;
        assertTrue(isDirectionCorrect);
    }

}

