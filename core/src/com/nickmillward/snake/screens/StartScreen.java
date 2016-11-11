package com.nickmillward.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
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

    Preferences scorePref = Gdx.app.getPreferences(Constants.PREF_HIGH_SCORE);
    Preferences firstPref = Gdx.app.getPreferences(Constants.PREF_FIRST_TIME);

    private Skin skin;
    private Enums.Difficulty difficulty;
    private BitmapFont font;
    private Label difficultyLabel;
    private Button btn_easy, btn_medium, btn_hard, btn_start;
    private Button.ButtonStyle easyButtonStyle, medButtonStyle, hardButtonStyle, startButtonStyle;
    private int highScore;
    private boolean isFirstTime;

    public StartScreen() {
        super();
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN));
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));

        difficulty = Enums.Difficulty.MEDIUM;

        easyButtonStyle = new Button.ButtonStyle();
        medButtonStyle = new Button.ButtonStyle();
        hardButtonStyle = new Button.ButtonStyle();
        startButtonStyle = new Button.ButtonStyle();

        highScore = scorePref.getInteger(Constants.KEY_HIGH_SCORE, 0);
        isFirstTime = firstPref.getBoolean(Constants.KEY_FIRST_TIME, true);
    }

    @Override
    public void buildStage() {
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        Image title_snake = new Image();
        title_snake.setDrawable(new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.TITLE_SNAKE))));
        title_snake.setSize(300, 200);

        font = Utils.generateFreeTypeFont(Constants.FONT_TITAN, 15, Color.WHITE);
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        Label subtitleLabelText = new Label(Constants.SUBTITLE_LABEL, labelStyle);
        subtitleLabelText.setFontScale(2);
        subtitleLabelText.setAlignment(Align.center);

        easyButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_EASY_OFF)));
        easyButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_EASY_ON)));
        btn_easy = new Button(easyButtonStyle);
        btn_easy.align(Align.left);
        if (!btn_easy.isChecked()) {
            btn_easy.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!btn_easy.isDisabled()) {
                        difficulty = Enums.Difficulty.EASY;
                        difficultyLabel.setText(getDifficulty().toString());

                        btn_easy.isChecked();
                        btn_medium.setChecked(false);
                        btn_hard.setChecked(false);

                        btn_medium.setDisabled(false);
                        btn_hard.setDisabled(false);
                    }
                    btn_easy.setDisabled(true);
                    event.stop();
                }
            });
        }

        medButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_MEDIUM_OFF)));
        medButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_MEDIUM_ON)));
        btn_medium = new Button(medButtonStyle);
        btn_medium.setStyle(medButtonStyle);
        btn_medium.align(Align.center);
        if (!btn_medium.isChecked()) {
            btn_medium.setChecked(true);
            btn_medium.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!btn_medium.isDisabled()) {
                        difficulty = Enums.Difficulty.MEDIUM;
                        difficultyLabel.setText(getDifficulty().toString());

                        btn_medium.isChecked();
                        btn_easy.setChecked(false);
                        btn_hard.setChecked(false);

                        btn_easy.setDisabled(false);
                        btn_hard.setDisabled(false);
                    }
                    btn_medium.setDisabled(true);
                    event.stop();
                }
            });
        }

        hardButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_HARD_OFF)));
        hardButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_HARD_ON)));
        btn_hard = new Button(hardButtonStyle);
        btn_hard.align(Align.right);
        if (!btn_hard.isChecked()) {
            btn_hard.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (!btn_hard.isDisabled()) {
                        difficulty = Enums.Difficulty.HARD;
                        difficultyLabel.setText(getDifficulty().toString());

                        btn_hard.isChecked();
                        btn_easy.setChecked(false);
                        btn_medium.setChecked(false);

                        btn_easy.setDisabled(false);
                        btn_medium.setDisabled(false);
                    }
                    btn_hard.setDisabled(true);
                    event.stop();
                }
            });
        }

        BitmapFont font = Utils.generateFreeTypeFont(Constants.FONT_TITAN, 50, Color.WHITE);
        Label.LabelStyle difficultyLabelStyle = new Label.LabelStyle(font, Color.YELLOW);
        difficultyLabel = new Label(getDifficulty().toString(), difficultyLabelStyle);

        BitmapFont highScoreFont = Utils.generateFreeTypeFont(Constants.FONT_TITAN, 30, Color.WHITE);
        Label.LabelStyle highScoreLabelStyle = new Label.LabelStyle(highScoreFont, Color.WHITE);
        Label highScoreLabel = new Label(Constants.HIGH_SCORE_LABEL + highScore, highScoreLabelStyle);

        startButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_PLAY)));
        btn_start = new Button(startButtonStyle);

        final ScaleToAction scaleScore = Actions.action(ScaleToAction.class);
        scaleScore.setScale(1.5f);
        scaleScore.setDuration(5.0f);

        btn_start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(Enums.Screen.GAME_SCREEN, difficulty);
                btn_start.addAction(scaleScore);
                event.stop();
            }
        });

        table.setBackground(new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.IMG_BACKGROUND))));

        table.add(title_snake).padTop(100).padLeft(50).padRight(50).padBottom(10).colspan(3).center();

        table.row();
        table.add(subtitleLabelText).padLeft(50).padRight(50).padBottom(100).colspan(3).center();

        table.row();
        table.add(btn_easy).padLeft(50).colspan(1);
        table.add(btn_medium).padLeft(10).padRight(10).colspan(1);
        table.add(btn_hard).padRight(50).colspan(1);

        table.row();
        table.add(difficultyLabel).pad(25).colspan(3);

        table.row();
        table.add(highScoreLabel).padTop(50).padBottom(70).colspan(3);

        table.row();
        table.add(btn_start).colspan(3).expand().center();

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
