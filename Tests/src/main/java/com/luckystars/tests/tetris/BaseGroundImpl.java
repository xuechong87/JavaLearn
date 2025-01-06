package com.luckystars.tests.tetris;

import javax.swing.*;

import java.awt.*;

import static com.luckystars.tests.tetris.Constants.*;

/**
 * 所有基底
 * 方块落下之后加入基底
 */
public class BaseGroundImpl implements BaseGround{

    private int[][] ground = new int[HEIGHT][WIDTH];
    private JPanel panel;
    public BaseGroundImpl(JPanel panel) {
        this.panel = panel;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                ground[i][j] = 0;
            }
        }
    }


    @Override
    public void pushIntoGround(Block block){
        synchronized (this) {
            int[][] shape = block.getShape();
            int x = block.getX();
            int y = block.getY();

            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[i].length; j++) {
                    if (shape[i][j] == 1) {
                        ground[y + i][x + j] = 1;
                    }
                }
            }
            this.printGround();
            this.panel.repaint();
        }
    }

    @Override
    public void drawGround(Graphics g) {
        g.setColor(Color.GREEN);
        int[][] shape = this.ground;
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    g.fillRect( j * PIC_UNIT ,  i * PIC_UNIT , PIC_UNIT, PIC_UNIT);
                }
            }
        }
        //画出每一格的边框

    }


    @Override
    public void printGround(){
        Utils.printShape(ground);
    }




}
