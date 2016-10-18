package com.nickmillward.snake.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
    ShapeRenderer shapeRenderer;

    public final Viewport viewport;
    final BitmapFont font;

    public Stage stage;
    private Label scoreLabel;
    private int score;

    public SnakeHUD(final GameplayScreen screen, SpriteBatch batch) {
        this.screen = screen;
        this.viewport = new ExtendViewport(Constants.SNAKE_HUD_VIEWPORT_SIZE, Constants.SNAKE_HUD_VIEWPORT_SIZE);
        font = Utils.generateFreeTypeFont(Constants.FONT_FISHFONT, 24, Color.WHITE);
        shapeRenderer = new ShapeRenderer();

        score = 0;

        stage = new Stage(viewport, batch);
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        scoreLabel = new Label(Constants.SNAKE_HUD_SCORE_LABEL + score, labelStyle);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        TextButton pauseButton = new TextButton(Constants.PAUSE_BUTTON_TEXT, textButtonStyle);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.setGameState(Enums.GAME_STATE.PAUSE);
                Gdx.app.log("SNAKE HUD", "PAUSE BUTTON CLICKED");
                event.stop();
            }
        });

        table.add(scoreLabel).expandX().padTop(Constants.SNAKE_HUD_MARGIN);
        table.add(pauseButton).expandX().padTop(Constants.SNAKE_HUD_MARGIN);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }


    public void updateScore(int score) {
        this.score = score;
        scoreLabel.setText(Constants.SNAKE_HUD_SCORE_LABEL + score);
    }

}
