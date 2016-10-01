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

    // SNAKE
    public static final float SNAKE_SEGMENT_DEFAULT_SIZE = WORLD_SIZE * 0.05f;
    public static final Vector2 SNAKE_SEGMENT_CENTER = new Vector2(SNAKE_SEGMENT_DEFAULT_SIZE / 2, SNAKE_SEGMENT_DEFAULT_SIZE / 2);
    public static final int EASY_SNAKE_VELOCITY = 10;
    public static final int MEDIUM_SNAKE_VELOCITY = 5;
    public static final int HARD_SNAKE_VELOCITY = 2;

    public static final Vector2 SNAKE_DEFAULT_START_POINT = new Vector2(WORLD_SIZE / 2, WORLD_SIZE / 2);
    public static final int SNAKE_DEFAULT_LENGTH = 4;

    // FOOD
    public static final float FOOD_DEFAULT_SIZE = WORLD_SIZE * 0.05f;
    public static final Vector2 FOOD_CENTER = new Vector2(FOOD_DEFAULT_SIZE / 2, FOOD_DEFAULT_SIZE / 2);
    public static final int FOOD_BASE_POINT_VALUE = 5;

    // SNAKE HUD
    public static final float SNAKE_HUD_VIEWPORT_SIZE = 480;
    public static final float SNAKE_HUD_MARGIN = 20;
    public static final String SNAKE_HUD_SCORE_LABEL = "Score: ";

    public static final Color BORDER_COLOR = Color.WHITE;
    public static final float BORDER_WIDTH = 10;
    public static final float BORDER_MARGIN = SNAKE_HUD_MARGIN / 2;

    // GAME OVER OVERLAY
    public static final float GAME_OVER_OVERLAY_VIEWPORT_SIZE = 480;
    public static final String HIGH_SCORE_LABEL = "High Score: ";

    // DIFFICULTY SCREEN
    public static final float DIFFICULTY_WORLD_SIZE = 480;
    public static final float DIFFICULTY_LABEL_SCALE = 1.5f;

    public static final Color BUTTON_COLOR = Color.WHITE;
    public static final float BUTTON_MARGIN = 20;

    public static final String EASY_DIFFICULTY_LABEL = "EASY";
    public static final String MEDIUM_DIFFICULTY_LABEL = "MEDIUM";
    public static final String HARD_DIFFICULTY_LABEL = "HARD";

    public static final Vector2 EASY_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 2, DIFFICULTY_WORLD_SIZE * 3/4);
    public static final Vector2 MEDIUM_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 2, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 HARD_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 2, DIFFICULTY_WORLD_SIZE / 4);

}
