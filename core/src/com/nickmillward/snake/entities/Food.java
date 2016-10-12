package com.nickmillward.snake.entities;

import com.badlogic.gdx.Gdx;
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
        x = (float) Math.random() * (Constants.WORLD_SIZE - Constants.FOOD_DEFAULT_SIZE);
        y = (float) Math.random() * (Constants.WORLD_SIZE - Constants.FOOD_DEFAULT_SIZE);
        position = new Vector2(x, y);
    }

    private void getRandomFoodAsset() {
        randomFood = Assets.instance.foodAssets.iosFoods.random();
    }

    public void changePosition() {
        //TODO: Make sure the new food does not land on the Snake Body
        Gdx.app.log("FOOD", "CHANGE POSITION CALLED =================== ");
        initPosition();
        getRandomFoodAsset();

        for (SnakeSegment segment : snake.snakeSegments) {
            if ((Math.abs(segment.getX() - x) < Constants.FOOD_DEFAULT_SIZE) && (Math.abs(segment.getY() - y) < Constants.FOOD_DEFAULT_SIZE)) {
                Gdx.app.log("FOOD", "=================== PLACED TOO CLOSE TO SNAKE");
                changePosition();
            }
        }
    }

    public boolean snakeCollisionWithSnack() {
        float snakeX = snake.getXofHead() + (Constants.SNAKE_SEGMENT_CENTER.x);
        float snakeY = snake.getYofHead() + (Constants.SNAKE_SEGMENT_CENTER.y);

        Vector2 foodCenterPosition = new Vector2(x + Constants.FOOD_CENTER.x, y + Constants.FOOD_CENTER.y);

        if (Math.abs(snakeX - foodCenterPosition.x) < (Constants.SNAKE_SEGMENT_DEFAULT_SIZE)) {
//            Gdx.app.log("FOOD", "SNAKE X & FOOD X DIFF = " + Math.abs(snakeX - foodCenterPosition.x));
            if (Math.abs(snakeY - foodCenterPosition.y) < (Constants.SNAKE_SEGMENT_DEFAULT_SIZE)) {
//                Gdx.app.log("FOOD", "SNAKE Y & FOOD Y DIFF = " + Math.abs(snakeY - foodCenterPosition.y));
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
