import com.game.Characters.*;
import com.game.Utilities.CollisionChecker;
import com.game.GamePanel.MainGamePanel;
import com.game.Items.*;
import com.game.Key.KeyHandler;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the functionality related to scoring bonuses
 * when a hero picks up different items in the game.
 */
public class TestItemScoreBonus {
    private CollisionChecker collisionChecker;
    private MainGamePanel gamePanel;
    KeyHandler keyHandler;
    private static Hero hero;
    private Enemy enemy;
    private Item[] items;

    @BeforeEach
    public void setUp() {
        gamePanel = new MainGamePanel();
        keyHandler = new KeyHandler(gamePanel);
        hero = gamePanel.getHero();
        hero.getGamePanel().item[0] = new APlusPaper(gamePanel);
        hero.getGamePanel().item[1] = new Bed(gamePanel);
        hero.getGamePanel().item[2] = new Coffee(gamePanel);
        hero.getGamePanel().item[3] = new PileOfBooks(gamePanel);
        hero.getGamePanel().item[4] = new Vortex(gamePanel);
    }

    /**
     * Test case to verify the score increment when the hero picks up an APlusPaper item.
     */
    @Test
    public void heroPicksUpAPlusPaper() {
        int initialScore = gamePanel.getHero().getScore();
        hero.pickUpItem(0);
        int scoreAfterPickup = hero.getScore();
        int scoreDiff = scoreAfterPickup - initialScore;
        assertEquals(10, scoreDiff);
    }

    /**
     * Test case to verify the score increment when the hero picks up a Bed item.
     */
    @Test
    public void heroPicksUpBed() {
        int initialScore = gamePanel.getHero().getScore();
        hero.pickUpItem(1);
        int scoreAfterPickup = hero.getScore();
        int scoreDiff = scoreAfterPickup - initialScore;
        assertEquals(5, scoreDiff);
    }

    /**
     * Test case to verify the score increment when the hero picks up a Coffee item.
     */
    @Test
    public void heroPicksUpCoffee() {
        int initialScore = gamePanel.getHero().getScore();
        hero.pickUpItem(2);
        int scoreAfterPickup = hero.getScore();
        int scoreDiff = scoreAfterPickup - initialScore;
        assertEquals(5, scoreDiff);
    }

    /**
     * Test case to verify the score increment when the hero picks up a PileOfBooks item.
     */
    @Test
    public void heroPicksUpPileOfBooks() {
        int initialScore = gamePanel.getHero().getScore();
        hero.pickUpItem(3);
        int scoreAfterPickup = hero.getScore();
        int scoreDiff = scoreAfterPickup - initialScore;
        assertEquals(-5, scoreDiff);
    }

    /**
     * Test case to verify the score increment when the hero picks up a Vortex item.
     */
    @Test
    public void heroPicksUpVortex() {
        int initialScore = gamePanel.getHero().getScore();
        try {
            hero.pickUpItem(4); // This will throw an exception as it will attempt to move the hero position
        } catch (NullPointerException e) {
            // ignore the exception that is thrown
        }
        int scoreAfterPickup = hero.getScore();
        int scoreDiff = scoreAfterPickup - initialScore;
        assertEquals(-5, scoreDiff);
    }
}
