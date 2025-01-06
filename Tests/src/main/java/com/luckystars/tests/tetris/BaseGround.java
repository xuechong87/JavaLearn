package com.luckystars.tests.tetris;

import java.awt.*;

public interface BaseGround {
    int[][] getGround();

    boolean hitGround(Block block);

    void pushIntoGround(Block block);

    void drawGround(Graphics g);

    void printGround();
}
