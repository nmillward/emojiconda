package com.nickmillward.snake.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nickmillward.snake.Level;
import com.nickmillward.snake.utils.Assets;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Utils;

/**
 * Created by nmillward on 9/14/16.
 */
public class Food {

    private float x, y;
    private float viewportWidth, viewportHeight;
    private TextureRegion randomFood;
    private Vector2 position;
    private Snake snake;
    private Level level;

    public Food(Snake snake, Level level) {
        this.snake = snake;
        this.level = level;
        initPosition();
        getRandomFoodAsset();
    }

    public void render(SpriteBatch batch) {
        Utils.drawTextureRegion(batch, randomFood, position, Constants.FOOD_CENTER);
    }

    private void initPosition() {
        viewportWidth = level.getViewportWidth();
        viewportHeight = level.getViewportHeight();

        x = (float) Math.random() * viewportWidth;
        y = (float) Math.random() * viewportHeight;

        while (x < Constants.BORDER_WIDTH + Constants.FOOD_DEFAULT_SIZE || x > viewportWidth - Constants.FOOD_DEFAULT_SIZE) {
            x = (float) Math.random() * (viewportWidth);
        }
        while (y < Constants.BORDER_WIDTH + Constants.FOOD_DEFAULT_SIZE || y > viewportHeight - Constants.FOOD_DEFAULT_SIZE - Constants.BORDER_SNAKE_HUD) {
            y = (float) Math.random() * (viewportHeight);
        }

        position = new Vector2(x, y);
    }

    private void getRandomFoodAsset() {
        randomFood = Assets.instance.foodAssets.iosFoods.random();
    }

    public void changePosition() {
        //TODO: Make sure the new food does not land on the Snake Body
        initPosition();
        getRandomFoodAsset();

        for (SnakeSegment segment : snake.snakeSegments) {
            if ((Math.abs(segment.getX() - x) < Constants.FOOD_DEFAULT_SIZE) && (Math.abs(segment.getY() - y) < Constants.FOOD_DEFAULT_SIZE)) {
                changePosition();
            }
        }
    }

    public boolean snakeCollisionWithSnack() {
        float snakeX = snake.getXofHead() + (Constants.SNAKE_SEGMENT_CENTER.x);
        float snakeY = snake.getYofHead() + (Constants.SNAKE_SEGMENT_CENTER.y);

        Vector2 foodCenterPosition = new Vector2(x + Constants.FOOD_CENTER.x, y + Constants.FOOD_CENTER.y);

        if (Math.abs(snakeX - foodCenterPosition.x) < (Constants.SNAKE_SEGMENT_DEFAULT_SIZE)) {
            if (Math.abs(snakeY - foodCenterPosition.y) < (Constants.SNAKE_SEGMENT_DEFAULT_SIZE)) {
                changePosition();
                snake.setGrowSnake(true);
                level.incrementCurrentScore(level.getDifficulty().getPointVal());
                return true;
            }
        }
        return false;
    }
}
