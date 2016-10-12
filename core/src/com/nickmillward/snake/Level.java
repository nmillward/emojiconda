package com.nickmillward.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nickmillward.snake.entities.Food;
import com.nickmillward.snake.entities.Snake;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 9/7/16.
 */
public class Level {

    public static final String TAG = Level.class.getName();
    public static Level level;

    SnakeGame game;
    ShapeRenderer shapeRenderer;
    public Viewport viewport;
    public Snake snake;
    public Food food;
    public boolean isGameOver;
    public int currentScore = 0;
    public int highScore = 0;

    public Level() {
        game = new SnakeGame();
        shapeRenderer = new ShapeRenderer();
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        initGameDefault();
    }

    public void update(float delta) {
        if (!isGameOver) {
            snake.update(delta);
            checkGameOver();
            food.snakeCollisionWithSnack();
        } else {
            //TODO: GAME OVER
            resetGame();
        }
    }

    public void render(SpriteBatch batch) {
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        snake.render(batch);
        food.render(batch);
        batch.end();

        // BORDER
//        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Constants.BORDER_COLOR);
//        shapeRenderer.rect(
//                Constants.BORDER_MARGIN,
//                Constants.BORDER_MARGIN,
//                viewport.getWorldWidth() - Constants.BORDER_MARGIN,
//                viewport.getWorldHeight() - Constants.BORDER_MARGIN
//        );
//        shapeRenderer.end();

    }

    public void checkGameOver() {
        if (snake.getXofHead() < Constants.BORDER_MARGIN || snake.getXofHead() > viewport.getWorldWidth() - Constants.SNAKE_SEGMENT_DEFAULT_SIZE - Constants.BORDER_MARGIN) {
            isGameOver = true;
            Gdx.app.log(TAG, "GAME OVER");
        }
        if (snake.getYofHead() < Constants.BORDER_MARGIN || snake.getYofHead() > viewport.getWorldHeight() - Constants.SNAKE_SEGMENT_DEFAULT_SIZE - Constants.BORDER_MARGIN) {
            isGameOver = true;
            Gdx.app.log(TAG, "GAME OVER");
        }
        if (snake.snakeCollision()) {
            isGameOver = true;
            Gdx.app.log(TAG, "GAME OVER");
        }
    }

    public void initGameDefault() {
        isGameOver = false;
        snake = new Snake(this);
        food = new Food(snake, this);
    }

    public void resetGame() {
        if (currentScore > highScore) {
            highScore = currentScore;
        }
        //TODO: Remove Timer Task. Replace with Button.
//        Timer.schedule(new Timer.Task() {
//            @Override
//            public void run() {
//                initGameDefault();
//                resetCurrentScore();
//                Gdx.app.log(TAG, "RESTART GAME");
//            }
//        }, 1);

        resetCurrentScore();
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void incrementCurrentScore(int pointValue) {
        currentScore += pointValue;
    }

    public void resetCurrentScore() {
        currentScore = 0;
    }
}
