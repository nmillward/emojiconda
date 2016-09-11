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

    private Level level;
    private Direction direction;
    private Vector2 spawnLocation;
    private Vector2 position;
    private Vector2 velocity;
    private float emojiSize;


    public Emoji(Level level, Vector2 spawnLocation) {
        this.level = level;
        this.spawnLocation = spawnLocation;
        position =  new Vector2();
        velocity = new Vector2();

        init();
    }

    public void init() {
        respawn();
    }

    private void respawn() {
        position.set(spawnLocation);
//        direction = Direction.UP;
        // TODO: set velocity, depending on Difficulty
//        velocity.setZero();
    }

    public void update(float delta) {
//        position.y += delta * Constants.EMOJI_DEFAULT_VELOCITY;
//        velocity.y += delta * Constants.EMOJI_DEFAULT_VELOCITY;
//        position.mulAdd(velocity, delta);

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
        emojiSize = Constants.WORLD_SIZE * 0.05f;

        batch.draw(happyFace, position.x, position.y, emojiSize, emojiSize);

    }

    private void moveLeft(float delta) {
        if (direction != Direction.RIGHT) {
            direction = Direction.LEFT;
        }
        position.x -= delta * Constants.EMOJI_DEFAULT_VELOCITY;
    }

    private void moveRight(float delta) {
        if (direction != Direction.LEFT) {
            direction = Direction.RIGHT;
        }
        position.x += delta * Constants.EMOJI_DEFAULT_VELOCITY;
    }

    private void moveUp(float delta) {
        if (direction != Direction.DOWN) {
            direction = Direction.UP;
        }
        position.y += delta * Constants.EMOJI_DEFAULT_VELOCITY;
    }

    private void moveDown(float delta) {
        if (direction != Direction.UP) {
            direction = Direction.DOWN;
        }
        position.y -= delta * Constants.EMOJI_DEFAULT_VELOCITY;
    }

}
