package com.nickmillward.snake.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nickmillward.snake.Level;
import com.nickmillward.snake.utils.Assets;
import com.nickmillward.snake.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmillward on 9/13/16.
 */
public class Snake {

    Level level;
    List<SnakeSegment> snakeSegments;

    public int xDir, yDir;
    boolean isMoving, growSnake;

    private int counter = 0;
    private TextureRegion randomEmoji;

    public Snake(Level level) {
        snakeSegments = new ArrayList<SnakeSegment>();
        this.level = level;
        xDir = 0;
        yDir = 0;
        isMoving = false;
        growSnake = false;
        getRandomEmojiAsset();
        snakeSegments.add(new SnakeSegment(Constants.SNAKE_DEFAULT_START_POINT.x, Constants.SNAKE_DEFAULT_START_POINT.y));
        for (int i = 1; i < Constants.SNAKE_DEFAULT_LENGTH; i++) {
            snakeSegments.add(new SnakeSegment(Constants.SNAKE_DEFAULT_START_POINT.x, Constants.SNAKE_DEFAULT_START_POINT.y - i * Constants.SNAKE_SEGMENT_DEFAULT_SIZE));
        }
    }

    public void render(SpriteBatch batch) {
        for (SnakeSegment snakeSegment : snakeSegments) {
            batch.draw(randomEmoji, snakeSegment.getX(), snakeSegment.getY(), Constants.SNAKE_SEGMENT_DEFAULT_SIZE, Constants.SNAKE_SEGMENT_DEFAULT_SIZE);
        }
    }

    private void getRandomEmojiAsset() {
        randomEmoji = Assets.instance.snakeAssets.iosEmojis.random();
    }


    public void update(float delta) {
        keyPressed();
        move();
    }

    public void move() {
        if (isMoving) {

            counter++;

            /**
             * Counter dictates how quickly the snake will "move"
             * TODO: Update counter modulo function according to difficulty level
             */
            if (counter % level.getDifficulty().getVelocity() == 0) {
                SnakeSegment head = snakeSegments.get(0);
                SnakeSegment tail = snakeSegments.get(snakeSegments.size() - 1);
                SnakeSegment newStart = new SnakeSegment(head.getX() + xDir * Constants.SNAKE_SEGMENT_DEFAULT_SIZE, head.getY() + yDir * Constants.SNAKE_SEGMENT_DEFAULT_SIZE);

                // Set new points for the Body
                for (int i = snakeSegments.size() - 1; i >= 1; i--) {
                    snakeSegments.set(i, snakeSegments.get(i - 1));
                }

                // If Snake has eaten a Food, add to Body
                if (growSnake) {
                    snakeSegments.add(tail);
                    growSnake = false;
                }

                // Set a new point for the Head
                snakeSegments.set(0, newStart);

                counter = 0;
            }
        }
    }

    public void keyPressed() {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            isMoving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (getxDir() != 1) {
                setxDir(-1);
                setyDir(0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (getxDir() != -1) {
                setxDir(1);
                setyDir(0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (getyDir() != -1) {
                setxDir(0);
                setyDir(1);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (getyDir() != 1) {
                setxDir(0);
                setyDir(-1);
            }
        }
    }

    public boolean snakeCollision() {
        float x = getXofHead();
        float y = getYofHead();
        for (int i = 1; i < snakeSegments.size(); i++) {
            if (snakeSegments.get(i).getX() == x && snakeSegments.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public int getxDir() {
        return xDir;
    }

    public void setxDir(int xDir) {
        this.xDir = xDir;
    }

    public int getyDir() {
        return yDir;
    }

    public void setyDir(int yDir) {
        this.yDir = yDir;
    }

    // Get Head of Snake
    public float getXofHead() {
        return snakeSegments.get(0).getX();
    }

    public float getYofHead() {
        return snakeSegments.get(0).getY();
    }

    public void setGrowSnake(boolean b) {
        growSnake = b;
    }
}
