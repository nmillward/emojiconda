package com.nickmillward.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by nmillward on 9/30/16.
 */
public class MobileControls extends InputAdapter implements InputProcessor {

    Level level;

    private Vector2 lastTouch = new Vector2();
    private Vector2 newTouch = new Vector2();
    private Vector2 delta = new Vector2();

    public MobileControls(Level level) {
        this.level = level;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastTouch.set(screenX, screenY);
        Gdx.app.log("MOBILE", "TOUCH DOWN: (" + screenX + ", " + screenY + ")");
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        newTouch = new Vector2(screenX, screenY);

        delta = newTouch.cpy().sub(lastTouch);

        float touchDiff = newTouch.dst(lastTouch); //Find the distance between the two points

        if (!level.snake.isMoving()) {
            if (newTouch.y < lastTouch.y) {
                if (newTouch.x > lastTouch.x || newTouch.x < lastTouch.x)
                level.snake.setMoving(true);
            } else {
                level.snake.setMoving(false);
            }
        }

        if (touchDiff > 15.0) { //TODO: Look into change over time (e.g. no direction change within 200 milliseconds or * difficulty speed)

            if (level.snake.isMoving()) {
                if (Math.abs(delta.x) > Math.abs(delta.y)) {    // Move Left or Right
                    if (delta.x > 0) {
                        if (level.snake.getxDir() != -1) {
                            level.snake.setxDir(1);
                            level.snake.setyDir(0);
                            Gdx.app.log("MOBILE CONTROL", "MOVE RIGHT");
                        }
                    } else if (delta.x < 0) {
                        if (level.snake.getxDir() != 1) {
                            level.snake.setxDir(-1);
                            level.snake.setyDir(0);
                            Gdx.app.log("MOBILE CONTROL", "MOVE LEFT");
                        }
                    }
                } else {
                    if (delta.y < 0) {
                        if (level.snake.getyDir() != -1) {
                            level.snake.setxDir(0);
                            level.snake.setyDir(1);
                            Gdx.app.log("MOBILE CONTROL", "MOVE UP");
                        }
                    } else if (delta.y > 0) {
                        if (level.snake.getyDir() != 1) {
                            level.snake.setxDir(0);
                            level.snake.setyDir(-1);
                            Gdx.app.log("MOBILE CONTROL", "MOVE DOWN");
                        }
                    }
                }
            }

        }

        lastTouch = newTouch;

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("MOBILE", "TOUCH UP");
        return true;
    }
}
