package com.nickmillward.snake;

import com.badlogic.gdx.Game;
import com.nickmillward.snake.utils.Enums;
import com.nickmillward.snake.utils.ScreenManager;

public class SnakeGame extends Game {

	@Override
	public void create () {
//		showStartScreen();
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(Enums.Screen.START_SCREEN);
	}

//	public void showStartScreen() {
//		setScreen(new StartScreen(this));
//	}
//
//	public void showGameplayScreen() {
//		setScreen(new GameplayScreen(this));
//	}
//
//	public void showRestartScreen() {
//		setScreen(new RestartScreen(this));
//	}
}
