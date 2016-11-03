package com.nickmillward.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Enums;
import com.nickmillward.snake.utils.ScreenManager;
import com.nickmillward.snake.utils.Utils;

/**
 * Created by nmillward on 10/9/16.
 */
public class RestartScreen extends AbstractScreen implements Screen {

    Preferences scorePref = Gdx.app.getPreferences(Constants.PREF_HIGH_SCORE);

    private Skin skin;
    private BitmapFont font;
    private Button.ButtonStyle homeButtonStyle, playButtonStyle;
    private int highScore;
    private Enums.Difficulty difficulty;

    public RestartScreen(Enums.Difficulty difficulty) {
        super();
        this.highScore = scorePref.getInteger(Constants.KEY_HIGH_SCORE, 0);
        this.difficulty = difficulty;
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN));
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));
        homeButtonStyle = new Button.ButtonStyle();
        playButtonStyle = new Button.ButtonStyle();
    }

    @Override
    public void buildStage() {
        Table table = new Table(skin);
        table.setFillParent(true);
        table.center();

        font = Utils.generateFreeTypeFont(Constants.FONT_TITAN, 40, Color.WHITE);

        TextureRegion gameOverTexture = new TextureRegion(skin.getRegion(Constants.TITLE_GAME_OVER));
        Image gameOver = new Image(new TextureRegionDrawable(new TextureRegion(gameOverTexture)));
        gameOver.setSize(gameOverTexture.getRegionWidth(), gameOverTexture.getRegionHeight());
        gameOver.setScaling(Scaling.fit);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        Label highScoreLabelText = new Label(Constants.HIGH_SCORE_LABEL, labelStyle);
        highScoreLabelText.setFontScale(2);

        Label highScoreLabelNum = new Label(highScore + "", labelStyle);
        highScoreLabelNum.setFontScale(2);

        Button homeButton = new Button(homeButtonStyle);
        homeButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_HOME)));
        homeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(Enums.Screen.START_SCREEN);
                event.stop();
            }
        });

        Button playButton = new Button(playButtonStyle);
        playButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_PLAY)));
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(Enums.Screen.GAME_SCREEN, difficulty);
                event.stop();
            }
        });

        table.add(gameOver).pad(50).expand().center();

        table.row();
        table.add(highScoreLabelText).pad(10).padTop(25);

        table.row();
        table.add(highScoreLabelNum).pad(10).padBottom(25);

        table.row();
        table.add(homeButton).padTop(100).expand();

        table.row();
        table.add(playButton).padBottom(100).expand();

        addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
        skin.dispose();
    }
}
