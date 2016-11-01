package com.nickmillward.snake.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.screens.GameplayScreen;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Enums;

/**
 * Created by nmillward on 10/17/16.
 */
public class PauseOverlay {

    GameplayScreen screen;

    public final Viewport viewport;

    public Stage stage;
    private Skin skin;
    private final Button resumeButton;

    public PauseOverlay(final GameplayScreen screen, SpriteBatch batch) {
        this.screen = screen;
        this.viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN));
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));

        stage = new Stage(viewport, batch);
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.toFront();

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(skin.getRegion(Constants.BUTTON_RESUME)));

        resumeButton = new Button(buttonStyle);
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.setGameState(Enums.GAME_STATE.RUN);
                Gdx.app.log("PAUSE OVERLAY", "RESUME BUTTON CLICKED");
                event.stop();
            }
        });

        table.add(resumeButton);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    public void disableButton() {
        resumeButton.setTouchable(Touchable.disabled);
    }

    public void enableButton() {
        resumeButton.setTouchable(Touchable.enabled);
    }
}
