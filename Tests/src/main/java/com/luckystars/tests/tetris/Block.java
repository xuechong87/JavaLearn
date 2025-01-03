package com.luckystars.tests.tetris;

import java.awt.*;

public interface Block {

    void printShape();

    void turn();

    void moveLeft();

    void moveRight();

    void moveDown();

    void drawBlock(Graphics g);

    void eraseBlock(Graphics g);
}
