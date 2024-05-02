package com.game.Utilities;

import com.game.GamePanel.MainGamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Provides utility functions for common operations, such as image scaling.
 * <p>
 * This class includes methods to perform various utility operations that are frequently
 * needed across different parts of the game, such as adjusting the size of images to fit
 * specific dimensions without altering their aspect ratio.
 */
public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage image, int w, int h){

        /**
         * Scales an image to the specified width and height.
         * <p>
         * This method takes a {@link BufferedImage} and resizes it to the given dimensions,
         * returning the resized image. It ensures that the scaled image retains the original
         * image's properties and quality.
         *
         * @param image The image to be scaled.
         * @param w The desired width of the scaled image.
         * @param h The desired height of the scaled image.
         * @return A new {@link BufferedImage} instance scaled to the specified dimensions.
         */
        BufferedImage Image = new BufferedImage(w,h,image.getType());
        Graphics2D i2 = Image.createGraphics();
        i2.drawImage(image, 0,0,w,h,null);
        i2.dispose();

        return Image;
    }

    /**
     * Sets the image for the character based on the provided name.
     *
     * @param name the name of the image resource
     * @return the scaled BufferedImage of the character
     */
    public BufferedImage setImage(String name, MainGamePanel gamePanel){
        BufferedImage image = null;
        UtilityTool uTool = new UtilityTool();
        try{
            image = ImageIO.read(getClass().getResourceAsStream( name + ".png"));
            image = uTool.scaleImage(image, gamePanel.tileSize,gamePanel.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

}
