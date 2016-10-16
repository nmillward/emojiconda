package com.nickmillward.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
    private TextButton startButton;
    private NinePatch startUpNine;
    private TextButton.TextButtonStyle textButtonStyle;

    public StartScreen() {
        super();
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN)); //down: button, up: button,
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));
        startUpNine = skin.getPatch("button");
        textButtonStyle = new TextButton.TextButtonStyle();
    }

    @Override
    public void buildStage() {
        table = new Table();
        table.setWidth(super.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, getHeight()); //Start at top left

        textButtonStyle.up = new NinePatchDrawable(startUpNine);
        textButtonStyle.font = Utils.generateFreeTypeFont(Constants.FONT_FISHFONT, 75, Color.BLACK);
        startButton = new TextButton(Constants.BUTTON_START_TEXT, textButtonStyle);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Start", "button clicked");
                ScreenManager.getInstance().showScreen(Enums.Screen.GAME);
                event.stop();
            }
        });

        table.add(startButton).padTop(100);
        addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
        skin.dispose();
    }
}
