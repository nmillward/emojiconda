package com.nickmillward.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nickmillward.snake.SnakeGame;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 10/6/16.
 */
public class StartScreen extends ScreenAdapter {

    SnakeGame game;

    private Skin skin;
    private Stage stage;
    private Table table;
    private TextButton startButton;
    private ScreenViewport viewPort;
    private TextureRegion menuButton;

    public StartScreen(SnakeGame game) {
        this.game = game;
    }

    @Override
    public void show() {

        viewPort = new ScreenViewport();
        stage = new Stage(viewPort);
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN)); //down: button, up: button,
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight()); //Start at top left

        startButton = new TextButton(Constants.BUTTON_START_TEXT, skin);
        startButton.setWidth(stage.getWidth() / 4);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Start", "button clicked");
                game.showGameplayScreen();
                event.stop();
            }
        });

        table.add(startButton).padTop(100);

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
