package com.nickmillward.snake;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.entities.Emoji;
import com.nickmillward.snake.entities.Snake;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 9/7/16.
 */
public class Level {

    public static final String TAG = Level.class.getName();

    public Viewport viewport;
    public Array<Emoji> emojis;

    Snake snake;

    public Level() {
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

        emojis = new Array<Emoji>();
//        debugLevel();

        snake = new Snake();
    }

    private void debugLevel() {
        emojis.add(new Emoji(this, new Vector2(50, 50)));
        emojis.add(new Emoji(this, new Vector2(50, 50 - Constants.EMOJI_DEFAULT_SIZE)));
        emojis.add(new Emoji(this, new Vector2(50, 50 - Constants.EMOJI_DEFAULT_SIZE * 2)));
        emojis.add(new Emoji(this, new Vector2(50, 50 - Constants.EMOJI_DEFAULT_SIZE * 3)));
    }

    public void update(float delta) {
//        for (Emoji emoji : emojis) {
//            emoji.update(delta);
//        }
        snake.update(delta);
    }

    public void render(SpriteBatch batch) {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

//        for (Emoji emoji : emojis) {
//            emoji.render(batch);
//        }

        snake.render(batch);
        batch.end();
    }
}
