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
        EASY(Constants.EASY_SNAKE_VELOCITY, Constants.EASY_DIFFICULTY_LABEL),
        MEDIUM(Constants.MEDIUM_SNAKE_VELOCITY, Constants.MEDIUM_DIFFICULTY_LABEL),
        HARD(Constants.HARD_SNAKE_VELOCITY, Constants.HARD_DIFFICULTY_LABEL);

        float velocity;
        String label;

        Difficulty(float velocity, String label) {
            this.velocity = velocity;
            this.label = label;
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

        GAME {
            public AbstractScreen getScreen(Object... params) {
                return new GameplayScreen();
            }
        };

        public abstract AbstractScreen getScreen(Object... params);

    }

}
