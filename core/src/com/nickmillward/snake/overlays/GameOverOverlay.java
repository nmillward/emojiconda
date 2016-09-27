package com.nickmillward.snake.overlays;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 9/27/16.
 */
public class GameOverOverlay {

    public final Viewport viewport;
    final BitmapFont font;

    public GameOverOverlay() {
        this.viewport = new ExtendViewport(Constants.GAME_OVER_OVERLAY_VIEWPORT_SIZE, Constants.GAME_OVER_OVERLAY_VIEWPORT_SIZE);
        font = new BitmapFont();
        font.getData().setScale(2);
    }

    public void render(SpriteBatch batch, int score) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        final String highScoreText = Constants.HIGH_SCORE_LABEL + score;

        font.draw(batch, highScoreText, viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2);

        batch.end();
    }
}
