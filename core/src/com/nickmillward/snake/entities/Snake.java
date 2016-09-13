package com.nickmillward.snake.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nickmillward.snake.utils.Constants;
import com.nickmillward.snake.utils.Enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmillward on 9/13/16.
 */
public class Snake {

    List<Point> snakePoints;

    private Enums.Direction direction;
    private Vector2 position;
    boolean isMoving, isAddPoint;

    public Snake() {
        snakePoints = new ArrayList<Point>();
        direction = Enums.Direction.UP;
        position = new Vector2(50, 50);
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

    public void move() {
        Point head = snakePoints.get(0);
        Point tail = snakePoints.get(snakePoints.size() - 1);
        Point newStart = new Point(head.getX() + position.x * Constants.EMOJI_DEFAULT_SIZE, head.getY() + position.y * Constants.EMOJI_DEFAULT_SIZE);

        // Set new points for the Body
        for (int i = snakePoints.size(); i > 0; i--) {
            snakePoints.set(i, snakePoints.get(i - 1));
        }

        // Set a new point for the Head
        snakePoints.set(0, newStart);
    }

    public Enums.Direction getDirection() {
        return direction;
    }

    public void setDirection(Enums.Direction direction) {
        this.direction = direction;
    }

    // Get Head of Snake
    public float getXofHead() {
        return snakePoints.get(0).getX();
    }

    public float getYofHead() {
        return snakePoints.get(0).getY();
    }
}
