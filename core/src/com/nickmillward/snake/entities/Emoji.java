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
//        velocity.y -= Constants.EMOJI_DEFAULT_VELOCITY;
    }

    public void render(SpriteBatch batch) {

        Texture happyFace = new Texture("happy.png");

        batch.draw(happyFace, spawnLocation.x, spawnLocation.y, Constants.WORLD_SIZE * 0.05f, Constants.WORLD_SIZE * 0.05f);

    }
}
