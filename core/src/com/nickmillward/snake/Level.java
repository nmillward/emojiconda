package com.nickmillward.snake;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.entities.Snake;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 9/7/16.
 */
public class Level {

    public static final String TAG = Level.class.getName();

    public Viewport viewport;
    public Snake snake;
    public boolean isGameOver;

    public Level() {
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        snake = new Snake();
        isGameOver = false;
    }


    public void update(float delta) {
        snake.update(delta);
        checkGameOver();
    }

    public void render(SpriteBatch batch) {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        snake.render(batch);
        batch.end();
    }

    public void checkGameOver() {
        if (snake.getXofHead() < 0 || snake.getXofHead() > Constants.WORLD_SIZE) {
            isGameOver = true;
        }
        if (snake.getYofHead() < 0 || snake.getYofHead() > Constants.WORLD_SIZE) {
            isGameOver = true;
        }
    }
}
