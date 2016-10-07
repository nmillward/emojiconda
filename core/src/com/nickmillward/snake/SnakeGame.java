package com.nickmillward.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.nickmillward.snake.screens.StartScreen;

public class SnakeGame extends Game {
	@Override
	public void create () {
		showStartScreen();
	}

	public void showStartScreen() {
		setScreen(new StartScreen(this));
	}

	public void showGameplayScreen() {
		Gdx.app.log("GAME", "Gameplay Screen called");
		setScreen(new GameplayScreen(this));
	}
}
