package com.nickmillward.snake.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.screens.GameplayScreen;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Enums;
import com.nickmillward.snake.utils.Utils;

/**
 * Created by nmillward on 11/10/16.
 */
public class InstructionOverlay {

    Preferences firstPref = Gdx.app.getPreferences(Constants.PREF_FIRST_TIME);

    GameplayScreen screen;

    public Viewport viewport;

    public Stage stage;
    private Skin skin;
    private Button gotItButton;
    private BitmapFont font;

    public InstructionOverlay(final GameplayScreen screen, SpriteBatch batch) {
        this.screen = screen;
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN));
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));

        stage = new Stage(viewport, batch);
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.toFront();

        Image swipe = new Image();
        swipe.setDrawable(new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.IMG_SWIPE))));
        swipe.setSize(300, 200);

        font = Utils.generateFreeTypeFont(Constants.FONT_TITAN, 30, Color.WHITE);
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        Label swipeText = new Label(Constants.TXT_SWIPE, labelStyle);
        swipeText.setFontScale(2);
        swipeText.setAlignment(Align.center);

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_GOT_IT)));

        gotItButton = new Button(buttonStyle);
        gotItButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                firstPref.putBoolean(Constants.KEY_FIRST_TIME, false);
                firstPref.flush();
                screen.setGameState(Enums.GAME_STATE.RUN);
                Gdx.app.log("PAUSE OVERLAY", "GOT IT BUTTON CLICKED");
                event.stop();
            }
        });

        table.add(swipe).expand().padTop(viewport.getWorldHeight() * 1/3);

        table.row();
        table.add(swipeText).expand();

        table.row();
        table.add(gotItButton).expand();

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    public void disableButton() {
        gotItButton.setTouchable(Touchable.disabled);
    }

    public void enableButton() {
        gotItButton.setTouchable(Touchable.enabled);
    }

}
