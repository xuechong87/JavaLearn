package org.luckystars.snake;


import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;

public interface BaseGround {
    int[][] getGround();

    void drawGround(Graphics g);

    void printGround();

    Vector2D createNewFood();

    Vector2D getFood();

    void setFood(Vector2D food);
}
