package com.nickmillward.snake.overlays;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Utils;

/**
 * Created by nmillward on 9/26/16.
 */
public class SnakeHUD {

    public final Viewport viewport;
    final BitmapFont font;
    ShapeRenderer shapeRenderer;
    public Stage stage;
    private Table table;
    private TextButton pauseButton;
    private TextButton.TextButtonStyle textButtonStyle;
    private Label scoreLabel;
    private Label.LabelStyle labelStyle;
    private int score;

    public SnakeHUD(SpriteBatch batch) {
        this.viewport = new ExtendViewport(Constants.SNAKE_HUD_VIEWPORT_SIZE, Constants.SNAKE_HUD_VIEWPORT_SIZE);
        font = Utils.generateFreeTypeFont(Constants.FONT_FISHFONT, 24, Color.WHITE);
        shapeRenderer = new ShapeRenderer();

        score = 0;

        stage = new Stage(viewport, batch);
        table = new Table();
        table.top();
        table.setFillParent(true);

        labelStyle = new Label.LabelStyle(font, Color.WHITE);
        scoreLabel = new Label(Constants.SNAKE_HUD_SCORE_LABEL + score, labelStyle);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        pauseButton = new TextButton(Constants.PAUSE_BUTTON_TEXT, textButtonStyle);

        table.add(scoreLabel).expandX().padTop(Constants.SNAKE_HUD_MARGIN);
        table.add(pauseButton).expandX().padTop(Constants.SNAKE_HUD_MARGIN);

        stage.addActor(table);
    }



    public void updateScore(int score) {
        this.score = score;
        scoreLabel.setText(Constants.SNAKE_HUD_SCORE_LABEL + score);
    }

}
