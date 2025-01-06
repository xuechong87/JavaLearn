package com.luckystars.tests.tetris;

import java.awt.*;

public interface BaseGround {
    void pushIntoGround(Block block);

    void drawGround(Graphics g);

    void printGround();
}
