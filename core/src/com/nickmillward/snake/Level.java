package com.nickmillward.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.entities.Food;
import com.nickmillward.snake.entities.Snake;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 9/7/16.
 */
public class Level {

    public static final String TAG = Level.class.getName();

    public Viewport viewport;
    public Snake snake;
    public Food food;
    public boolean isGameOver;

    public Level() {
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        snake = new Snake(this);
        food = new Food(snake);
        isGameOver = false;
    }


    public void update(float delta) {
        if (!isGameOver) {
            snake.update(delta);
            checkGameOver();
            food.snakeCollisionWithSnack();
        } else {
            //TODO: GAME OVER
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
            Gdx.app.log(TAG, "GAME OVER");
        }
        if (snake.getYofHead() < 0 || snake.getYofHead() > viewport.getWorldHeight() - Constants.SNAKE_SEGMENT_DEFAULT_SIZE) {
            isGameOver = true;
            Gdx.app.log(TAG, "GAME OVER");
        }
        if (snake.snakeCollision()) {
            isGameOver = true;
            Gdx.app.log(TAG, "GAME OVER");
        }
    }
}
