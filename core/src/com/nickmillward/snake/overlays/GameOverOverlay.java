package com.nickmillward.snake.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.SnakeGame;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 9/27/16.
 */
public class GameOverOverlay extends InputAdapter {

    public final Viewport viewport;
//    final BitmapFont fontLarge, fontSmall;
    ShapeRenderer renderer;


    SnakeGame game;
    private Skin skin;
    private Stage stage;
    private Table table;
    private Label highScore;
    private BitmapFont font;
    private TextButton restartButton;

    public GameOverOverlay() {
        this.viewport = new ExtendViewport(Constants.GAME_OVER_OVERLAY_VIEWPORT_SIZE, Constants.GAME_OVER_OVERLAY_VIEWPORT_SIZE);
//        fontLarge = new BitmapFont();
//        fontLarge.getData().setScale(3);
//        fontSmall = new BitmapFont();
//        fontSmall.getData().setScale(1);
//        renderer = new ShapeRenderer();

        game = new SnakeGame();
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal(Constants.UI_SKIN));
        font = new BitmapFont();

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight()); //Start at top left

        highScore = new Label(Constants.HIGH_SCORE_LABEL, skin); // + score
        table.add(highScore).padTop(100);

        table.row();    // Add New Row
        restartButton = new TextButton(Constants.BUTTON_RESTART_TEXT, skin);
        restartButton.setWidth(stage.getWidth() / 4);
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.showGameplayScreen();
                event.stop();
            }
        });

        table.add(restartButton).padTop(100);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    public void render(SpriteBatch batch, float delta, int score) {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        final String highScoreText = Constants.HIGH_SCORE_LABEL + score;



//        fontLarge.draw(batch, highScoreText, viewport.getWorldWidth() / 3, viewport.getWorldHeight() / 2);
        batch.end();

//        renderer.begin(ShapeRenderer.ShapeType.Line);
//        renderer.setColor(Color.WHITE);
//        renderer.rect(
//                (viewport.getWorldWidth() / 2) - (viewport.getWorldWidth() / 8),
//                viewport.getWorldHeight() / 4,
//                viewport.getWorldWidth() / 4,
//                viewport.getWorldHeight() / 12
//        );
//        renderer.end();

//        batch.begin();
//        fontSmall.draw(
//                batch,
//                Constants.BUTTON_RESTART_TEXT,
//                viewport.getWorldWidth() / 2,
//                (viewport.getWorldHeight() / 4) + (viewport.getWorldHeight() / 16),
//                0,
//                Align.center,
//                false
//        );
//        batch.end();

//        Gdx.gl.glClearColor(
//                Constants.BACKGROUND_COLOR.r,
//                Constants.BACKGROUND_COLOR.g,
//                Constants.BACKGROUND_COLOR.b,
//                Constants.BACKGROUND_COLOR.a);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
        stage.act(delta);
        stage.draw();

    }

}
