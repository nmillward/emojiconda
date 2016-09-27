package com.nickmillward.snake.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nickmillward.snake.Level;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 9/14/16.
 */
public class Food {

    private float x, y;
    private Snake snake;
    private Level level;

    public Food(Snake snake, Level level) {
        this.snake = snake;
        this.level = level;
        x = (float) Math.random() * (Constants.WORLD_SIZE - Constants.FOOD_DEFAULT_SIZE);
        y = (float) Math.random() * (Constants.WORLD_SIZE - Constants.FOOD_DEFAULT_SIZE);
    }

    public void render(SpriteBatch batch) {
        Texture burger = new Texture("burger.png");
        batch.draw(burger, x, y, Constants.FOOD_DEFAULT_SIZE, Constants.FOOD_DEFAULT_SIZE);
    }

    public void changePosition() {
        //TODO: Make sure the new food does not land on the Snake Body
        x = (float) Math.random() * (Constants.WORLD_SIZE - Constants.FOOD_DEFAULT_SIZE);
        y = (float) Math.random() * (Constants.WORLD_SIZE - Constants.FOOD_DEFAULT_SIZE);
    }

    public boolean snakeCollisionWithSnack() {
        float snakeX = snake.getXofHead() + (Constants.SNAKE_SEGMENT_DEFAULT_SIZE / 2);
        float snakeY = snake.getYofHead() + (Constants.SNAKE_SEGMENT_DEFAULT_SIZE / 2);
        if (snakeX >= x - 1 && snakeX <= (x + Constants.FOOD_DEFAULT_SIZE)) {
            if (snakeY >= y - 1 && snakeY <= (y + Constants.FOOD_DEFAULT_SIZE)) {
                changePosition();
                snake.setGrowSnake(true);
                level.incrementCurrentScore(Constants.FOOD_BASE_POINT_VALUE);
                Gdx.app.log("SNACK", "SNAKE HIT SNACK " + level.getCurrentScore());
                return true;
            }
        }
        return false;
    }
}
