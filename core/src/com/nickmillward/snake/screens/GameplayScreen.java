package com.nickmillward.snake.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.nickmillward.snake.Level;
import com.nickmillward.snake.MobileControls;
import com.nickmillward.snake.overlays.PauseOverlay;
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
    private Enums.Difficulty difficulty;
    private Enums.GAME_STATE gameState;
    private SnakeHUD snakeHUD;
    private PauseOverlay pauseOverlay;
    private ShapeRenderer shapeRenderer;
    public boolean isPaused;

    public GameplayScreen(Enums.Difficulty difficulty) {
        super();
        this.difficulty = difficulty;
        AssetManager assetManager = new AssetManager();
        Assets.instance.init(assetManager);
        level = new Level(this, difficulty);
        batch = new SpriteBatch();
        snakeHUD = new SnakeHUD(this, batch);
        pauseOverlay = new PauseOverlay(this, batch);
        mobileControls = new MobileControls(level);
        gameState = Enums.GAME_STATE.RUN;
        isPaused = false;
    }

    @Override
    public void buildStage() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        if (onMobile()) {
            gestureDetector = new GestureDetector(mobileControls);
            Gdx.input.setInputProcessor(gestureDetector);
        }
    }

    @Override
    public void resize(int width, int height) {
        level.viewport.update(width, height, true);
        snakeHUD.viewport.update(width, height, true);
        pauseOverlay.viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(
                Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // BORDER
        shapeRenderer.setProjectionMatrix(getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Constants.BORDER_COLOR);
        shapeRenderer.rectLine(0, 0, 0, getHeight(), Constants.BORDER_WIDTH); // left
        shapeRenderer.rectLine(0, 0, getWidth(), 0, Constants.BORDER_WIDTH); // bottom
        shapeRenderer.rectLine(getWidth(), 0, getWidth(), getHeight(), Constants.BORDER_WIDTH); // right
        shapeRenderer.rectLine(0, getHeight(), getWidth(), getHeight(), Constants.BORDER_WIDTH); // top
        shapeRenderer.end();

        batch.setProjectionMatrix(snakeHUD.stage.getCamera().combined);
        batch.setProjectionMatrix(pauseOverlay.stage.getCamera().combined);

        switch (gameState) {
            case RUN:
                level.update(delta);
                level.render(batch);
                snakeHUD.updateScore(level.getCurrentScore());
                snakeHUD.stage.draw();
                pauseKeyPressed();
                break;

            case PAUSE:
                snakeHUD.stage.draw();
                level.render(batch);

                // Blend transparent rectangle for fade out effect
                Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
                Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(new Color(1, 1, 1, 0.3f));
                shapeRenderer.rect(0, 0, getWidth(), getHeight());
                shapeRenderer.end();
                Gdx.gl.glDisable(GL20.GL_BLEND);

                pauseOverlay.stage.draw();
                pauseKeyPressed();
                break;

            case STOP:
                level.resetGame();
                ScreenManager.getInstance().showScreen(Enums.Screen.RESTART_SCREEN, level.getHighScore(), difficulty);
                break;
        }
    }

    /**
     * Check if the user is on a mobile device
     */
    public boolean onMobile() {
        return Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS;
    }

    public void pauseKeyPressed() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (!isPaused) {
                gameState = Enums.GAME_STATE.PAUSE;
                isPaused = true;
            } else {
                gameState = Enums.GAME_STATE.RUN;
                isPaused = false;
            }
        }
    }

    public void setGameState(Enums.GAME_STATE gameState) {
        this.gameState = gameState;
    }
}
