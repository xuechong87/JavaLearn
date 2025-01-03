package com.luckystars.tests.tetris;

import static com.luckystars.tests.tetris.Constants.*;

/**
 * 所有基底
 * 方块落下之后加入基底
 */
public class BaseGround {

    private int[][] ground = new int[WIDTH][HEIGHT];

    public BaseGround() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                ground[i][j] = 0;
            }
        }
    }





}
