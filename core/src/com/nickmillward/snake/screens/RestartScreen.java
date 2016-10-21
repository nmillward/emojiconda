package com.nickmillward.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
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
    private Label highScoreLabel;
    private TextButton restartButton;
    private BitmapFont font;
    private int highScore;
    private NinePatch resetUpNine;
    private TextButton.TextButtonStyle textButtonStyle;
    private Label.LabelStyle labelStyle;

    public RestartScreen(int highScore) {
        super();
        this.highScore = highScore;
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN));
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));
        resetUpNine = skin.getPatch("button");
        textButtonStyle = new TextButton.TextButtonStyle();
    }

    @Override
    public void buildStage() {
        table = new Table();
        table.setWidth(getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, getHeight());

        font = Utils.generateFreeTypeFont(Constants.FONT_FISHFONT, 75, Color.BLACK);

        textButtonStyle.up = new NinePatchDrawable(resetUpNine);
        textButtonStyle.font = font;
        restartButton = new TextButton(Constants.BUTTON_RESTART_TEXT, textButtonStyle);

        labelStyle = new Label.LabelStyle(font, Color.BLACK);

        highScoreLabel = new Label(Constants.HIGH_SCORE_LABEL + highScore, labelStyle);
        highScoreLabel.setFontScale(2);
        table.add(highScoreLabel).padTop(100);

        table.row();
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(Enums.Screen.GAME_SCREEN);
                event.stop();
            }
        });

        table.add(restartButton).padTop(100);
        addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
        skin.dispose();
    }
}
