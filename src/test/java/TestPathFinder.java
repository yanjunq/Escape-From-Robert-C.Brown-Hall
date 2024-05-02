import com.game.Characters.Enemy;
import com.game.Characters.EnemyMovement.PathFinder;
import com.game.GamePanel.MainGamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the functionality of the {@link PathFinder} class.
 * This includes verifying the ability of the pathfinding algorithm to find a path
 * under various conditions, such as different distances and obstacles.
 */
public class TestPathFinder {

    private MainGamePanel gamePanel;
    private Enemy enemy;
    private PathFinder pathFinder;

    /**
     * Sets up the testing environment before each test.
     * Initializes game panel, sets up the game, and prepares the enemy and pathfinder instances.
     */
    @BeforeEach
    public void setUp() {
        gamePanel = new MainGamePanel();
        gamePanel.setupGame("Medium");
        enemy = gamePanel.getEnemy();
        pathFinder = gamePanel.pathFinder;
    }

    /**
     * Tests the pathfinder's ability to find a path over a short distance.
     * Verifies that a path is correctly identified between two nearby points.
     */
    @Test
    public void testEnemySearchToGoalWithShortDistance() {
        int startCol = 1;
        int startRow = 1;
        int goalCol = 4;
        int goalRow = 4;

        enemy.setPosition(startCol * gamePanel.tileSize, startRow * gamePanel.tileSize);
        pathFinder.setNode(startCol, startRow, goalCol, goalRow, enemy);
        boolean pathFound = pathFinder.search();
        assertTrue(pathFound);
    }

    /**
     * Tests the pathfinder's ability to find a path over a long distance.
     * Verifies that a path is correctly identified between two distant points.
     */
    @Test
    public void testEnemySearchToGoalWithLongDistance() {
        int startCol = 1;
        int startRow = 1;
        int goalCol = 25;
        int goalRow = 16;

        enemy.setPosition(startCol * gamePanel.tileSize, startRow * gamePanel.tileSize);
        pathFinder.setNode(startCol, startRow, goalCol, goalRow, enemy);
        boolean pathFound = pathFinder.search();
        assertTrue(pathFound);
    }

    /**
     * Tests the pathfinder's ability to find a path when there is an obstacle.
     * Verifies that the pathfinder can navigate around obstacles to find a path.
     */
    @Test
    public void testSearchWithCollsion() {
        int startCol = 1;
        int startRow = 1;
        int goalCol = 27;
        int goalRow = 16;

        enemy.setPosition(startCol * gamePanel.tileSize, startRow * gamePanel.tileSize);
        pathFinder.setNode(startCol, startRow, goalCol, goalRow, enemy);
        boolean pathFound = pathFinder.search();
        assertTrue(pathFound);
    }

    /**
     * Tests the pathfinder when the starting position and goal position are the same.
     * This should simulate the condition where the hero and enemy are on the same position,
     * and verify that no path needs to be found.
     */
    @Test
    public void testSearchWithPositionThatHeroAndEnemyOnTheSamePosition() {
        pathFinder.goalReached = true;
        int startCol = 0;
        int startRow = 0;
        int goalCol = 0;
        int goalRow = 0;

        enemy.setPosition(startCol * gamePanel.tileSize, startRow * gamePanel.tileSize);
        pathFinder.setNode(startCol, startRow, goalCol, goalRow, enemy);
        boolean pathFound = !pathFinder.search();
        assertTrue(pathFound);
    }
}

