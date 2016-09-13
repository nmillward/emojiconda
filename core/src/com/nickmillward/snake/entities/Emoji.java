package com.nickmillward.snake.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nickmillward.snake.Level;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Enums.Direction;

/**
 * Created by nmillward on 9/9/16.
 */
public class Emoji {

    public static final String TAG = Level.class.getName();

    private Level level;
    private Direction direction;
    private Vector2 spawnLocation;
    private Vector2 position;
    private Vector2 velocity;


    public Emoji(Level level, Vector2 spawnLocation) {
        this.level = level;
        this.position = spawnLocation;
//        position =  new Vector2();
        velocity = new Vector2();

        init();
    }

    public void init() {
        respawn();
    }

    private void respawn() {
//        position.set(spawnLocation);
        position.set(position);
//        direction = Direction.UP;
        // TODO: set velocity, depending on Difficulty
//        velocity.setZero();
    }

    public void update(float delta) {
//        position.y += delta * Constants.EMOJI_DEFAULT_VELOCITY;
//        velocity.y += delta * Constants.EMOJI_DEFAULT_VELOCITY;
//        position.mulAdd(velocity, delta);

        // TODO: Switch statement to check DIRECTION
        // call advance method depending on direction

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveLeft(delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveRight(delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            moveUp(delta);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moveDown(delta);
        }
    }

    public void render(SpriteBatch batch) {

        Texture happyFace = new Texture("happy.png");

        batch.draw(happyFace, position.x, position.y, Constants.EMOJI_DEFAULT_SIZE, Constants.EMOJI_DEFAULT_SIZE);

    }

    private void moveLeft(float delta) {
        if (direction != Direction.RIGHT) {
            direction = Direction.LEFT;
        }
//        position.x -= delta * Constants.EMOJI_DEFAULT_VELOCITY;
        advanceEmojis(delta);
    }

    private void moveRight(float delta) {
        if (direction != Direction.LEFT) {
            direction = Direction.RIGHT;
        }
//        position.x += delta * Constants.EMOJI_DEFAULT_VELOCITY;
        advanceEmojis(delta);
    }

    private void moveUp(float delta) {
        if (direction != Direction.DOWN) {
            direction = Direction.UP;
        }
//        position.y += delta * Constants.EMOJI_DEFAULT_VELOCITY;
        advanceEmojis(delta);
    }

    private void moveDown(float delta) {
        if (direction != Direction.UP) {
            direction = Direction.DOWN;
        }
//        position.y -= delta * Constants.EMOJI_DEFAULT_VELOCITY;
        advanceEmojis(delta);
    }

    private void advanceEmojis(float delta) {
        Emoji head = level.emojis.first();
        int length = level.emojis.size - 1;
        Gdx.app.log(TAG, "snake length = " + length);

        for (int i = length; i > 0; i--) {
            Gdx.app.log(TAG, "snake part = " + i);
            Gdx.app.log(TAG, "snake part X = " + level.emojis.get(i).position.x);
            Gdx.app.log(TAG, "snake part Y = " + level.emojis.get(i).position.y);

            if (direction == Direction.LEFT) {
                level.emojis.get(i).position.x = level.emojis.get(i - 1).position.x + Constants.EMOJI_DEFAULT_SIZE;
                level.emojis.get(i).position.y = level.emojis.get(i - 1).position.y;
            } else if (direction == Direction.RIGHT) {
                level.emojis.get(i).position.x = level.emojis.get(i - 1).position.x - Constants.EMOJI_DEFAULT_SIZE;
                level.emojis.get(i).position.y = level.emojis.get(i - 1).position.y;
            } else if (direction == Direction.UP) {
                level.emojis.get(i).position.x = level.emojis.get(i - 1).position.x;
                level.emojis.get(i).position.y = level.emojis.get(i - 1).position.y - Constants.EMOJI_DEFAULT_SIZE;
            } else if (direction == Direction.DOWN) {
                level.emojis.get(i).position.x = level.emojis.get(i - 1).position.x;
                level.emojis.get(i).position.y = level.emojis.get(i - 1).position.y + Constants.EMOJI_DEFAULT_SIZE;
            }
        }

//        if (direction == Direction.LEFT) {
//            head.position.x -= delta * Constants.EMOJI_DEFAULT_VELOCITY;
//        }

        switch (direction) {
            case LEFT:
                head.position.x -= delta * Constants.EMOJI_DEFAULT_VELOCITY;
                break;
            case RIGHT:
                head.position.x += delta * Constants.EMOJI_DEFAULT_VELOCITY;
                break;
            case UP:
                head.position.y += delta * Constants.EMOJI_DEFAULT_VELOCITY;
                break;
            case DOWN:
                head.position.y -= delta * Constants.EMOJI_DEFAULT_VELOCITY;
                break;
        }

//        level.emojis.get(0).position.x = position.x;
//        level.emojis.get(0).position.y = position.y;

        //level.emojis.get(0).position.x -= delta * Constants.EMOJI_DEFAULT_VELOCITY;

//        for (int i = length; i > 0; i--) {
//            Emoji before = level.emojis.get(i - 1);
//            Emoji part = level.emojis.get(i);
//            part.position.x = before.position.x + Constants.EMOJI_DEFAULT_SIZE;
//            part.position.y = before.position.y + Constants.EMOJI_DEFAULT_SIZE;
//            Gdx.app.log(TAG, "snake position = (" + part.position.x + ", " + part.position.y + ")") ;
//        }
//
//        if (direction == Direction.LEFT) {
////            head.position.x -= 1;
//            head.position.x -= delta * Constants.EMOJI_DEFAULT_VELOCITY;
//        }
    }
}
