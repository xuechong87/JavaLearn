package org.luckystars.snake;


import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static org.luckystars.snake.Constants.*;

/**
 * 所有基底
 * 方块落下之后加入基底
 */
public class BaseGroundImpl implements BaseGround {

    private int[][] ground = new int[HEIGHT][WIDTH];
    private JPanel panel;
    private volatile Vector2D food;


    public BaseGroundImpl(JPanel panel) {
        this.panel = panel;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                ground[i][j] = 0;
            }
        }
        createNewFood();
    }

    @Override
    public int[][] getGround() {
        return ground;
    }



    public boolean lastLinefull(){
        for (int i = 0; i < WIDTH; i++) {
            if(ground[HEIGHT-1][i] == 0){
                return false;
            }
        }
        return true;
    }


    @Override
    public void drawGround(Graphics g) {
        g.setColor(Color.GREEN);
        int[][] shape = this.ground;
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    g.fillRect( j * PIC_UNIT ,  i * PIC_UNIT , PIC_UNIT, PIC_UNIT);
                    // 设置边框颜色
                    g.setColor(Color.BLACK);
                    // 绘制边框，边框宽度为1像素，所以左上角坐标要向内偏移1像素
                    g.drawRect(j * PIC_UNIT + 1, i * PIC_UNIT + 1, PIC_UNIT - 1, PIC_UNIT - 1);
                    // 恢复方块颜色
                    g.setColor(Color.GREEN);
                }
            }
        }
        //画食物位置
        g.fillRect((int)this.food.getX(),(int)this.food.getY(),Constants.PIC_UNIT,Constants.PIC_UNIT);
        //画出外边框
        g.setColor(Color.CYAN);
        g.drawRect(0, 0, WIDTH * PIC_UNIT, HEIGHT * PIC_UNIT);
        g.setColor(Color.GREEN);
    }

    @Override
    public Vector2D createNewFood() {
        synchronized (this) {
            Random r = new Random(System.nanoTime());
            int x = r.nextInt(WIDTH);
            int y = r.nextInt(HEIGHT);
            Vector2D food = new Vector2D(x,y);
            this.food = food;
            return food;
        }
    }

    @Override
    public void printGround(){
        Utils.printShape(ground);
    }


    @Override
    public Vector2D getFood() {
        return food;
    }

    @Override
    public void setFood(Vector2D food) {
        this.food = food;
    }
}
