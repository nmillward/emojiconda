package com.nickmillward.snake.overlays;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 9/26/16.
 */
public class SnakeHUD {

    public final Viewport viewport;
    final BitmapFont font;

    public SnakeHUD() {
        this.viewport = new ExtendViewport(Constants.SNAKE_HUD_VIEWPORT_SIZE, Constants.SNAKE_HUD_VIEWPORT_SIZE);
        font = new BitmapFont();
        font.getData().setScale(1);
    }

    public void render(SpriteBatch batch, int score) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        final String scoreText = Constants.SNAKE_HUD_SCORE_LABEL + score;

        font.draw(batch, scoreText, Constants.SNAKE_HUD_MARGIN, viewport.getWorldHeight() - Constants.SNAKE_HUD_MARGIN);

        batch.end();
    }
}
