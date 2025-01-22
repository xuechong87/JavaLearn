package com.luckystars.tests.tetris;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
    public int[][] getGround() {
        return ground;
    }

    @Override
    public boolean hitGround(Block block){
        int[][] shape = block.getShape();
        int x = block.getX();
        int y = block.getY();
        System.out.println("x:"+x + " y:"+y);

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    //获取下方是否有砖块或者超出边界
                    if((i + y+1) >= HEIGHT || ground[y + i +1][x + j] == 1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean gameEnd(Block block){
//        if(true){
//            return false;
//        }
        int[][] shape = block.getShape();
        int x = block.getX();
        int y = block.getY()-1;//刷新位置
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    if(ground[y + i +1][x + j] == 1){
                        System.out.println("game over");
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
                        ground[y + i][x + j] = 1;
                    }
                }
            }
            this.printGround();
            this.panel.repaint();
            System.out.println("lastLine full" + lastLinefull());
        }
    }

    public boolean lastLinefull(){
        for (int i = 0; i < WIDTH; i++) {
            if(ground[HEIGHT-1][i] == 0){
                return false;
            }
        }
        return true;
    }

    public void removeLastLine(){
        synchronized (this) {
            int[][] newGround = new int[HEIGHT][WIDTH];
            Arrays.fill(newGround[0], 0);
            for (int i = 0; i < HEIGHT - 1; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    newGround[i + 1][j] = ground[i][j];
                }
            }
            this.ground = newGround;
        }
    }

    public java.util.List<Integer> getFullLines(){
        java.util.List<Integer> fullLines = new ArrayList<>();
        for (int i = 0; i < HEIGHT; i++) {
            boolean full = true;
            for (int j = 0; j < WIDTH; j++) {
                if(ground[i][j] == 0){
                    full = false;
                    break;
                }
            }
            if(full){
                fullLines.add(i);
            }
        }
        return fullLines;
    }

    @Override
    public void removeFullLines() {
        synchronized (this) {
            java.util.List<Integer> fullLines = getFullLines();
            if(fullLines!=null&&!fullLines.isEmpty()){

                int[][] newGround = new int[HEIGHT][WIDTH];
                int size = fullLines.size();
                for(int i =0;i<fullLines.size();i++){
                    Arrays.fill(newGround[i], 0);
                }
                int newIndex = size;
                for (int i = 0; i < HEIGHT ; i++) {
                    if(fullLines.contains(i)){
                        continue;
                    }
                    System.arraycopy(ground[i], 0, newGround[newIndex], 0, WIDTH);
                    newIndex++;
                }
                this.ground = newGround;
            }
        }
    }

    @Override
    public void drawGround(Graphics g) {
        if(DEBUG){
            printGround();
            return;
        }
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
        //画出外边框
        g.setColor(Color.CYAN);
        g.drawRect(0, 0, WIDTH * PIC_UNIT, HEIGHT * PIC_UNIT);
        g.setColor(Color.GREEN);
    }


    @Override
    public void printGround(){
        Utils.printShape(ground);
    }




}
