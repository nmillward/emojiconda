package com.nickmillward.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.nickmillward.snake.Level;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Enums;
import com.nickmillward.snake.utils.ScreenManager;

/**
 * Created by nmillward on 10/9/16.
 */
public class RestartScreen extends AbstractScreen {

    private Skin skin;
    private Table table;
    private Label highScore;
    private TextButton restartButton;
    private BitmapFont font;
    private Level level;

    public RestartScreen() {
        super();
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN));
        font = new BitmapFont();
        level = new Level();
    }

    @Override
    public void buildStage() {
        table = new Table();
        table.setWidth(getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        highScore = new Label(Constants.HIGH_SCORE_LABEL + level.getHighScore(), skin); //  + level.getHighScore()
//        highScore = new Label(Constants.HIGH_SCORE_LABEL + Level.getLevel().getHighScore(), skin); //  + level.getHighScore()
//        Gdx.app.log("RESTART", "score = " + );
        table.add(highScore).padTop(100);

        table.row();    // Add New Row
        restartButton = new TextButton(Constants.BUTTON_RESTART_TEXT, skin);
        restartButton.setWidth(getWidth() / 4);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(Enums.Screen.GAME);
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
