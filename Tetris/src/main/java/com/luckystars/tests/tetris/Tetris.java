package com.luckystars.tests.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.luckystars.tests.tetris.Constants.PIC_UNIT;


public class Tetris  extends JFrame {

    private final JPanel gamePanel;

    private Block currentBlock;

    private final BaseGroundImpl ground;

    private final Timer timer;

    private volatile boolean isRunning = true;

    public Tetris() {

        setTitle("Tetris");
        setSize(Constants.WIDTH*PIC_UNIT, Constants.HEIGHT*PIC_UNIT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new JPanel() {

            @Override
            public void paint(Graphics g) {
                g.setColor(Color.GREEN);
//                currentBlock.eraseBlock(g);
                super.paint(g);
                ground.drawGround(g);
                currentBlock.drawBlock(g);
                // 在这里绘制游戏元素
            }

        };

        this.ground = new BaseGroundImpl(gamePanel);
        this.currentBlock = BlockImpl.getRandomBlock(gamePanel,ground);


        gamePanel.setBackground(Color.BLACK);

        gamePanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                synchronized (gamePanel) {

                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        System.out.println("tab");
                        if (timer.isRunning()) {
                            isRunning = false;
                            timer.stop();
                        } else {
                            isRunning = true;
                            timer.restart();
                        }
                    }
                    // 暂停时不处理按键
                    if (!isRunning) {
                        return;
                    }
                    // 处理按键事件
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        System.out.println("Left");
                        currentBlock.moveLeft();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

                        System.out.println("Right");
                        currentBlock.moveRight();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        System.out.println("UP");
                        currentBlock.turn();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        System.out.println("Down");
                        currentBlock.moveDown();
                        if(ground.gameEnd(currentBlock)){
                            currentBlock.moveUp();
                            ground.pushIntoGround(currentBlock);
                            gamePanel.repaint();
                            timer.stop();
                            isRunning = false;
                        }else{
                            if (ground.hitGround(currentBlock)) {
                                ground.pushIntoGround(currentBlock);
                                currentBlock = BlockImpl.getRandomBlock(gamePanel, ground);
                                ground.removeFullLines();
                            }
                        }
                    }
//                if (e.getKeyCode() == KeyEvent.VK_SPACE)  {
//                    System.out.println("space");
//                    ground.pushIntoGround(currentBlock);
//                    currentBlock = BlockImpl.getRandomBlock(gamePanel,ground);
//                }

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        gamePanel.setFocusable(true); // 使JPanel获得焦点，才能接收键盘事件
        add(gamePanel);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (gamePanel) {
                    currentBlock.moveDown();
                    if(ground.gameEnd(currentBlock)){
                        currentBlock.moveUp();
                        ground.pushIntoGround(currentBlock);
                        timer.stop();
                        isRunning = false;
                    }else{
                        if (ground.hitGround(currentBlock)) {
                            ground.pushIntoGround(currentBlock);
                            currentBlock = BlockImpl.getRandomBlock(gamePanel, ground);
                            ground.removeFullLines();
                        }
                    }
                    repaint();
                }

            }
        };

        timer = new Timer(800, listener);
        timer.start();

        setVisible(true);
    }



    public static void main(String[] args) throws InterruptedException {
        new Tetris();

    }
}
