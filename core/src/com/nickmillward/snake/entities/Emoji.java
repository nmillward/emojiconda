package com.nickmillward.snake.entities;

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
        position.y += delta * Constants.EMOJI_DEFAULT_VELOCITY;
//        velocity.y += delta * Constants.EMOJI_DEFAULT_VELOCITY;
//        position.mulAdd(velocity, delta);
    }

    public void render(SpriteBatch batch) {

        Texture happyFace = new Texture("happy.png");

        batch.draw(happyFace, position.x, position.y, Constants.WORLD_SIZE * 0.05f, Constants.WORLD_SIZE * 0.05f);

    }
}
