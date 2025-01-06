package com.luckystars.tests.tetris;

import javax.swing.*;

import java.awt.*;

import static com.luckystars.tests.tetris.Constants.*;

/**
 * 所有基底
 * 方块落下之后加入基底
 */
public class BaseGroundImpl implements BaseGround{

    private int[][] ground = new int[WIDTH][HEIGHT];
    private JPanel panel;
    public BaseGroundImpl(JPanel panel) {
        this.panel = panel;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                ground[i][j] = 0;
            }
        }
    }

    public boolean hit(int[][] shape, int x, int y){
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    if (x + j < 0 || x + j >= WIDTH || y + i >= HEIGHT) {
                        return true;
                    }
                    if (y + i >= 0 && ground[x + j][y + i] == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
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
                        ground[x + j][y + i] = 1;
                    }
                }
            }
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
    }


    @Override
    public void printGround(){
        Utils.printShape(ground);
    }




}
