package com.nickmillward.snake.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by nmillward on 9/7/16.
 */
public class Constants {

    // WORLD
    public static final float WORLD_SIZE = 160;
    public static final Color BACKGROUND_COLOR = Color.BLACK;

    // EMOJI
    public static final float EMOJI_DEFAULT_VELOCITY = 20;
    public static final float EMOJI_DEFAULT_SIZE = WORLD_SIZE * 0.05f;

    public static final Vector2 SNAKE_DEFAULT_START_POINT = new Vector2(50, 50);
    public static final int SNAKE_DEFAULT_LENGTH = 4;

}
