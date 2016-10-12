package com.nickmillward.snake.utils;

import com.badlogic.gdx.Screen;
import com.nickmillward.snake.SnakeGame;
import com.nickmillward.snake.screens.AbstractScreen;

/**
 * Created by nmillward on 10/11/16.
 */
public class ScreenManager {

    private SnakeGame game;

    // Singleton - unique instance
    private static ScreenManager instance;

    private ScreenManager() {
        super();
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void initialize(SnakeGame game) {
        this.game = game;
    }

    // Use enum to retrieve screen
    public void showScreen(Enums.Screen screenEnum, Object... params) {

        // Get current screen to dispose of
        Screen currentScreen = game.getScreen();

        // Show new screen
        AbstractScreen newScreen = screenEnum.getScreen(params);
        newScreen.buildStage();
        game.setScreen(newScreen);

        // Dispose old screen
        if (currentScreen != null) {
            currentScreen.dispose();
        }

    }

}
