package com.nickmillward.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nickmillward.snake.Level;
import com.nickmillward.snake.SnakeGame;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 10/9/16.
 */
public class RestartScreen extends ScreenAdapter {

    SnakeGame game;
    Level level;

    private Skin skin;
    private Stage stage;
    private Table table;
    private Label highScore;
    private TextButton restartButton;
    private ScreenViewport viewport;
    private BitmapFont font;

    public RestartScreen(SnakeGame game) {
        this.game = game;
    }

    @Override
    public void show() {

        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN));
        font = new BitmapFont();

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        highScore = new Label(Constants.HIGH_SCORE_LABEL, skin); //  + level.getHighScore()
        table.add(highScore).padTop(100);

        table.row();    // Add New Row
        restartButton = new TextButton(Constants.BUTTON_RESTART_TEXT, skin);
        restartButton.setWidth(stage.getWidth() / 4);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.showGameplayScreen();
                event.stop();
            }
        });

        table.add(restartButton).padTop(100);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(
                Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
