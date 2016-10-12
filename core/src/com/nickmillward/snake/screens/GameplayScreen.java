package com.nickmillward.snake.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.nickmillward.snake.Level;
import com.nickmillward.snake.MobileControls;
import com.nickmillward.snake.overlays.SnakeHUD;
import com.nickmillward.snake.utils.Assets;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Enums;
import com.nickmillward.snake.utils.ScreenManager;

/**
 * Created by nmillward on 9/7/16.
 */
public class GameplayScreen extends AbstractScreen {

    public static final String TAG = GameplayScreen.class.getName();

    MobileControls mobileControls;
    GestureDetector gestureDetector;
    SpriteBatch batch;
    Level level;
    private SnakeHUD snakeHUD;
//    private GameOverOverlay gameOverOverlay;

    public GameplayScreen() {
        super();
        AssetManager assetManager = new AssetManager();
        Assets.instance.init(assetManager);
        level = new Level();
        batch = new SpriteBatch();
        snakeHUD = new SnakeHUD();
        mobileControls = new MobileControls(level);
    }

    @Override
    public void buildStage() {

    }

    @Override
    public void show() {
//        gameOverOverlay = new GameOverOverlay();

        if (onMobile()) {
            gestureDetector = new GestureDetector(mobileControls);
            Gdx.input.setInputProcessor(gestureDetector);
        }
    }

    @Override
    public void resize(int width, int height) {
        level.viewport.update(width, height, true);
        snakeHUD.viewport.update(width, height, true);
//        gameOverOverlay.viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
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
            level.resetGame();
            ScreenManager.getInstance().showScreen(Enums.Screen.RESTART_SCREEN, level.getHighScore());
        }
    }

    /**
     * Check if the user is on a mobile device
     */
    public boolean onMobile() {
        return Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS;
    }
}
