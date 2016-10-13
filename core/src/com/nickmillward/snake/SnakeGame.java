package com.nickmillward.snake;

import com.badlogic.gdx.Game;
import com.nickmillward.snake.utils.Enums;
import com.nickmillward.snake.utils.ScreenManager;

public class SnakeGame extends Game {

	@Override
	public void create () {
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(Enums.Screen.START_SCREEN);
	}
}
