package com.nickmillward.snake.utils;

import com.nickmillward.snake.screens.AbstractScreen;
import com.nickmillward.snake.screens.GameplayScreen;
import com.nickmillward.snake.screens.RestartScreen;
import com.nickmillward.snake.screens.StartScreen;

/**
 * Created by nmillward on 9/9/16.
 */
public class Enums {

    public enum GAME_STATE {
        RUN,
        PAUSE,
        FIRST,
        STOP
    }

    public enum Difficulty {
        EASY(Constants.EASY_SNAKE_VELOCITY, Constants.FOOD_EASY_POINT_VALUE),
        MEDIUM(Constants.MEDIUM_SNAKE_VELOCITY, Constants.FOOD_MEDIUM_POINT_VALUE),
        HARD(Constants.HARD_SNAKE_VELOCITY, Constants.FOOD_HARD_POINT_VALUE);

        float velocity;
        int pointVal;

        Difficulty(float velocity, int pointVal) {
            this.velocity = velocity;
            this.pointVal = pointVal;
        }

        public float getVelocity() {
            return velocity;
        }

        public int getPointVal() {
            return pointVal;
        }
    }

    public enum Screen {

        START_SCREEN {
            public AbstractScreen getScreen(Object... params) {
                return new StartScreen();
            }
        },

        RESTART_SCREEN {
            public AbstractScreen getScreen(Object... params) {
                return new RestartScreen((Difficulty) params[0]);
            }
        },

        GAME_SCREEN {
            public AbstractScreen getScreen(Object... params) {
                return new GameplayScreen((Difficulty) params[0]);
            }
        };

        public abstract AbstractScreen getScreen(Object... params);

    }

}
