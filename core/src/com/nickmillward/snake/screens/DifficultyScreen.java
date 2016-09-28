package com.nickmillward.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.nickmillward.snake.SnakeGame;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 9/28/16.
 */
public class DifficultyScreen extends InputAdapter implements Screen {

    SnakeGame game;
    ShapeRenderer renderer;
    SpriteBatch batch;
    ExtendViewport viewport;
    BitmapFont font;

    public DifficultyScreen(SnakeGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        viewport = new ExtendViewport(Constants.DIFFICULTY_WORLD_SIZE, Constants.DIFFICULTY_WORLD_SIZE);

        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.getData().setScale(Constants.DIFFICULTY_LABEL_SCALE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(
                Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        final GlyphLayout easyLayout = new GlyphLayout(font, Constants.EASY_DIFFICULTY_LABEL);
        font.draw(batch, Constants.EASY_DIFFICULTY_LABEL, Constants.EASY_CENTER.x + easyLayout.width / 2, Constants.EASY_CENTER.y, 0, Align.center, false);

        final GlyphLayout mediumLayout = new GlyphLayout(font, Constants.MEDIUM_DIFFICULTY_LABEL);
        font.draw(batch, Constants.MEDIUM_DIFFICULTY_LABEL, Constants.MEDIUM_CENTER.x + mediumLayout.width / 2, Constants.MEDIUM_CENTER.y, 0, Align.center, false);

        final GlyphLayout hardLayout = new GlyphLayout(font, Constants.HARD_DIFFICULTY_LABEL);
        font.draw(batch, Constants.HARD_DIFFICULTY_LABEL, Constants.HARD_CENTER.x + hardLayout.width / 2, Constants.HARD_CENTER.y, 0, Align.center, false);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

//        if (worldTouch.dst(Constants.EASY_CENTER) < (Constants.EASY_CENTER.x * 2)) {
//            Gdx.app.log("DIFF SCREEN", "Difficulty = EASY");
//        }

        return true;
    }
}
