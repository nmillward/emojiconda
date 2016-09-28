package com.nickmillward.snake;

import com.badlogic.gdx.Game;
import com.nickmillward.snake.screens.DifficultyScreen;

public class SnakeGame extends Game {
	@Override
	public void create () {
		setScreen(new GameplayScreen());
	}
}
