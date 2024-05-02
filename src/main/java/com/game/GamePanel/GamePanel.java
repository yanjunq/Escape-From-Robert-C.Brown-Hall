package com.game.GamePanel;

import com.game.Characters.*;
import com.game.GameTerminator.GameTerminator;
import com.game.Items.Item;
import com.game.Tile.TileManager;
import com.game.UI.UI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public abstract class GamePanel extends JPanel implements Runnable, ScreenSetting{


    protected Hero hero;
    protected Enemy enemy;
    public Item[] item; // item slots - dictates how many items can be displayed at one time

    public UI ui;
    public TileManager tileM;
    protected int timeElapsedSec;

    public boolean running = false;
    public Thread thread;
    protected int timeElapsed;    // time elapsed since game started in seconds
    public Graphics2D g2;
    public GameTerminator gameTerminator;

    public Hero getHero() {return this.hero;}
    public Enemy getEnemy() {return this.enemy;}
    public Item[] getItem() {return this.item;}
    public int getTimeElapsedSec() {return timeElapsedSec;}

    public abstract void startGame(String diff);
    public abstract void reSpawnAPlusPaperPosition();


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    /**
     * Updates the game state, including player, enemy movements, and interactions.
     * <p>
     * This method is called within the game loop to handle game logic updates, such as character
     * movements, collision detection, and interactions between game objects.
     *
     * @throws IOException if there is an error loading resources.
     */
    public void update() throws IOException {
        hero.update();
        enemy.update();
    }

    public boolean isRunning() {
        return running;
    }
}
