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
        RESUME,
        STOP
    }

    public enum Difficulty {
        EASY(Constants.EASY_SNAKE_VELOCITY),
        MEDIUM(Constants.MEDIUM_SNAKE_VELOCITY),
        HARD(Constants.HARD_SNAKE_VELOCITY);

        float velocity;

        Difficulty(float velocity) {
            this.velocity = velocity;
        }

        public float getVelocity() {
            return velocity;
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
                return new RestartScreen((Integer) params[0]);
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
