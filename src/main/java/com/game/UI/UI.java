package com.game.UI;

import com.game.GamePanel.MainGamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

/**
 * Manages and renders the user interface elements in the game.
 * <p>
 * This class is responsible for drawing UI components such as the score, game timer,
 * and temporary messages on the screen. It uses custom fonts and formatting to present
 * information clearly to the player during gameplay.
 */
public class UI{

    MainGamePanel gp;
    Font a40, a60, a80BIG;
    private boolean visibleText = false;
    private String words = "";
    private int wordsTime = 0;
    private double timer;
    DecimalFormat deForm = new DecimalFormat("#0.00");
    public boolean gameDone = false;
    private static final int MAX_WORDSTIME = 45;

    /**
     * Constructs a UI manager for a specific game panel.
     *
     * @param gp The game panel where UI elements are to be rendered.
     */
    public UI(MainGamePanel gp) {
        this.gp = gp;
        a40 = createFont("Arial", Font.PLAIN, 40);
        a60 = createFont("Arial", Font.BOLD, 60);
        a80BIG = createFont("Arial", Font.BOLD, 80);
    }

    /**
     * Creates a new font with the specified name, style, and size.
     *
     * @param name  the font name
     * @param style the font style (e.g., Font.PLAIN, Font.BOLD)
     * @param size  the font size
     * @return the newly created Font object
     */
    private Font createFont(String name, int style, int size) {
        return new Font(name, style, size);
    }

    /**
     * Displays a temporary message at a predetermined location on the screen.
     *
     * @param text The message text to display.
     */
    public void showMessage(String text) {
        words = text;
        visibleText = true;
    }

    /**
     * Draws UI elements such as the score, timer, and temporary messages on the screen.
     *
     * @param graphics The Graphics2D object used for drawing UI components.
     */
    public void draw(Graphics2D graphics) {

        if (gameDone) {

            graphics.setFont(a60);
            graphics.setColor(Color.YELLOW);

            String endtext;
            int endtextlen;
            int a;
            int b;

            endtext = "You got all the rewards and escaped RCB!";
            endtextlen = (int)graphics.getFontMetrics().getStringBounds(endtext, graphics).getWidth();

            a = gp.screenWidth/2 - endtextlen/2;
            b = gp.screenHeight/2 - (gp.tileSize*3);
            graphics.drawString(endtext, a, b);

            graphics.setColor(Color.ORANGE);

            endtext = "Your total score is " + gp.getHero().getScore() + " points.";
            endtextlen = (int)graphics.getFontMetrics().getStringBounds(endtext, graphics).getWidth();

            // a = gp.screenWidth/2 - endtextlen/2;
            b = gp.screenHeight/2 + (gp.tileSize*2);
            graphics.drawString(endtext, a, b);

            graphics.setFont(a80BIG);
            graphics.setColor(Color.GREEN);

            endtext = "You took " + deForm.format(timer) + "s to escape RCB.";
            endtextlen = (int)graphics.getFontMetrics().getStringBounds(endtext, graphics).getWidth();

            // a = gp.screenWidth/2 - endtextlen/2;
            b = gp.screenHeight/2 + (gp.tileSize*4);
            graphics.drawString(endtext, a, b);

        } else {

            graphics.setFont(a40);
            graphics.setColor(Color.WHITE);

            graphics.drawString("Score = " + gp.getHero().getScore(), 150, 40);

            timer += (double) 1 / 60;
            graphics.drawString("Time:" + deForm.format(timer), gp.tileSize * 20, 40);

            if (visibleText) {
                graphics.setFont(graphics.getFont().deriveFont(24F));
                graphics.drawString(words, gp.tileSize * 10, gp.tileSize * 11);

                wordsTime++;
                if (wordsTime > MAX_WORDSTIME) {
                    wordsTime = 0;
                    visibleText = false;
                }
            }
        }
    }
}
