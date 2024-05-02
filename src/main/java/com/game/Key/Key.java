package com.game.Key;

/**
 * Manages the state of an individual key input for the game.
 * <p>
 * This class tracks whether a specific key is currently pressed or not. It provides methods to
 * retrieve the current state of the key and to toggle this state, ensuring accurate input handling
 * within the game.
 */
public class Key {
    private boolean pressed = false;

    /**
     * Constructs a Key object with its initial pressed state set to false.
     */
    public Key(){}

    /**
     * Retrieves the current pressed state of the key.
     * <p>
     * This method allows checking whether the key is currently pressed, facilitating input
     * processing within the game.
     *
     * @return {@code true} if the key is pressed, {@code false} otherwise.
     */
    public boolean getPressed(){return this.pressed;}

    /**
     * Toggles the pressed state of the key.
     * <p>
     * If the new pressed state differs from the current state, this method updates the state
     * accordingly. This ensures that key press and release events are accurately reflected.
     *
     * @param pressed the new pressed state to set.
     */
    public void toggle(boolean pressed){
        if(pressed != this.pressed)  this.pressed = pressed;
    }

}
