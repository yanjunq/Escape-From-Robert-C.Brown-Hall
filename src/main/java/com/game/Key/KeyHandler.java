package com.game.Key;

import com.game.GamePanel.MainGamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles keyboard input for the game, tracking the state of specific keys.
 * <p>
 * This class implements the {@link KeyListener} interface to manage keyboard events for the game.
 * It maintains the state of arrow keys or WASD as boolean values, allowing the game to check if
 * a specific directional input is currently active.
 *
 * @see KeyListener
 */
public class KeyHandler implements KeyListener{

    public Key up = new Key();    // Tracks the "up" or "W" key state
    public Key down = new Key();  // Tracks the "down" or "S" key state
    public Key left = new Key();  // Tracks the "left" or "A" key state
    public Key right = new Key(); // Tracks the "right" or "D" key state

    /**
     * Constructs a KeyHandler and associates it with a GamePanel to listen for key events.
     *
     * @param gp the {@link MainGamePanel} to which this KeyHandler will be attached.
     */
    public KeyHandler(MainGamePanel gp){
        gp.addKeyListener(this);
    }

    /**
     * Updates the state of a specific key based on keyboard events.
     * <p>
     * This method is called by {@code keyPressed} and {@code keyReleased} methods to toggle the state
     * of the directional keys based on the key event passed to it.
     *
     * @param e the {@link KeyEvent} associated with the key action.
     * @param pressed {@code true} if the key is pressed; {@code false} if released.
     */
    void update(KeyEvent e, boolean pressed){
        int k = e.getKeyCode();
        if(k == KeyEvent.VK_A) left.toggle(pressed);
        if(k == KeyEvent.VK_D) right.toggle(pressed);
        if(k == KeyEvent.VK_W) up.toggle(pressed);
        if(k == KeyEvent.VK_S) down.toggle(pressed);
    }

    /**
     * Checks if a specific directional key is pressed.
     * <p>
     * This method allows querying the state of a directional key, specified by a {@link Direction} enum,
     * to facilitate movement or other actions within the game based on keyboard input.
     *
     * @param d the {@link Direction} enum value representing the key to check.
     * @return {@code true} if the specified key is pressed, {@code false} otherwise.
     */
    public boolean getPressed(Direction d){
        return switch (d) {
            case UP -> up.getPressed();
            case DOWN -> down.getPressed();
            case LEFT -> left.getPressed();
            case RIGHT -> right.getPressed();
        };
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) { update(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) { update(e, false);
    }
}
