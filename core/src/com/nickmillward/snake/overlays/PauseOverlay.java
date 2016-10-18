package com.nickmillward.snake.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.screens.GameplayScreen;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Enums;
import com.nickmillward.snake.utils.Utils;

/**
 * Created by nmillward on 10/17/16.
 */
public class PauseOverlay {

    GameplayScreen screen;
    ShapeRenderer shapeRenderer;

    public final Viewport viewport;
    final BitmapFont font;

    public Stage stage;
    private Skin skin;
    private NinePatch resumeUpNine;

    public PauseOverlay(final GameplayScreen screen, SpriteBatch batch) {
        this.screen = screen;
        this.viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN));
        skin.addRegions(new TextureAtlas(Constants.TEXTURE_ATLAS));
        resumeUpNine = skin.getPatch("button");

        font = Utils.generateFreeTypeFont(Constants.FONT_FISHFONT, 96, Color.BLACK);
        shapeRenderer = new ShapeRenderer();

        stage = new Stage(viewport, batch);
        Table table = new Table();
        table.top();
        table.setWidth(viewport.getWorldWidth());

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLACK);
        Label pauseLabel = new Label(Constants.PAUSED_TEXT, labelStyle);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = new NinePatchDrawable(resumeUpNine);

        TextButton resumeButton = new TextButton(Constants.RESUME_BUTTON_TEXT, textButtonStyle);
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.setGameState(Enums.GAME_STATE.RUN);
                Gdx.app.log("PAUE OVERLAY", "RESUME BUTTON CLICKED");
                event.stop();
            }
        });

        table.add(pauseLabel).padTop(100);
        table.row();
        table.add(resumeButton).padTop(100);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }
}
