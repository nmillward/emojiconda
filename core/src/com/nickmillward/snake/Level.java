package com.nickmillward.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.Utils.Constants;

/**
 * Created by nmillward on 9/7/16.
 */
public class Level {

    public static final String TAG = Level.class.getName();

    Texture happyFace;
    public Viewport viewport;

    public Level() {
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        happyFace = new Texture("happy.png");
    }

    public void update(float delta) {

    }

    public void render(SpriteBatch batch) {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        batch.draw(happyFace, 0, 0, Constants.WORLD_SIZE * 0.5f, Constants.WORLD_SIZE * 0.5f);

        batch.end();
    }
}
