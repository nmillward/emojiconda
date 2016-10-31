package com.nickmillward.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Enums;
import com.nickmillward.snake.utils.ScreenManager;

/**
 * Created by nmillward on 10/6/16.
 */
public class StartScreen extends AbstractScreen {

    private SpriteBatch batch;
    private TextureRegion background;
    private Stage backgroundStage;
    private ExtendViewport backgroundViewport;
    private Button btn_easy, btn_medium, btn_hard, btn_start;
    private Button.ButtonStyle easyButtonStyle, medButtonStyle, hardButtonStyle, startButtonStyle;
    private Image title_snake;


    private Skin skin;
    private Table table;
    private Enums.Difficulty difficulty;

    public StartScreen() {
        super();
        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN)); //down: button, up: button,
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));
        difficulty = Enums.Difficulty.MEDIUM;

//        backgroundViewport = new ExtendViewport(getWidth(), getHeight());
//        backgroundStage = new Stage();
//        backgroundStage.setViewport(backgroundViewport);
        background = new TextureRegion(skin.getRegion("bg_start"));
//        backgroundStage.addActor(new Image(background));

        easyButtonStyle = new Button.ButtonStyle();
        medButtonStyle = new Button.ButtonStyle();
        hardButtonStyle = new Button.ButtonStyle();
        startButtonStyle = new Button.ButtonStyle();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(
                Color.BLACK.r,
                Color.BLACK.g,
                Color.BLACK.b,
                Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        backgroundStage.act();
//        backgroundStage.draw();
        act(delta);
        draw();
    }

    @Override
    public void buildStage() {
        table = new Table();
        table.setWidth(super.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, getHeight()); //Start at top left

        title_snake = new Image();
        title_snake.setDrawable(new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.TITLE_SNAKE))));

        easyButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_EASY_OFF)));
        easyButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_EASY_ON)));
        btn_easy = new Button(easyButtonStyle);
        btn_easy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difficulty = Enums.Difficulty.EASY;
                event.stop();
            }
        });

        medButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_MEDIUM_OFF)));
        medButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_MEDIUM_ON)));
        btn_medium = new Button(medButtonStyle);
        btn_medium.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difficulty = Enums.Difficulty.MEDIUM;
                event.stop();
            }
        });

        hardButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_HARD_OFF)));
        hardButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_HARD_ON)));
        btn_hard = new Button(hardButtonStyle);
        btn_hard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difficulty = Enums.Difficulty.HARD;
                event.stop();
            }
        });

        startButtonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_PLAY)));
        btn_start = new Button(startButtonStyle);
        btn_start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(Enums.Screen.GAME_SCREEN, difficulty);
                event.stop();
            }
        });

        table.add(title_snake).padTop(getViewport().getScreenHeight() / 25);

        table.row();
        table.add(btn_easy).padTop(getViewport().getScreenHeight() / 25).colspan(1);
        table.add(btn_medium).padTop(getViewport().getScreenHeight() / 75).colspan(1);
        table.add(btn_hard).padTop(getViewport().getScreenHeight() / 75).colspan(1);

        table.row();
        table.add(btn_start).padTop(getViewport().getScreenHeight() / 15);

        table.background(new TextureRegionDrawable(new TextureRegion(skin.getRegion("bg_start"))));

        addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
        skin.dispose();
    }
}
