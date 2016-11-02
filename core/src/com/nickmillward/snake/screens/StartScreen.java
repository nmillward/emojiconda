package com.nickmillward.snake.screens;

import com.badlogic.gdx.Gdx;
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
    private Enums.Difficulty difficulty;
    private Button.ButtonStyle easyButtonStyle, medButtonStyle, hardButtonStyle, startButtonStyle;

    public StartScreen() {
        super();
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN)); //down: button, up: button,
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));
        difficulty = Enums.Difficulty.MEDIUM;

        easyButtonStyle = new Button.ButtonStyle();
        medButtonStyle = new Button.ButtonStyle();
        hardButtonStyle = new Button.ButtonStyle();
        startButtonStyle = new Button.ButtonStyle();

    }
    
    @Override
    public void buildStage() {
        table = new Table();
        table.setFillParent(true);
        table.center();

        Image title_snake = new Image();
        title_snake.setDrawable(new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.TITLE_SNAKE))));
        title_snake.setSize(300, 200);

        easyButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_EASY_OFF)));
        easyButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_EASY_ON)));
        Button btn_easy = new Button(easyButtonStyle);
        btn_easy.align(Align.left);
        btn_easy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difficulty = Enums.Difficulty.EASY;
                event.stop();
            }
        });

        medButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_MEDIUM_OFF)));
        medButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_MEDIUM_ON)));
        Button btn_medium = new Button(medButtonStyle);
        btn_medium.align(Align.center);
        btn_medium.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difficulty = Enums.Difficulty.MEDIUM;
                event.stop();
            }
        });

        hardButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_HARD_OFF)));
        hardButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_HARD_ON)));
        Button btn_hard = new Button(hardButtonStyle);
        btn_hard.align(Align.right);
        btn_hard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difficulty = Enums.Difficulty.HARD;
                event.stop();
            }
        });

        BitmapFont font = Utils.generateFreeTypeFont(Constants.FONT_TITAN, 50, Color.WHITE);
        Label.LabelStyle difficultyLabelStyle = new Label.LabelStyle(font, Color.YELLOW);
        Label difficultyLabel = new Label(getDifficulty().toString(), difficultyLabelStyle);

        BitmapFont highScoreFont = Utils.generateFreeTypeFont(Constants.FONT_TITAN, 30, Color.WHITE);
        Label.LabelStyle highScoreLabelStyle = new Label.LabelStyle(highScoreFont, Color.WHITE);
        Label highScoreLabel = new Label(Constants.HIGH_SCORE_LABEL + "100", highScoreLabelStyle);  //TODO: pull in saved high score

        startButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_PLAY)));
        Button btn_start = new Button(startButtonStyle);
        btn_start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(Enums.Screen.GAME_SCREEN, difficulty);
                event.stop();
            }
        });

        table.setBackground(new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.IMG_BACKGROUND))));

        table.add(title_snake).pad(50).colspan(3).expand().center();

        table.row();
        table.add(btn_easy).padLeft(50).colspan(1);
        table.add(btn_medium).padLeft(10).padRight(10).colspan(1);
        table.add(btn_hard).padRight(50).colspan(1);

        table.row();
        table.add(difficultyLabel).pad(25).colspan(3);

        table.row();
        table.add(highScoreLabel).pad(50).colspan(3);

        table.row();
        table.add(btn_start).pad(50).colspan(3).expand().center();

        addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
        skin.dispose();
    }

    public Enums.Difficulty getDifficulty() {
        return difficulty;
    }
}
