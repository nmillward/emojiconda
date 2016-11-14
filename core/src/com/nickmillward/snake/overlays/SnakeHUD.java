package com.nickmillward.snake.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    private Label scoreLabel;
    private Button pauseButton;
    private Skin skin;
    private int score;

    public SnakeHUD(final GameplayScreen screen) {
        this.screen = screen;
        this.viewport = new ExtendViewport(Constants.SNAKE_HUD_VIEWPORT_SIZE, Constants.SNAKE_HUD_VIEWPORT_SIZE);
        font = Utils.generateFreeTypeFont(Constants.FONT_TITAN, 24, Color.WHITE);
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN));
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));

        score = 0;

        stage = new Stage(viewport);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.setTransform(true);
        table.toFront();

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        scoreLabel = new Label(Constants.SNAKE_HUD_SCORE_LABEL + score, labelStyle);

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_PAUSE)));

        pauseButton = new Button(buttonStyle);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("SNAKE HUD", "PAUSE BUTTON CLICKED");
                screen.setGameState(Enums.GAME_STATE.PAUSE);
                event.stop();
            }
        });

        table.add(scoreLabel).expandX().padTop(Constants.SNAKE_HUD_MARGIN);
        table.add(pauseButton).maxHeight(40).maxWidth(100).expandX().padTop(Constants.SNAKE_HUD_MARGIN);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }


    public void updateScore(int score) {
        this.score = score;
        scoreLabel.setText(Constants.SNAKE_HUD_SCORE_LABEL + score);
    }

}
