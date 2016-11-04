package com.nickmillward.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by nmillward on 9/30/16.
 */
public class MobileControls extends InputAdapter implements GestureDetector.GestureListener {

    Level level;

    private Vector2 lastTouch = new Vector2();
    private Vector2 newTouch = new Vector2();

    public MobileControls(Level level) {
        this.level = level;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        if (velocityX > 0 || velocityX < 0 || velocityY > 0) {
            level.snake.setMoving(true);
        }

        if (Math.abs(velocityX) > Math.abs(velocityY)) {    // Move Left or Right

            if (velocityX < 0) { // Move Left
                if (level.snake.getxDir() != 1) {
                    level.snake.setxDir(-1);
                    level.snake.setyDir(0);
                    Gdx.app.log("MOBILE CONTROL", "MOVE LEFT");
                }

            } else if (velocityX > 0) { // Move Right
                if (level.snake.getxDir() != -1) {
                    level.snake.setxDir(1);
                    level.snake.setyDir(0);
                    Gdx.app.log("MOBILE CONTROL", "MOVE RIGHT");
                }

            } else {
                // Do Nothing
            }

        } else {

            if (velocityY < 0) { // Move UP
                if (level.snake.getyDir() != -1) {
                    level.snake.setxDir(0);
                    level.snake.setyDir(1);
                    Gdx.app.log("MOBILE CONTROL", "MOVE UP");
                }

            } else if (velocityY > 0) { // Move Down
                if (level.snake.getyDir() != 1) {
                    level.snake.setxDir(0);
                    level.snake.setyDir(-1);
                    Gdx.app.log("MOBILE CONTROL", "MOVE DOWN");
                }
            } else {
                // Do Nothing
            }

        }

        level.snake.move();

        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
//        lastTouch.set(x, y);
//        Gdx.app.log("MOBILE", "TOUCH DOWN: (" + x + ", " + y + ")");
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        return super.touchDragged(screenX, screenY, pointer);
//        Gdx.app.log("MOBILE", "DRAG OCCURRED");
//
//        newTouch.set(screenX, screenY);
//
//        Vector2 delta = newTouch.cpy().sub(lastTouch);
//
//        if (delta.x > 0) {
//            Gdx.app.log("MOBILE", "MOVE RIGHT");
//        } else if (delta.x < 0) {
//            Gdx.app.log("MOBILE", "MOVE LEFT");
//        }
//
//        if (delta.y > 0) {
//            Gdx.app.log("MOBILE", "MOVE UP");
//        } else if (delta.y < 0) {
//            Gdx.app.log("MOBILE", "MOVE DOWN");
//        }
//
//        lastTouch = newTouch;

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }


    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
