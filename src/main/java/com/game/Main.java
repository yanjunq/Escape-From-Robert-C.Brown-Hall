package com.game;

import com.game.GamePanel.GamePanel;
import com.game.GamePanel.MainGamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The main class that initializes and displays the game's start menu.
 * <p>
 * This class is responsible for setting up the initial user interface of the game,
 * including loading the background image, creating menu buttons for difficulty selection,
 * and handling the game's start based on the selected difficulty. It also features an exit
 * button to close the game.
 */
public class Main {

    /**
     * The main method that serves as the entry point for the game application.
     * <p>
     * It sets up the main game windsow, including the background, buttons for difficulty selection,
     * and the exit button. It defines the action listeners for these buttons to start the game
     * with the chosen difficulty or exit the game.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame();
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Load background image
        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(Main.class.getResourceAsStream("/Title/title.jpg")); // Provide path to your image
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImagePanel backgroundPanel = new ImagePanel(backgroundImage);
        backgroundPanel.setLayout(new BorderLayout());

        // Create panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(130, 0, 0, 0)); // Add top padding
        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");

        // Make buttons bigger, white, and square
        Dimension buttonSize = new Dimension(120, 120); // Set width and height to the same value for square buttons
        Color buttonColor = Color.WHITE;
        easyButton.setPreferredSize(buttonSize);
        easyButton.setFont(new Font("Arial", Font.BOLD, 30));
        easyButton.setBackground(buttonColor);
        easyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add black border for a square shape

        mediumButton.setPreferredSize(buttonSize);
        mediumButton.setFont(new Font("Arial", Font.BOLD, 30));
        mediumButton.setBackground(buttonColor);
        mediumButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add black border for a square shape

        hardButton.setPreferredSize(buttonSize);
        hardButton.setFont(new Font("Arial", Font.BOLD, 30));
        hardButton.setBackground(buttonColor);
        hardButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add black border for a square shape

        // Add action listeners to the buttons
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("Easy");
            }
        });
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("Medium");
            }
        });
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("Hard");
            }
        });

        buttonPanel.setOpaque(false);
        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);

        // Create exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 18)); // Set font and size as desired
        exitButton.setForeground(Color.WHITE); // Set text color
        exitButton.setBackground(Color.RED); // Set background color
        exitButton.setFocusPainted(false); // Remove focus border
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Create bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 0)); // Add bottom padding
        bottomPanel.setOpaque(false); // Make the panel transparent
        bottomPanel.add(exitButton);

        // Add components to the background panel
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        backgroundPanel.add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel with exit button

        mainPanel.add(backgroundPanel);
        window.setContentPane(mainPanel);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Lost in RCB");
        window.setSize(864, 864); // Set window size
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }


    /**
     * Starts the game with the selected difficulty level.
     * <p>
     * This method initializes the game environment and begins the game based on the chosen
     * difficulty from the main menu. It sets up a new window for the game and loads the game
     * panel configured with the selected difficulty.
     *
     * @param difficulty The difficulty level chosen by the player ("Easy", "Medium", or "Hard").
     */
    private static void startGame(String difficulty) {
        // Implement your game startup logic here based on difficulty
        System.out.println("Starting game with difficulty: " + difficulty);
        JFrame gameWindow = new JFrame();
        GamePanel gamePanel = new MainGamePanel();
        gamePanel.startGame(difficulty);

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Lost in RCB");         // Placeholder name - can be updated later
        gameWindow.add(gamePanel);
        gameWindow.pack();

        gameWindow.setLocationRelativeTo(null);     // centers window in screen
        gameWindow.setVisible(true);
    }
}

/**
 * A custom JPanel subclass for displaying a background image.
 * <p>
 * This panel is used in the game's main menu to display a background image. It overrides
 * the {@code paintComponent} method to draw the background image scaled to fill the panel.
 */
class ImagePanel extends JPanel {
    private Image backgroundImage;

    /**
     * Constructs an ImagePanel with a specified background image.
     *
     * @param backgroundImage The image to set as the background.
     */
    public ImagePanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * Paints the panel's background image to fit its current size.
     * <p>
     * This method overrides the {@code paintComponent} method of {@link JPanel} to
     * draw the specified background image across the entire area of the panel. It
     * scales the image to match the panel's width and height, ensuring the background
     * fully covers the panel. The method is called automatically by the Swing framework
     * whenever the panel needs to be redrawn, such as after being resized or made visible.
     *
     * @param g The {@link Graphics} context used for drawing operations.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}