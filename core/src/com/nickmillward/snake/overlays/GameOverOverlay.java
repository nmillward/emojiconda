package com.nickmillward.snake.overlays;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 9/27/16.
 */
public class GameOverOverlay extends InputAdapter {

    public final Viewport viewport;
    final BitmapFont fontLarge, fontSmall;
    ShapeRenderer renderer;

    public GameOverOverlay() {
        this.viewport = new ExtendViewport(Constants.GAME_OVER_OVERLAY_VIEWPORT_SIZE, Constants.GAME_OVER_OVERLAY_VIEWPORT_SIZE);
        fontLarge = new BitmapFont();
        fontLarge.getData().setScale(3);
        fontSmall = new BitmapFont();
        fontSmall.getData().setScale(1);
        renderer = new ShapeRenderer();
    }

    public void render(SpriteBatch batch, int score) {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        final String highScoreText = Constants.HIGH_SCORE_LABEL + score;
        fontLarge.draw(batch, highScoreText, viewport.getWorldWidth() / 3, viewport.getWorldHeight() / 2);
        batch.end();

//        renderer.begin(ShapeRenderer.ShapeType.Line);
//        renderer.setColor(Color.WHITE);
//        renderer.rect(
//                (viewport.getWorldWidth() / 2) - (viewport.getWorldWidth() / 8),
//                viewport.getWorldHeight() / 4,
//                viewport.getWorldWidth() / 4,
//                viewport.getWorldHeight() / 12
//        );
//        renderer.end();

        batch.begin();
        fontSmall.draw(
                batch,
                Constants.BUTTON_RESTART_TEXT,
                viewport.getWorldWidth() / 2,
                (viewport.getWorldHeight() / 4) + (viewport.getWorldHeight() / 16),
                0,
                Align.center,
                false
        );
        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
