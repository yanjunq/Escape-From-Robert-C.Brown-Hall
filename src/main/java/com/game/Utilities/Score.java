package com.game.Utilities;

/**
 * Defines the functionality for managing and manipulating score in the game.
 * <p>
 * This interface specifies methods for setting and getting scores, checking score-related conditions,
 * and adding points to the current score. Implementing classes will define specific behaviors for these
 * actions, allowing for flexible scoring mechanisms across different parts of the game.
 */
public interface Score {

    /**
     * Sets the current score to the specified value.
     * <p>
     * This method allows for directly setting the score, overriding any previous score value.
     *
     * @param score The score value to be set.
     */
    void  setScore(int score);

    /**
     * Retrieves the current score.
     * <p>
     * This method returns the current score value.
     *
     * @return The current score.
     */
    int getScore();

    /**
     * Checks specific conditions related to the score.
     * <p>
     * This method is intended to verify certain score-related conditions, such as whether a score threshold
     * has been met or exceeded. The exact conditions checked can vary based on the implementation.
     *
     * @return {@code true} if the conditions are met, {@code false} otherwise.
     */
    boolean checkScore();

    /**
     * Adds the specified number of points to the current score.
     * <p>
     * This method increases the current score by a given amount, allowing for positive score modifications.
     * Negative values can be used to decrease the score if desired.
     *
     * @param score The number of points to add to the current score.
     */
    void addScore(int score);

    // per UML: add HUD

    /*
     *  private Map currentMap;
     *  private HUD currentHUD;
     *  private int currentScore;
     *  private time timeElapsed; <- typo in UML?
     * 
     *  public HUD() {
     *  
     *  }
     * 
     *  public void displayHUD() {
     * 
     * 
     *  }
     */

     // per UML: add timeElapsed

     /*
      * private int secondsElapsed; 
      *
      * public timeElapsed() {
      *  
      * }
      *  
      * public void setTime() { // setter
      *
      * }
      *
      * public int getTime() { // getter
      *
      * }
      *
      *
      */

}