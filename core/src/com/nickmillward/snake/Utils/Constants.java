package com.nickmillward.snake.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by nmillward on 9/7/16.
 */
public class Constants {

    // WORLD
    public static final float WORLD_SIZE = 800;
    public static final Color BACKGROUND_COLOR = Color.SKY;
    public static final String TEXTURE_ATLAS = "textures/textureAtlas.atlas";
    public static final String UI_SKIN = "uiskin.json";
    public static final String FONT_FISHFONT = "font/Fishfingers.ttf";

    // SNAKE
    public static final float SNAKE_SEGMENT_DEFAULT_SIZE = 50;
    public static final Vector2 SNAKE_SEGMENT_CENTER = new Vector2(SNAKE_SEGMENT_DEFAULT_SIZE / 2, SNAKE_SEGMENT_DEFAULT_SIZE / 2);
    public static final int EASY_SNAKE_VELOCITY = 10;
    public static final int MEDIUM_SNAKE_VELOCITY = 5;
    public static final int HARD_SNAKE_VELOCITY = 2;

    public static final Vector2 SNAKE_DEFAULT_START_POINT = new Vector2(WORLD_SIZE / 2, WORLD_SIZE / 2);
    public static final int SNAKE_DEFAULT_LENGTH = 4;

    public static final String IOS_CRY_TEARS = "cry_tears";
    public static final String IOS_GRIN = "grin";
    public static final String IOS_HAPPY = "happy";
    public static final String IOS_HAPPY_CRY = "happy_cry";
    public static final String IOS_HEART = "heart";
    public static final String IOS_KISS = "kiss";
    public static final String IOS_SHADES = "shades";
    public static final String IOS_SHOCK = "shock";
    public static final String IOS_SMIRK = "smirk";
    public static final String IOS_TONGUE_HAPPY = "tongue_happy";
    public static final String IOS_TONGUE_TEASE = "tongue_tease";
    public static final String IOS_TONGUE_WINK = "tongue_wink";

    // FOOD
    public static final float FOOD_DEFAULT_SIZE = 40;
    public static final Vector2 FOOD_CENTER = new Vector2(FOOD_DEFAULT_SIZE / 2, FOOD_DEFAULT_SIZE / 2);
    public static final int FOOD_BASE_POINT_VALUE = 5;

    public static final String IOS_BEEF = "beef";
    public static final String IOS_BURGER = "burger";
    public static final String IOS_CAKE = "cake";
    public static final String IOS_CANDYBAR = "candybar";
    public static final String IOS_COOKIE = "cookie";
    public static final String IOS_DONUT = "donut";
    public static final String IOS_DRUMSTICK = "drumstick";
    public static final String IOS_ICECREAM = "icecream";
    public static final String IOS_LOLLIPOP = "lollipop";
    public static final String IOS_PIZZA = "pizza";

    // SNAKE HUD
    public static final float SNAKE_HUD_VIEWPORT_SIZE = 480;
    public static final float SNAKE_HUD_MARGIN = 20;
    public static final String SNAKE_HUD_SCORE_LABEL = "Score: ";
    public static final String PAUSE_BUTTON_TEXT = "PAUSE";

    // PAUSE OVERLAY
    public static final Color PAUSE_BACKGROUND_COLOR = Color.valueOf("BFFFFFFF"); // 75% White
    public static final String PAUSED_TEXT = "PAUSED";
    public static final String RESUME_BUTTON_TEXT = "RESUME";

    // LEVEL
    public static final Color BORDER_COLOR = Color.WHITE;
    public static final float BORDER_WIDTH = 15.0f;

    // GAME_SCREEN OVER SCREEN
    public static final String HIGH_SCORE_LABEL = "High Score: ";
    public static final String BUTTON_RESTART_TEXT = "RESTART";

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

    // START SCREEN
    public static final String GAME_TITLE = "SNAKE";
    public static final String BUTTON_START_TEXT = "START";
    public static final String MENU_BUTTON = "button";

}
