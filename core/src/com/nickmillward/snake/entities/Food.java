package com.nickmillward.snake.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
        float snakeX = snake.getXofHead() + (Constants.SNAKE_SEGMENT_CENTER.x);
        float snakeY = snake.getYofHead() + (Constants.SNAKE_SEGMENT_CENTER.y);

        Vector2 foodCenterPosition = new Vector2(x + Constants.FOOD_CENTER.x, y + Constants.FOOD_CENTER.y);

        if (snakeX > foodCenterPosition.x - Constants.SNAKE_SEGMENT_CENTER.x && snakeX < (foodCenterPosition.x + Constants.SNAKE_SEGMENT_CENTER.x)) {
            if (snakeY > foodCenterPosition.y - Constants.SNAKE_SEGMENT_CENTER.y && snakeY < (foodCenterPosition.y + Constants.SNAKE_SEGMENT_CENTER.y)) {
                changePosition();
                snake.setGrowSnake(true);
                level.incrementCurrentScore(Constants.FOOD_BASE_POINT_VALUE);
                Gdx.app.log("FOOD", "SNAKE COORDINATES (" + snakeX + ", " + snakeY + ")");
                Gdx.app.log("FOOD", "FOOD COORDINATES (" + foodCenterPosition.x + ", " + foodCenterPosition.y + ")");
                Gdx.app.log("FOOD", "SNAKE ATE FOOD " + level.getCurrentScore());
                return true;
            }
        }
        return false;
    }
}
