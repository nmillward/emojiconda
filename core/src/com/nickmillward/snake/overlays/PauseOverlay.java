package com.nickmillward.snake.overlays;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Utils;

/**
 * Created by nmillward on 10/17/16.
 */
public class PauseOverlay {

    public final Viewport viewport;
    final BitmapFont font;
    ShapeRenderer shapeRenderer;

    public PauseOverlay() {
        this.viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        font = Utils.generateFreeTypeFont(Constants.FONT_FISHFONT, 96, Color.BLACK);
        shapeRenderer = new ShapeRenderer();
    }

    public void render(SpriteBatch batch) {
        viewport.apply();

        // SCORE TEXT
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        final String pauseText = "PAUSED";
        font.draw(batch, pauseText, viewport.getWorldWidth() / 2, viewport.getWorldHeight() * 3/4);
        batch.end();

    }

}
