package com.nickmillward.snake;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.nickmillward.snake.overlays.GameOverOverlay;
import com.nickmillward.snake.overlays.SnakeHUD;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 9/7/16.
 */
public class GameplayScreen extends ScreenAdapter {

    public static final String TAG = GameplayScreen.class.getName();

    SnakeGame game;
    MobileControls mobileControls;
    GestureDetector gestureDetector;
    SpriteBatch batch;
    private Level level;
    private SnakeHUD snakeHUD;
    private GameOverOverlay gameOverOverlay;

    public GameplayScreen(SnakeGame game) { //TODO: Add difficulty parameter
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        level = new Level();
        snakeHUD = new SnakeHUD();
        gameOverOverlay = new GameOverOverlay();
        mobileControls = new MobileControls(level);

        if (onMobile()) {
            gestureDetector = new GestureDetector(mobileControls);
            Gdx.input.setInputProcessor(gestureDetector);
        }

    }

    @Override
    public void resize(int width, int height) {
        level.viewport.update(width, height, true);
        snakeHUD.viewport.update(width, height, true);
        gameOverOverlay.viewport.update(width, height, true);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {
        level.update(delta);

        Gdx.gl.glClearColor(
                Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        level.render(batch);

        snakeHUD.render(batch, level.getCurrentScore());

        if (level.isGameOver) {
            gameOverOverlay.render(batch, level.getHighScore());
        }
    }

    /**
     * Check if the user is on a mobile device
     */
    public boolean onMobile() {
        return Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS;
    }
}
