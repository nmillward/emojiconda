package com.nickmillward.snake.entities;

/**
 * Created by nmillward on 9/13/16.
 */
public class SnakeSegment {

    private float x, y;

    public SnakeSegment() {
        x = 0;
        y = 0;
    }

    public SnakeSegment(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
