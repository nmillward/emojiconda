package com.nickmillward.snake.screens;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Enums;
import com.nickmillward.snake.utils.ScreenManager;
import com.nickmillward.snake.utils.Utils;

/**
 * Created by nmillward on 10/9/16.
 */
public class RestartScreen extends AbstractScreen implements Screen {

    private Skin skin;
    private Table table;
    private Label highScoreLabelText, highScoreLabelNum;
    private Button playButton, homeButton;
    private BitmapFont font;
    private Button.ButtonStyle homeButtonStyle, playButtonStyle;
    private Label.LabelStyle labelStyle;
    private TextureRegion gameOverTexture;
    private int highScore;
    private Image gameOver;
    private Enums.Difficulty difficulty;

    public RestartScreen(int highScore, Enums.Difficulty difficulty) {
        super();
        this.highScore = highScore;
        this.difficulty = difficulty;
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN));
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));
        homeButtonStyle = new Button.ButtonStyle();
        playButtonStyle = new Button.ButtonStyle();
    }

    @Override
    public void buildStage() {
        table = new Table(skin);
        table.setWidth(getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, getHeight());

        font = Utils.generateFreeTypeFont(Constants.FONT_FISHFONT, 75, Color.WHITE);


        table.row();
        gameOverTexture = new TextureRegion(skin.getRegion(Constants.TITLE_GAME_OVER));
//        Texture gameOverTexture = new Texture(Gdx.files.internal("images/txt_gameover.png"));
        gameOver = new Image(new TextureRegionDrawable(new TextureRegion(gameOverTexture)));
//        gameOver.setDrawable(new TextureRegionDrawable(new TextureRegion(gameOverTexture)));
        gameOver.setSize(gameOverTexture.getRegionWidth(), gameOverTexture.getRegionHeight());
        gameOver.setScaling(Scaling.fit);
        table.add(gameOver).padTop(25);

        table.row();
        labelStyle = new Label.LabelStyle(font, Color.WHITE);
        highScoreLabelText = new Label(Constants.HIGH_SCORE_LABEL, labelStyle);
        highScoreLabelText.setFontScale(2);
        table.add(highScoreLabelText).padTop(25);
        table.row();
        highScoreLabelNum = new Label(highScore + "", labelStyle);
        highScoreLabelNum.setFontScale(2);
        table.add(highScoreLabelNum).padTop(10);

        table.row();
        homeButton = new Button(homeButtonStyle);
        homeButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_HOME)));
        homeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(Enums.Screen.START_SCREEN);
                event.stop();
            }
        });
        table.add(homeButton).padTop(100);

        table.row();
        playButton = new Button(playButtonStyle);
        playButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_PLAY)));
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(Enums.Screen.GAME_SCREEN, difficulty);
                event.stop();
            }
        });
        table.add(playButton).padTop(25);

        addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
        skin.dispose();
    }
}
