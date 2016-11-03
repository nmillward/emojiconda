package com.nickmillward.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.entities.Food;
import com.nickmillward.snake.entities.Snake;
import com.nickmillward.snake.screens.GameplayScreen;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Enums;

/**
 * Created by nmillward on 9/7/16.
 */
public class Level {

    public static final String TAG = Level.class.getName();

    Preferences scorePref = Gdx.app.getPreferences(Constants.PREF_HIGH_SCORE);

    GameplayScreen screen;
    public Viewport viewport;
    public Snake snake;
    public Food food;
    public boolean isGameOver;
    public int currentScore = 0;
    public int highScore = scorePref.getInteger(Constants.KEY_HIGH_SCORE, 0);
    public Enums.Difficulty difficulty;

    public Level(GameplayScreen screen, Enums.Difficulty difficulty) {
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        this.screen = screen;
        this.difficulty = difficulty;
        initGameDefault();
    }

    public void update(float delta) {
        if (!isGameOver) {
            snake.update(delta);
            checkGameOver();
            food.snakeCollisionWithSnack();
        }
    }

    public void render(SpriteBatch batch) {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        snake.render(batch);
        food.render(batch);
        batch.end();
    }

    public void checkGameOver() {
        if (snake.getXofHead() < 0 || snake.getXofHead() > viewport.getWorldWidth() - Constants.SNAKE_SEGMENT_DEFAULT_SIZE) {
            isGameOver = true;
            screen.setGameState(Enums.GAME_STATE.STOP);
        }
        if (snake.getYofHead() < 0 || snake.getYofHead() > viewport.getWorldHeight() - Constants.SNAKE_SEGMENT_DEFAULT_SIZE) {
            isGameOver = true;
            screen.setGameState(Enums.GAME_STATE.STOP);
        }
        if (snake.snakeCollision()) {
            isGameOver = true;
            screen.setGameState(Enums.GAME_STATE.STOP);
        }
    }

    public void initGameDefault() {
        isGameOver = false;
        snake = new Snake(this);
        food = new Food(snake, this);
    }

    public void resetGame() {
        if (currentScore > highScore) {
            highScore = currentScore;
            scorePref.putInteger(Constants.KEY_HIGH_SCORE, highScore);
            scorePref.flush();
        }
        resetCurrentScore();
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void incrementCurrentScore(int pointValue) {
        currentScore += pointValue;
    }

    public void resetCurrentScore() {
        currentScore = 0;
    }

    public Enums.Difficulty getDifficulty() {
        return difficulty;
    }
}
