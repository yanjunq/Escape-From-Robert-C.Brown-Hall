package com.game.GamePanel;

import com.game.Utilities.AssetSetter;
import com.game.Characters.*;
import com.game.Characters.EnemyMovement.PathFinder;

import com.game.Utilities.CollisionChecker;
import com.game.GameTerminator.DefaultGameTerminator;
import com.game.Items.APlusPaper;
import com.game.Items.Item;
import com.game.Key.KeyHandler;
import com.game.Tile.TileManager;
import com.game.UI.UI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Manages the main game panel, rendering, and game loop.
 * <p>
 * This class extends {@link JPanel} and implements {@link Runnable} to manage the game's main loop,
 * rendering, and interactions. It initializes and updates game components, processes user input,
 * and handles rendering of the game world and UI elements.
 *
 */
public class MainGamePanel extends GamePanel{

    private int difficulty;
    public AssetSetter assetSetter = new AssetSetter(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public PathFinder pathFinder = new PathFinder(this);
    public TileManager tileM = new TileManager(this,"Easy"); //default
    KeyHandler keyHandler = new KeyHandler(this);

    /**
     * Initializes the game panel with default settings.
     * <p>
     * Sets up the game panel size, background color, double buffering, and input handling. It initializes
     * the hero and the enemy characters with default parameters.
     */
    public MainGamePanel(){ //not finished
        super();
        initialDefaultGamePanel();
        this.hero = new Hero(4,this.keyHandler,this);
        this.enemy = new Enemy(2,this); //default
        this.item = new Item[25];

    }

    public void initialDefaultGamePanel(){
        this.ui = new UI(this);
        gameTerminator = new DefaultGameTerminator(this);
        this.addKeyListener(keyHandler);
    }

    /**
     * Sets the game's difficulty level and initializes game components accordingly.
     * <p>
     * Depending on the difficulty level, different enemies are spawned, and other game parameters
     * are adjusted. This method also initializes the {@link TileManager}, {@link AssetSetter}, and
     * sets the initial enemy for the game based on the difficulty.
     *
     * @param diff A {@link String} representing the game's difficulty level.
     */
    public void  setupGame(String diff) {
        hero.diff = diff;
        tileM = new TileManager(this,diff);
        assetSetter.setObject(diff);
        setEnemy();
    }

    /**
     * Starts the game loop in a new thread.
     * <p>
     * This method checks if the game is already running to prevent multiple instances of the game loop.
     * It then starts a new thread that controls the game loop, updating and rendering the game state.
     */
    public void  startGame(String diff){
        //public synchronized void startGame
        if(running) return;
        running = true;

        setupGame(diff);
        thread = new Thread(this);
        thread.start();
    }

    public void reSpawnAPlusPaperPosition() {
        // Move the position of APlusPaper items every 10 seconds
        for(int i = 0; i < item.length; i++) {

            if(item[i] instanceof APlusPaper)
                ((APlusPaper)item[i]).reSpawnPosition();
        }
    }

    /**
     * Sets the enemy based on the current game difficulty level.
     * <p>
     * Depending on the difficulty level determined by the {@link TileManager}, this method
     * initializes the {@code enemy} field with a specific type of enemy. Different enemies
     * are assigned for different difficulty levels, each with its own speed setting.
     */
    public void setEnemy() {
        switch (tileM.getMapDifficulty()) {
            case "Easy" -> this.enemy = new ZombieProfessor(2, this);
            case "Medium" -> this.enemy = new Bear(3, this);
            case "Hard" -> this.enemy = new FailedExam(4, this);
        }
    }
    /**
     * Renders the game world and UI components to the screen.
     * <p>
     * This method overrides {@link JPanel#paintComponent(Graphics)} to custom draw the game's world and
     * UI elements. It is called as part of the swing repaint mechanism.
     *
     * @param g The {@link Graphics} context used for drawing.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g2 = (Graphics2D)g;

        tileM.draw(g2);
        // items on map
        for (Item item : item) {
            if (item != null) {
                item.draw(g2, (MainGamePanel) this);
            }
        }

        enemy.draw(g2);
        hero.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }


    /**
     * The main game loop that controls game updates and rendering.
     * <p>
     * This method runs in a separate thread and is responsible for the game's timing mechanism,
     * ensuring that updates and rendering occur at a consistent rate. It calculates the delta
     * time between frames to update game states and re-renders the game at a fixed rate. Additionally,
     * it handles periodic tasks such as updating item states and managing power-up effects' durations.
     */
    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double frameInterval = 1000000000.0 / FPS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        long start = System.currentTimeMillis();    // Used to calculate timeElapsed
        int updates = 0;
        timeElapsedSec = 0;
        int previousTimeElapsedSec = 0;

        while (running) {
            long now = System.nanoTime();
            long current = System.currentTimeMillis();  // Used to calculated timeElapsed
            delta += (now - lastTime) / frameInterval;
            lastTime = now;
            timeElapsedSec = (int) (current - start) / 1000;

            // Calls updateItemState() every 10 seconds that has elapsed
            if (previousTimeElapsedSec != timeElapsedSec && timeElapsedSec % 10 == 0) {
                reSpawnAPlusPaperPosition();
                previousTimeElapsedSec = timeElapsedSec;
            }

            // Used for ending speed modification effect from coffee object - original hero speed = 4
            if (hero.coffeeTimeEnd == timeElapsedSec) {
                hero.setMovementSpeed(4);
            }

            while (delta >= 1) {
                try {
                    this.update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                this.repaint();
                delta--;
                updates++;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                //System.out.println("FPS:" + updates); // for testing purposes
                updates = 0;
                timer += 1000; // Increment timer by 1 second
                timeElapsedSec++;
            }
        }
    }


}
