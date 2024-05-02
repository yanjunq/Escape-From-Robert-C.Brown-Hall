/*
import com.game.UI.UI;
import com.game.GamePanel.MainGamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;

    public class UITest {

        private UI ui;
        private MainGamePanel gamePanel;
        private Graphics2D graphics;

        @BeforeEach
        public void setUp() {
            gamePanel = new MainGamePanel();
            ui = new UI(gamePanel);
            BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
            graphics = image.createGraphics();
        }

        @Test
        public void testConstructor() {
            assertNotNull(ui);
            assertNotNull(ui.a40);
            assertNotNull(ui.a60);
            assertNotNull(ui.a80BIG);
            assertFalse(ui.gameDone);
        }

        @Test
        public void testShowMessage() {
            ui.showMessage("Test Message");
            assertTrue(ui.visibleText);
            assertEquals("Test Message", ui.words);
        }

        @Test
        public void testDrawGameNotDone() {
            ui.draw(graphics);
            assertFalse(ui.gameDone);
            assertEquals("Score = 0", graphics.toString());
        }

        @Test
        public void testDrawGameDone() {
            ui.gameDone = true;
            ui.draw(graphics);
            assertTrue(ui.gameDone);
            // Verify the drawing of the final score, completion message, and escape time
            // You can assert the expected output based on the implementation
        }
    }
*/
// unnecessary code


