package com.nickmillward.snake.screens;

import com.badlogic.gdx.Gdx;
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
 * Created by nmillward on 10/6/16.
 */
public class StartScreen extends AbstractScreen {

    private Skin skin;
    private Table table;
    private BitmapFont titleFont;
    private Label titleLabel;
    private Label.LabelStyle titleLableStyle;
    private TextButton startButton, easyButton, medButton, hardButton;
    private NinePatch startUpNine, difficultyUpNine;
    private TextButton.TextButtonStyle startButtonStyle, difficultyButtonStyle;

    public StartScreen() {
        super();
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN)); //down: button, up: button,
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));
        startUpNine = skin.getPatch("button");
        difficultyUpNine = skin.getPatch("button");
        startButtonStyle = new TextButton.TextButtonStyle();
        difficultyButtonStyle = new TextButton.TextButtonStyle();
    }

    @Override
    public void buildStage() {
        table = new Table();
        table.setWidth(super.getWidth());
//        table.setHeight(super.getHeight());
        table.align(Align.center | Align.top);
        table.setPosition(0, getHeight()); //Start at top left

        titleFont = Utils.generateFreeTypeFont(Constants.FONT_FISHFONT, 75, Color.CORAL);

        titleLableStyle = new Label.LabelStyle(titleFont, Color.CORAL);
        startButtonStyle.up = new NinePatchDrawable(startUpNine);
        startButtonStyle.font = Utils.generateFreeTypeFont(Constants.FONT_FISHFONT, 75, Color.BLACK);;
        difficultyButtonStyle.up = new NinePatchDrawable(difficultyUpNine);
        difficultyButtonStyle.font = Utils.generateFreeTypeFont(Constants.FONT_FISHFONT, 50, Color.BLACK);

        startButton = new TextButton(Constants.BUTTON_START_TEXT, startButtonStyle);

        titleLabel = new Label(Constants.GAME_TITLE, titleLableStyle);
        titleLabel.setFontScale(2);
        table.add(titleLabel).padTop(25);

        table.row();
        easyButton = new TextButton(Constants.EASY_DIFFICULTY_LABEL, difficultyButtonStyle);
        table.add(easyButton).padTop(10).width(startButton.getWidth() * 2/3).height(startButton.getHeight() * 3/4);

        table.row();
        medButton = new TextButton(Constants.MEDIUM_DIFFICULTY_LABEL, difficultyButtonStyle);
        table.add(medButton).padTop(10).width(startButton.getWidth() * 2/3).height(startButton.getHeight() * 3/4);

        table.row();
        hardButton = new TextButton(Constants.HARD_DIFFICULTY_LABEL, difficultyButtonStyle);
        table.add(hardButton).padTop(10).width(startButton.getWidth() * 2/3).height(startButton.getHeight() * 3/4);

        table.row();
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Start", "button clicked");
                ScreenManager.getInstance().showScreen(Enums.Screen.GAME_SCREEN);
                event.stop();
            }
        });
        table.add(startButton).padTop(50);

        addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
        skin.dispose();
        titleFont.dispose();
    }
}
