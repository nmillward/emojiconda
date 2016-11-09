package com.nickmillward.snake.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.screens.GameplayScreen;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Enums;
import com.nickmillward.snake.utils.Utils;

/**
 * Created by nmillward on 9/26/16.
 */
public class SnakeHUD {

    GameplayScreen screen;

    public final Viewport viewport;
    final BitmapFont font;

    public Stage stage;
    private SpriteBatch batch;
    private Label scoreLabel;
    private Button pauseButton;
    private Skin skin;
    private int score;

    public SnakeHUD(final GameplayScreen screen, SpriteBatch batch) {
        this.screen = screen;
        this.batch = batch;
        this.viewport = new ExtendViewport(Constants.SNAKE_HUD_VIEWPORT_SIZE, Constants.SNAKE_HUD_VIEWPORT_SIZE);
        font = Utils.generateFreeTypeFont(Constants.FONT_TITAN, 24, Color.WHITE);
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN));
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));

        score = 0;

        stage = new Stage(viewport, batch);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.setTransform(true);
        table.toFront();

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        scoreLabel = new Label(Constants.SNAKE_HUD_SCORE_LABEL + score, labelStyle);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        pauseButton = new TextButton(Constants.PAUSE_BUTTON_TEXT, textButtonStyle);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("SNAKE HUD", "PAUSE BUTTON CLICKED");
                screen.setGameState(Enums.GAME_STATE.PAUSE);
                event.stop();
            }
        });

//        ScaleToAction scaleScore = new ScaleToAction();
//        scaleScore.setScale(1.5f);
//        scaleScore.setDuration(5.0f);
//        table.addAction(scaleScore);

        table.add(scoreLabel).expandX().padTop(Constants.SNAKE_HUD_MARGIN);
        table.add(pauseButton).expandX().padTop(Constants.SNAKE_HUD_MARGIN);

        stage.addActor(table);
//        stage.act();

        Gdx.input.setInputProcessor(stage);
    }


    public void updateScore(int score) {
        this.score = score;
        scoreLabel.setText(Constants.SNAKE_HUD_SCORE_LABEL + score);
    }

}
