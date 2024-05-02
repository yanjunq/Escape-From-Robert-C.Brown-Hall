package com.game.GamePanel;

public interface ScreenSetting {
    int originalTileSize = 16;
    int scale = 3;
    int tileSize = originalTileSize * scale;   // 48x48 tile (due to scaling)
    int maxScreenCol = 28;        // Changed according to UI mockup - range of columns: 0-27
    int maxScreeRow = 18;         // Changed according to UI mockup - range of rows: 0-17
    int screenWidth = tileSize * maxScreenCol;    // (48*28) = 1,344 pixels
    int screenHeight = tileSize * maxScreeRow ;  // (48*18) = 864 pixels
    int FPS = 60;

}
