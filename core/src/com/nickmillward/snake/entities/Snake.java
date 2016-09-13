package com.nickmillward.snake.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nickmillward.snake.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmillward on 9/13/16.
 */
public class Snake {

    List<Point> snakePoints;

    public int xDir, yDir;
    boolean isMoving, isAddPoint;

    public Snake() {
        snakePoints = new ArrayList<Point>();
        xDir = 0;
        yDir = 0;
        isMoving = false;
        isAddPoint = false;
        snakePoints.add(new Point(Constants.SNAKE_DEFAULT_START_POINT.x, Constants.SNAKE_DEFAULT_START_POINT.y));
        for (int i = 1; i < Constants.SNAKE_DEFAULT_LENGTH; i++) {
            snakePoints.add(new Point(Constants.SNAKE_DEFAULT_START_POINT.x, Constants.SNAKE_DEFAULT_START_POINT.y - i * Constants.EMOJI_DEFAULT_SIZE));
        }
    }

    public void render(SpriteBatch batch) {

        Texture happyFace = new Texture("happy.png");

        for (Point p : snakePoints) {
            batch.draw(happyFace, p.getX(), p.getY(), Constants.EMOJI_DEFAULT_SIZE, Constants.EMOJI_DEFAULT_SIZE);
        }

    }

    public void update(float delta) {
        keyPressed();
        Gdx.app.log("SNAKE", "Direction: " + getxDir() + ", " + getyDir());
        move();
    }

    public void move() {
        Point head = snakePoints.get(0);
        Point tail = snakePoints.get(snakePoints.size() - 1);
        Point newStart = new Point(head.getX() + xDir * 4, head.getY() + yDir * 4);

        // Set new points for the Body
        for (int i = snakePoints.size() - 1; i >= 1; i--) {
            snakePoints.set(i, snakePoints.get(i - 1));
        }

        // Set a new point for the Head
        snakePoints.set(0, newStart);
    }

    public void keyPressed() {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (getxDir() != 1) {
                setxDir(-1);
                setyDir(0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (getxDir() != -1) {
                setxDir(1);
                setyDir(0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (getyDir() != -1) {
                setxDir(0);
                setyDir(1);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (getyDir() != 1) {
                setxDir(0);
                setyDir(-1);
            }
        }
    }

    public int getxDir() {
        return xDir;
    }

    public void setxDir(int xDir) {
        this.xDir = xDir;
    }

    public int getyDir() {
        return yDir;
    }

    public void setyDir(int yDir) {
        this.yDir = yDir;
    }

    // Get Head of Snake
    public float getXofHead() {
        return snakePoints.get(0).getX();
    }

    public float getYofHead() {
        return snakePoints.get(0).getY();
    }
}
