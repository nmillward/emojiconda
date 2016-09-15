package com.nickmillward.snake.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nickmillward.snake.utils.Constants;

/**
 * Created by nmillward on 9/14/16.
 */
public class Snack {

    private float x, y;
    private int score;
    private Snake snake;

    public Snack(Snake snake) {
        this.snake = snake;
        x = (float) Math.random() * (Constants.WORLD_SIZE - Constants.SNACK_DEFAULT_SIZE);
        y = (float) Math.random() * (Constants.WORLD_SIZE - Constants.SNACK_DEFAULT_SIZE);
    }

    public void render(SpriteBatch batch) {
        Texture burger = new Texture("burger.png");
        batch.draw(burger, x, y, Constants.SNACK_DEFAULT_SIZE, Constants.SNACK_DEFAULT_SIZE);
    }

    public void changePosition() {
        x = (float) Math.random() * (Constants.WORLD_SIZE - Constants.SNACK_DEFAULT_SIZE);
        y = (float) Math.random() * (Constants.WORLD_SIZE - Constants.SNACK_DEFAULT_SIZE);
    }

    public int getScore() {
        return score;
    }

    public boolean snakeCollisionWithSnack() {
        float snakeX = snake.getXofHead() + (Constants.EMOJI_DEFAULT_SIZE / 2);
        float snakeY = snake.getYofHead() + (Constants.EMOJI_DEFAULT_SIZE / 2);
        if (snakeX >= x - 1 && snakeX <= (x + Constants.SNACK_DEFAULT_SIZE)) {
            if (snakeY >= y - 1 && snakeY <= (y + Constants.SNACK_DEFAULT_SIZE)) {
                changePosition();
                snake.setGrowSnake(true);
                score++;
                Gdx.app.log("SNACK", "SNAKE HIT SNACK " + score);
                return true;
            }
        }
        return false;
    }
}
